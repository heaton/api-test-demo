package me.heaton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

  @Bean(destroyMethod = "stop")
  public MocoInitialization mocoInitialization() {
    final MocoInitialization moco = new MocoInitialization();
    moco.init();
    return moco;
  }

}
