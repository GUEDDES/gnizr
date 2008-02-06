package com.gnizr.web.action.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gnizr.core.search.OpenSearchDirectory;
import com.gnizr.core.search.OpenSearchService;
import com.gnizr.web.action.AbstractLoggedInUserAction;
import com.gnizr.web.util.GnizrConfiguration;

public class ListSearchEngines extends AbstractLoggedInUserAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7674261654917938966L;
	private Logger logger = Logger.getLogger(ListSearchEngines.class);

	private static OpenSearchDirectory openSearchDirectory;

	private List<OpenSearchService> services;

	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public List<OpenSearchService> getServices() {
		return services;
	}

	public void setOpenSearchDirectory(OpenSearchDirectory openSearchDirectory) {
		ListSearchEngines.openSearchDirectory = openSearchDirectory;
	}

	@Override
	protected boolean isStrictLoggedInUserMode() {
		return false;
	}

	@Override
	protected String go() throws Exception {
		logger.debug("ListSearchEngine.go(): loggedInUser="
						+ getLoggedInUser());

		openSearchDirectory = getOpenSearchDirectory();

		List<OpenSearchService> allServices = openSearchDirectory.getServices();
		services = new ArrayList<OpenSearchService>();
		if (getLoggedInUser() == null && allServices.isEmpty() == false) {
			for (OpenSearchService srv : allServices) {
				if (srv.isLoginRequired() == false) {
					services.add(srv);
				}
			}
		} else if (allServices.isEmpty() == false) {
			services.addAll(allServices);
		}
		return SUCCESS;
	}

	private synchronized OpenSearchDirectory getOpenSearchDirectory() {
		if (openSearchDirectory == null) {			
			GnizrConfiguration c = getGnizrConfiguration();
			if (c == null) {
				logger.error("Missing GnizrConfiguration");
				throw new RuntimeException("Missing GnizrConfiguration");
			}
			if (c.getOpenSearchServices() == null
					|| !(c.getOpenSearchServices() instanceof List)) {
				final String m = "OpenSearchServices variable is undefined in the GnizrConfiguraiton";
				logger.error(m);
				throw new RuntimeException(m);
			}
			openSearchDirectory = new OpenSearchDirectory(c
					.getOpenSearchServices());
			if (openSearchDirectory.getWebApplicationUrl() == null) {
				String prefixUrl = getGnizrConfiguration()
						.getWebApplicationUrl();
				openSearchDirectory.setWebApplicationUrl(prefixUrl);
			}
			openSearchDirectory.init();
		}
		return openSearchDirectory;
	}
}
