package priv.eric;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author EricTownsChina
 */
@EnableCaching
@MapperScan("priv.eric.bc.dao")
@ConfigurationPropertiesScan(basePackages = {"priv.eric.kit"})
@SpringBootApplication(scanBasePackages = {"priv.eric.bc", "priv.eric.kit"})
public class BootFastWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootFastWebApplication.class, args);
    }

}
