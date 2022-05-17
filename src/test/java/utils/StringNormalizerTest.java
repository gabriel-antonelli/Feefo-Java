package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.text.Normalizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringNormalizerTest {

    @Mock
    private final StringNormalizer stringNormalizer = new StringNormalizerImp();

    @Test
    @DisplayName("Should return lowercase string")
    void shouldReturnLowerCase() {
        String normalizer = stringNormalizer.normalize("TEST");
        assertEquals("test", normalizer);
    }

    @Test
    @DisplayName("Should return string without trailing spaces")
    void shouldReturnWithoutTrailingSpaces() {
        String normalizer = stringNormalizer.normalize("  TEST ");
        assertEquals("test", normalizer);
    }

    @Test
    @DisplayName("Should return normalized string with NFD form")
    void shouldReturnNormalized() {
        String normalizer = stringNormalizer.normalize("TEST");
        boolean isNormalized = Normalizer.isNormalized(normalizer, Normalizer.Form.NFD);
        assertTrue(isNormalized);
    }

    @Test
    @DisplayName("Should return string without accents")
    void shouldReturnWithAccents() {
        String normalizer = stringNormalizer.normalize("TÉSTÃ");
        assertEquals("testa", normalizer);
    }

    @Test
    @DisplayName("Should not thrown errors with empty string")
    void shouldNotThrown() {
        String normalizer = stringNormalizer.normalize(" ");
        assertEquals("", normalizer);
    }
}
