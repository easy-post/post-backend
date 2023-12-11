package blacksmith.post.exceptions.post;

public class PostNotExistException extends PostException{
    public PostNotExistException(String message) {
        super(message);
    }

    public PostNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostNotExistException(Throwable cause) {
        super(cause);
    }

    public PostNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
