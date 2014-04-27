package sk.bsmk.csrf;

import org.springframework.boot.SpringApplication;

/**
 * Created by bsmk on 4/26/14.
 */
public class Application {

  public static void main(String[] args) throws Throwable {
    SpringApplication.run(WebSecurityConfig.class, args);
  }

}