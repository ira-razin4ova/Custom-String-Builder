import org.example.collection_framework.ElementCounter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
;

public class ElementCounterTest {

    @Test
    void testCounterElementEmptyMap() {
        String[] words = new String[0];

        Map<String, Integer> result = ElementCounter.counterElement(words);

        assertEquals(result.size(), 0);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCounterElement() {
        String[] words = {"яблоко", "банан", "яблоко", "апельсин"};

        Map<String, Integer> result = ElementCounter.counterElement(words);

        assertNotNull(result);
        assertEquals(2, result.get("яблоко"));
        assertEquals(1, result.get("апельсин"));
        assertEquals(1, result.get("банан"));
    }
}
