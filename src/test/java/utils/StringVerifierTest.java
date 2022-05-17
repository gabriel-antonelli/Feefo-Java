package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringVerifierTest {

    @Mock
    private final StringVerifier stringVerifierImp = new StringVerifierImp();

    @Test
    @DisplayName("Should return true if string is not empty and has 50 or less characters")
    void testValidString() {
        String validString = generate(() -> "t").limit(50).collect(joining());
        boolean isValid = stringVerifierImp.isValidString(validString);
        assertTrue(isValid);
    }

    @Test
    @DisplayName("Should return false if string is empty")
    void testEmptyString() {
        boolean isValid = stringVerifierImp.isValidString("");
        assertFalse(isValid);
    }

    @Test
    @DisplayName("Should return false if string has more than 50 characters")
    void testTooLongString() {
        String longString = generate(() -> "t").limit(51).collect(joining());
        boolean isValid = stringVerifierImp.isValidString(longString);
        assertFalse(isValid);
    }

}
