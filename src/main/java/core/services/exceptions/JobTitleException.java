package core.services.exceptions;

public class JobTitleException extends RuntimeException{
    public JobTitleException(final String errorMessage) {
        super(errorMessage);
    }
}
