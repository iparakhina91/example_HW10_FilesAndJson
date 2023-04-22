import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JacksonTest {

    private ClassLoader cl = JacksonTest.class.getClassLoader();

    @Test
    void jacksonJsonTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("order.json");
             InputStreamReader isr = new InputStreamReader(is)) {

            Order order = objectMapper.readValue(isr, Order.class);

            Assertions.assertEquals(152235, order.id);
            Assertions.assertEquals("Shirt", order.product);
            Assertions.assertEquals(2, order.quantity);
            Assertions.assertEquals("XS", order.size);
            Assertions.assertEquals("Коровина", order.lastname);
            Assertions.assertEquals("Ирина", order.firstname);
            Assertions.assertTrue(order.female);
            Assertions.assertEquals(List.of("88001005230", "irina_korovina@gmail.com"),
                    order.contacts);
        }
    }
}