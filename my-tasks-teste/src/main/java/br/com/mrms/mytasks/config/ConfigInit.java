package br.com.mrms.mytasks.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"br.com.mrms.mytasks"})
@PropertySource("application.yml")
public class ConfigInit {

}
