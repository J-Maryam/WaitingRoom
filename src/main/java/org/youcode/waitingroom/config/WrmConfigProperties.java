package org.youcode.waitingroom.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.Algorithm;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.TypeMode;

@Configuration
@ConfigurationProperties(prefix = "wrm")
@Getter
@Setter
public class WrmConfigProperties {
    private TypeMode mode = TypeMode.FULL_TIME;
    private Algorithm algorithm = Algorithm.FIFO;
    private int maxPerDay = 15;

}
