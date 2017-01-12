package me.heaton.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

public class WebAppContextLoaderListener extends ContextLoaderListener{

  private static final Log logger = LogFactory.getLog(WebAppContextLoaderListener.class);

  @Override
  public void contextInitialized(ServletContextEvent event) {
    logger.info("=== Initial web app context loader listener ===");
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    logger.info("=== Destroy web app context loader listener ===");
  }
}
