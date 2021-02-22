package ch.business.decision.configs;

import ch.business.decision.features.Feature1;
import ch.business.decision.features.Feature2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FeatureConfig {

    @Bean
    @Profile("feature1")
    public Runnable feature1() {
        return new Feature1();
    }

    @Bean
    @Profile("feature2")
    public Runnable feature2() {
        return new Feature2();
    }

}
