package core.services;

import core.JobTitleNormalizer;
import core.services.exceptions.InternalSeverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import utils.StringNormalizer;
import utils.StringNormalizerImp;
import utils.StringVerifier;
import utils.StringVerifierImp;

import java.io.ByteArrayInputStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JobTitleNormalizerTest {

    @Mock
    StringVerifier stringVerifier = new StringVerifierImp();
    @Mock
    private InputStringImp inputString = new InputStringImp();
    @Mock
    private StringNormalizer stringNormalizer = new StringNormalizerImp();
    @InjectMocks
    JobTitleNormalizer jobTitleNormalizer = new JobTitleNormalizerImp(inputString, stringVerifier, stringNormalizer);


    @Test
    @DisplayName("Should return Architect title")
    void testArchitectTitle() {
        System.setIn(new ByteArrayInputStream("Business Architect".getBytes()));
        String architect = jobTitleNormalizer.normalizeTitle();
        assertEquals("Architect", architect);
    }

    @Test
    @DisplayName("Should return Accountant title")
    void testAccountantTitle() {
        System.setIn(new ByteArrayInputStream("Business Accountant".getBytes()));
        String accountant = jobTitleNormalizer.normalizeTitle();
        assertEquals("Accountant", accountant);
    }

    @Test
    @DisplayName("Should return Engineer title")
    void testEngineerTitle() {
        System.setIn(new ByteArrayInputStream("Java Engineer".getBytes()));
        String engineer = jobTitleNormalizer.normalizeTitle();
        assertEquals("Software engineer", engineer);
    }

    @Test
    @DisplayName("Should return Surveyor title")
    void testSurveyorTitle() {
        System.setIn(new ByteArrayInputStream("Qtd Surveyor".getBytes()));
        String quantitySurveyor = jobTitleNormalizer.normalizeTitle();
        assertEquals("Quantity surveyor", quantitySurveyor);
    }

    @Test
    @DisplayName("Should return best match")
    void testBestMatch() {
        System.setIn(new ByteArrayInputStream("architect software engineer".getBytes()));
        String bestMatch = jobTitleNormalizer.normalizeTitle();
        assertEquals("Software engineer", bestMatch);
    }
    @Test
    @DisplayName("Should return first title")
    void testDrawScore() {
        System.setIn(new ByteArrayInputStream("Quantity Surveyor software engineer".getBytes()));
        String firstTitle = jobTitleNormalizer.normalizeTitle();
        assertEquals("Quantity surveyor", firstTitle);
    }

    @Test
    @DisplayName("Should thrown InvalidStringException")
    void testInvalidStringException() {
        String longString = generate(() -> "t").limit(51).collect(joining());
        System.setIn(new ByteArrayInputStream(longString.getBytes()));
        assertThrows(InternalSeverException.class, () -> jobTitleNormalizer.normalizeTitle());
    }

}
