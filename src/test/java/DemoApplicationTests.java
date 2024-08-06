import com.example.springbootlessonapp.SpringBootLessonAppApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SpringBootLessonAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests  {

    @Autowired
    private TestRestTemplate restTemplate;

    private static GenericContainer<?> devAppContainer = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    private static GenericContainer<?> prodAppContainer = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devAppContainer.start();
        prodAppContainer.start();
    }

    @Test
    void testDevApp() {
        Integer devPort = devAppContainer.getMappedPort(8080);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + devPort, String.class);
        assertEquals("Expected response from dev profile", response.getBody());
    }

    @Test
    void testProdApp() {
        Integer prodPort = prodAppContainer.getMappedPort(8081);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + prodPort, String.class);
        assertEquals("Expected response from prod profile", response.getBody());
    }
}
