package me.heaton.config;


import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Moco;
import com.github.dreamhead.moco.MocoJsonRunner;

public class MocoInitialization {

  private HttpServer httpServer;

  public void init() {
    //httpServer = MocoJsonRunner.jsonHttpServer(12306, Moco.file("src/test/resources/moco/hello.json"));
  }

  public void stop() {
  }

}
