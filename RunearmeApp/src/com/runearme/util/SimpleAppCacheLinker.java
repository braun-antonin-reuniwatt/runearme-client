package com.runearme.util;

import java.util.Arrays;
import java.util.Date;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.Artifact;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.EmittedArtifact;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.LinkerOrder.Order;
import com.google.gwt.core.ext.linker.impl.SelectionInformation;

@LinkerOrder(Order.POST)
public class SimpleAppCacheLinker extends AbstractLinker {

	private static final String MANIFEST = "appcache.nocache.manifest";

	@Override
	public String getDescription() {
		return "AppCacheLinker";
	}

	@Override
	public ArtifactSet link(TreeLogger logger, LinkerContext context,
			ArtifactSet artifacts, boolean onePermutation)
			throws UnableToCompleteException {

		ArtifactSet toReturn = new ArtifactSet(artifacts);

		if (onePermutation) {
			return toReturn;
		}

		if (toReturn.find(SelectionInformation.class).isEmpty()) {
			logger.log(TreeLogger.INFO, "DevMode warning: Clobbering "
					+ MANIFEST + " to allow debugging. "
					+ "Recompile before deploying your app!");
			artifacts = null;
		}

		// Create the general cache-manifest resource for the landing page:
		toReturn.add(emitLandingPageCacheManifest(context, logger, artifacts));
		return toReturn;
	}

	/**
	 * Override this method to force the linker to also include more files in
	 * the manifest.
	 */
	protected String[] otherCachedFiles() {
		return null;
	}

	/**
	 * Creates the cache-manifest resource specific for the landing page.
	 * 
	 * @param context
	 *            the linker environment
	 * @param logger
	 *            the tree logger to record to
	 * @param artifacts
	 *            {@code null} to generate an empty cache manifest
	 */
	private Artifact<?> emitLandingPageCacheManifest(LinkerContext context,
			TreeLogger logger, ArtifactSet artifacts)
			throws UnableToCompleteException {
		StringBuilder publicSourcesSb = new StringBuilder();
		StringBuilder staticResoucesSb = new StringBuilder();

		if (artifacts != null) {
			// Iterate over all emitted artifacts, and collect all cacheable
			// artifacts
			for (@SuppressWarnings("rawtypes")
			Artifact artifact : artifacts) {
				if (artifact instanceof EmittedArtifact) {
					EmittedArtifact ea = (EmittedArtifact) artifact;
					String pathName = ea.getPartialPath();
					if (pathName.endsWith("symbolMap")
							|| pathName.endsWith(".xml.gz")
							|| pathName.endsWith("rpc.log")
							|| pathName.endsWith("gwt.rpc")
							|| pathName.endsWith("manifest.txt")
							|| pathName.startsWith("rpcPolicyManifest")
							|| pathName.startsWith("soycReport")) {
						// skip these resources
					} else {
						publicSourcesSb.append(pathName + "\n");
					}
				}
			}

			String[] cacheExtraFiles = getCacheExtraFiles();
			for (int i = 0; i < cacheExtraFiles.length; i++) {
				staticResoucesSb.append(cacheExtraFiles[i]);
				staticResoucesSb.append("\n");
			}
		}

		// build cache list
		StringBuilder sb = new StringBuilder();
		sb.append("CACHE MANIFEST\n");
		sb.append("# Unique id #" + (new Date()).getTime() + "."
				+ Math.random() + "\n");
		// we have to generate this unique id because the resources can change
		// but
		// the hashed cache.html files can remain the same.
		sb.append("# Note: must change this every time for cache to invalidate\n");
		sb.append("\n");
		sb.append("CACHE:\n");
		sb.append("# Static app files\n");
		sb.append(staticResoucesSb.toString());
		sb.append("\n# Generated app files\n");
		sb.append(publicSourcesSb.toString());
		sb.append("\n\n");
		sb.append("# All other resources require the user to be online.\n");
		sb.append("NETWORK:\n");
		sb.append("*\n");

		logger.log(TreeLogger.INFO,
				"Be sure your landing page's <html> tag declares a manifest:"
						+ " <html manifest=" + context.getModuleFunctionName()
						+ "/" + MANIFEST + "\">");

		// Create the manifest as a new artifact and return it:
		return emitString(logger, sb.toString(), MANIFEST);
	}

	/**
	 * Obtains the extra files to include in the manifest. Ensures the returned
	 * array is not null.
	 */
	private String[] getCacheExtraFiles() {
		String[] cacheExtraFiles = otherCachedFiles();
		return cacheExtraFiles == null ? new String[0] : Arrays.copyOf(
				cacheExtraFiles, cacheExtraFiles.length);
	}
}
