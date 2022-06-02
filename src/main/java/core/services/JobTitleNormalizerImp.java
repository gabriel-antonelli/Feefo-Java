package core.services;

import core.InputString;
import core.JobTitleNormalizer;
import core.services.exceptions.InternalSeverException;
import core.services.exceptions.InvalidStringException;
import utils.StringNormalizer;
import utils.StringVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try {
            String inputtedString = inputString.getInputtedString();
            verifyString(inputtedString);
            String normalizedString = stringNormalizer.normalize(inputtedString);
            return findBestMatch(normalizedString);
        } catch (Exception ex) {
            throw new InternalSeverException("An unexpected internal error occurred: " + ex.getMessage());
        }
    }

    private String findBestMatch(String normalizedString) {
        HashMap<String, List<String>> dictionary = createDictionary();
        HashMap<String, Double> scores = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : dictionary.entrySet()) {
            for (String word : entry.getValue()) {
                if (normalizedString.contains(word)) {
                    if (scores.get(entry.getKey()) != null) {
                        return entry.getKey();
                    }
                    scores.put(entry.getKey(), 0.1);
                }
            }
        }
        return scores.keySet().stream().findFirst().orElse(normalizedString);
    }

    private HashMap<String, List<String>> createDictionary() {
        HashMap<String, List<String>> dictionary = new HashMap<>();

        List<String> architect = new ArrayList<>();
        architect.add("architect");

        List<String> softwareEngineer = new ArrayList<>();
        softwareEngineer.add("software");
        softwareEngineer.add("engineer");

        List<String> quantitySurvivor = new ArrayList<>();
        quantitySurvivor.add("quantity");
        quantitySurvivor.add("surveyor");

        List<String> accountant = new ArrayList<>();
        accountant.add("accountant");

        dictionary.put("Architect", architect);
        dictionary.put("Software engineer", softwareEngineer);
        dictionary.put("Quantity surveyor", quantitySurvivor);
        dictionary.put("Accountant", accountant);

        return dictionary;
    }

    private void verifyString(String string) {
        boolean isStringValid = stringVerifier.isValidString(string);
        if (!isStringValid) {
            throw new InvalidStringException("String : " + string + " is not valid");
        }
    }
}
