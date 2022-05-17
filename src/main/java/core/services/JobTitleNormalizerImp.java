package core.services;

import core.InputString;
import core.JobTitleNormalizer;
import core.services.exceptions.InvalidStringException;
import core.services.exceptions.NotExpectedJobTitleException;
import utils.StringNormalizer;
import utils.StringVerifier;

public class JobTitleNormalizerImp implements JobTitleNormalizer {

    private final InputString inputString;
    private final StringVerifier stringVerifier;
    private final StringNormalizer stringNormalizer;

    public JobTitleNormalizerImp(InputString inputString, StringVerifier stringVerifier, StringNormalizer stringNormalizer) {
        this.inputString = inputString;
        this.stringVerifier = stringVerifier;
        this.stringNormalizer = stringNormalizer;
    }

    @Override
    public String normalizeTitle() {
        String inputtedString = inputString.getInputtedString();
        verifyString(inputtedString);
        String normalizedString = stringNormalizer.normalize(inputtedString);
        if(normalizedString.contains("architect")) {
            return "Architect";
        }
        if(normalizedString.contains("accountant")) {
            return "Accountant";
        }
        if(normalizedString.contains("engineer")) {
            return "Software engineer";
        }
        if(normalizedString.contains("surveyor")) {
            return "Quantity surveyor";
        }
        throw new NotExpectedJobTitleException("The job title: " + inputtedString + " was not expected");
    }

    private void verifyString(String string) {
        boolean isStringValid = stringVerifier.isValidString(string);
        if(!isStringValid) {
            throw new InvalidStringException("String : " + string + " is not valid");
        }
    }
}
