package app;

import app.factories.JobTitleNormalizerFactory;

import java.util.logging.Logger;

public class JobTitlesNormalizerApplication {
    private static final Logger LOGGER = Logger.getLogger( JobTitlesNormalizerApplication.class.getName() );
    public static void main(String[] args) {
        String foundString = new JobTitleNormalizerFactory().create().normalizeTitle();
        LOGGER.info(foundString);
    }
}
