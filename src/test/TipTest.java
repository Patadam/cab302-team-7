import com.wellnessapp.controller.MainController;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipTest {
    private MainController mainController;

    @BeforeEach
    public void setUp() {
        mainController = new MainController();
    }

    @Test
    public void testInitialize() {
        mainController.initialize(); // Call the initialize method
        TextArea termsAndConditions = mainController.getTermsAndConditions();
        String expectedText = "Text,\n" +
                "Turn your computer off every one hour.\n" +
                "Go to sleep if you feel tired";
        assertEquals(expectedText, termsAndConditions.getText());
    }
}
