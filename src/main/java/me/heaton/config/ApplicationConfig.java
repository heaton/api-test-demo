package me.heaton.config;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.DispatcherServlet;

import javax.sql.DataSource;
import java.util.EventListener;

@Configuration
public class ApplicationConfig {

  @Bean
  public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
    ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
    registration.addUrlMappings("/services/*");
    return registration;
  }

  @Bean
  public ServletListenerRegistrationBean<EventListener> getMyListener() {
    ServletListenerRegistrationBean<EventListener> listeners = new ServletListenerRegistrationBean<>();
    listeners.setListener(new WebAppContextLoaderListener());
    listeners.setOrder(1);
    return listeners;
  }

  @Bean
  public TomcatEmbeddedServletContainerFactory tomcatFactory() {
    return new TomcatEmbeddedServletContainerFactory() {

      @Override
      protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
        tomcat.enableNaming();
        return super.getTomcatEmbeddedServletContainer(tomcat);
      }

      @Override
      protected void postProcessContext(Context context) {
        context.getNamingResources().addResource(newResource("DS1", "jdbc:mysql://localhost/test"));
        context.getNamingResources().addResource(newResource("DS2", "jdbc:mysql://localhost/test2"));
      }

      private ContextResource newResource(String name, String url) {
        ContextResource resource = new ContextResource();
        resource.setName(name);
        resource.setType(DataSource.class.getName());
        resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        resource.setProperty("url", url);
        return resource;
      }
    };
  }

  @Bean(value = "ds1", destroyMethod = "")
  public JndiObjectFactoryBean testDataSource() {
    final JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
    jndiBean.setJndiName("DS1");
    jndiBean.setResourceRef(true);
    jndiBean.setLookupOnStartup(false);
    jndiBean.setExpectedType(DataSource.class);
    return jndiBean;
  }

  @Bean(value = "ds2", destroyMethod = "")
  public JndiObjectFactoryBean testDataSource2() {
    final JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
    jndiBean.setJndiName("DS2");
    jndiBean.setResourceRef(true);
    jndiBean.setLookupOnStartup(false);
    jndiBean.setExpectedType(DataSource.class);
    return jndiBean;
  }

}
