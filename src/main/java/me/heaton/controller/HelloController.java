package me.heaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class HelloController {

  @Autowired
  @Qualifier("ds1")
  private DataSource ds;

  @GetMapping("hello")
  public String hello() {
    return "Hello World!";
  }

  @GetMapping("jndi/test")
  public String jndiTest() {
    //final DataSource ds = (DataSource) jndi.getObject();
    return "DataSource is " + ds.toString();
  }

}
