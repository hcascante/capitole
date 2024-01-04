package com.capitole.infrastructure.conf;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = "com.capitole") // Escaneo de componentes en el paquete com.capitole
public class PriceTestConfig {


}