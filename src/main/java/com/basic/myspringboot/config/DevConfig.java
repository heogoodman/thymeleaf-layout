package com.basic.myspringboot.config;

import com.basic.myspringboot.dto.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    @Bean("myCustomer")
    public Customer customer() {
        return Customer.builder() //CustomerBuilder
                .name("DevMode")
                .age(100)
                .build();

    }


}
