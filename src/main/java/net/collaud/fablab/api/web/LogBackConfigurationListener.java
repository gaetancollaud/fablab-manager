package net.collaud.fablab.api.web;
import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class LogBackConfigurationListener implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(LogBackConfigurationListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String logbackConfigFile = sce.getServletContext()
				.getInitParameter("logback.configurationFile");
		URL configFileURL;
		if (logbackConfigFile != null) {
			configFileURL = Thread.currentThread().getContextClassLoader()
					.getResource(logbackConfigFile);
			if (configFileURL != null) {
				JoranConfigurator configurator = new JoranConfigurator();
				LoggerContext loggerContext = (LoggerContext) LoggerFactory
						.getILoggerFactory();
				loggerContext.reset();
				configurator.setContext(loggerContext);
				try {
					configurator.doConfigure(configFileURL);
					logger.info("Logback configured successfully with config: " + configFileURL + ".");
				} catch (JoranException ex) {
					logger.error("Error while configuring logback !", ex);
				}
			} else {
				logger.error("Could not find logback config file !");
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
