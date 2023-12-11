package blacksmith.post.exceptions.post;

import blacksmith.post.exceptions.UserException;

public class PostException extends UserException {
    public PostException(String message) {
        super(message);
    }

    public PostException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostException(Throwable cause) {
        super(cause);
    }

    public PostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
