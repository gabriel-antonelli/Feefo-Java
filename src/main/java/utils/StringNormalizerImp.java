package utils;

import java.text.Normalizer;

public class StringNormalizerImp implements StringNormalizer{
    @Override
    public String normalize(String pureString) {
        String lowerNoSpaces = pureString.trim().toLowerCase();
        String decomposed = Normalizer.normalize(lowerNoSpaces, Normalizer.Form.NFD);
        return decomposed.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}
