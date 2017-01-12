package me.heaton.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DbInitialization {

  private static final Log logger = LogFactory.getLog(DbInitialization.class);

  @EventListener(ContextRefreshedEvent.class)
  public void initDB() throws IOException {
    logger.info("==== initial database ===");
    final Process process = Runtime.getRuntime().exec("pwd");
    logger.info(Streams.asString(process.getInputStream()));
    logger.info("==== end initial database ===");
  }
}
