package app.factories;

import core.InputString;
import core.services.InputStringImp;
import core.services.JobTitleNormalizerImp;
import utils.StringNormalizer;
import utils.StringNormalizerImp;
import utils.StringVerifier;
import utils.StringVerifierImp;

public class JobTitleNormalizerFactory {
    public JobTitleNormalizerImp create() {
        InputString inputString = new InputStringImp();
        StringVerifier stringVerifier = new StringVerifierImp();
        StringNormalizer stringNormalizer = new StringNormalizerImp();
        return new JobTitleNormalizerImp(inputString, stringVerifier, stringNormalizer);
    }
}
