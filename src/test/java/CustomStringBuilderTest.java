import org.example.new_implementation.CustomStringBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomStringBuilderTest {

    @Test
    void testAppendSucces() {

        CustomStringBuilder builder = new CustomStringBuilder("Привет");

        builder.append(" Мир").append("!");

        assertEquals("Привет Мир!", builder.toString());
    }

    @Test
    void testUndoSuccess() {
        CustomStringBuilder builder = new CustomStringBuilder("Привет");
        builder.append(" Мир").append("!");
        builder.undo();
        assertEquals("Привет Мир", builder.toString());
    }

    @Test
    void testHistoryEmpty() {
        CustomStringBuilder builder = new CustomStringBuilder("Привет");
        CustomStringBuilder result = builder.undo();
        assertSame(builder, result);
        assertEquals(builder.getHistory().size(), 0);
        assertEquals("Привет", builder.toString());
    }
}
