package com.ceiec.twmp.tmp.admin;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CreateDate：2018/11/28 <br/>
 * Author：wenliang <br/>
 * Description: Admin main class
 **/


@SpringBootApplication
@EnableAdminServer
public class TwmpAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwmpAdminApplication.class, args);
    }

}
