package MoEzwawi.BES6L5.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id) {
        super("User n° "+id+" not found!");
    }
}
