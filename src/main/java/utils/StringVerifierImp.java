package utils;

public class StringVerifierImp implements StringVerifier {
    @Override
    public boolean isValidString(String string) {
        return string.length() <= 50 && !string.isEmpty();
    }
}
