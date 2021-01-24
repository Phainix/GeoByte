package co.phainix.geobyte.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes=co.phainix.geobyte.Application.class, locations = "classpath*:/spring/applicationContext*.xml")
@SpringBootTest
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }

}
