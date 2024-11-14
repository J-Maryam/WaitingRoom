package org.youcode.waitingroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.youcode.waitingroom.config.WrmConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(WrmConfigProperties.class)
public class WaitingRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaitingRoomApplication.class, args);
        System.out.println("Waiting Room Application Started");
    }

}
