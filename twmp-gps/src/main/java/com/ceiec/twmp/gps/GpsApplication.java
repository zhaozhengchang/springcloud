package com.ceiec.twmp.gps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ding
 * @version V1.0
 * @Description: twmp gps application boot
 * @create 2019-03-18 17:18
 **/
@SpringBootApplication
public class GpsApplication {



    public static void main(String[] args){

        SpringApplication.run(GpsApplication.class, args);

        StorageService.start();
        FenceAnalysisService.start();

    }




}
