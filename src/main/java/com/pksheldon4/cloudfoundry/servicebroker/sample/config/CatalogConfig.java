package com.pksheldon4.cloudfoundry.servicebroker.sample.config;

import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CatalogConfig {

    @Bean
    public Catalog catalog() {
        return new Catalog(Collections.singletonList(
                new ServiceDefinition(
                        getEnvOrDefault("SERVICE_ID", "sample-service-broker"),
                        getEnvOrDefault("SERVICE_NAME", "SampleService"),
                        "A sample service broker implementation",
                        true,
                        false,
                        Collections.singletonList(
                                new Plan(getEnvOrDefault("PLAN_ID", "sample-plan"),
                                "standard",
                                "This is the standard plan. All services are created equally",
                                getPlanMetadata(),
                                true)),
                        Arrays.asList("sample","document"),
                        getServiceDefinitionMetadata(),
                        null,
                        null)));

    }

    private Map<String, Object> getServiceDefinitionMetadata() {
        Map<String, Object> sdMetadata = new HashMap<>();
        sdMetadata.put("displayName", "Sample");
        sdMetadata.put("imageUrl", "http://info.mongodb.com/rs/mongodb/images/MongoDB_Logo_Full.png");
        sdMetadata.put("longDescription", "My Sample Service");
        sdMetadata.put("providerDisplayName", "Pivotal");
        sdMetadata.put("documentationUrl", "https://github.com/pksheldon4/sample-servicebroker");
        sdMetadata.put("supportUrl", "https://github.com/pksheldon4/sample-servicebroker");
        return sdMetadata;
    }

    private Map<String,Object> getPlanMetadata() {
        Map<String,Object> planMetadata = new HashMap<>();
        planMetadata.put("bullets", getBullets());
        return planMetadata;
    }



    private List<String> getBullets() {
        return Arrays.asList("Shared Sample server",
                "100 MB Storage (not enforced)",
                "40 concurrent connections (not enforced)");
    }

    private String getEnvOrDefault(final String variable, final String defaultValue){
        String value = System.getenv(variable);
        if(value != null){
            return value;
        }
        else{
            return defaultValue;
        }
    }
}
