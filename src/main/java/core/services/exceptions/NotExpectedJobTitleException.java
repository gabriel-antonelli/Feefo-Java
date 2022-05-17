package core.services.exceptions;

public class NotExpectedJobTitleException extends JobTitleException{
    public NotExpectedJobTitleException(String errorMessage) {
        super(errorMessage);
    }
}
