package exceptions;

/**
 * 06.05.2017
 * UserNotFoundException
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UserNotFoundException extends RuntimeException {
    private int userId;

    public UserNotFoundException() {
        super();
        this.userId = -1;
    }
    public UserNotFoundException(int userId) {
        super();
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "User with id <" + userId + "> not found";
    }


}
