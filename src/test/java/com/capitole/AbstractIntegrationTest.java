package com.capitole;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

@SpringBootTest(properties = {"spring.datasource.initialization-mode=never"})
@TestPropertySource(value = {
        "classpath:application-test.yml"
})
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractIntegrationTest {


}
