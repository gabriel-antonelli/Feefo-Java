package core.services;

import core.InputString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputStringTest {

    @Mock
    private final InputString inputString = new InputStringImp();

    @Test
    @DisplayName("Should return inserted string")
    void testExpectedFlux() {
        String mockedInput = "test";
        System.setIn(new ByteArrayInputStream(mockedInput.getBytes()));
        String inputted = inputString.getInputtedString();
        assertEquals(mockedInput, inputted);
    }
}
