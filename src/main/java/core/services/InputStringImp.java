package core.services;

import core.InputString;

import java.util.Scanner;
import java.util.logging.Logger;

public class InputStringImp implements InputString {

    private static final Logger LOGGER = Logger.getLogger( InputStringImp.class.getName() );
    @Override
    public String getInputtedString() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("\nPlease enter a Job Title: ");
        return scanner.nextLine();
    }
}
