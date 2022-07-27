package br.com.mrms.mytasks.config;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import io.cucumber.java.Before;

@ContextConfiguration(classes = ConfigInit.class, loader = SpringBootContextLoader.class)
public class ConfigureSpringContext {

    @Before
    public void SetupSpringContext() {
    }
}
