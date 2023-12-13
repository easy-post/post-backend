package blacksmith.post.exceptions.post;

public class PostIsNotMineException extends PostException{
    public PostIsNotMineException(String message) {
        super(message);
    }

    public PostIsNotMineException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostIsNotMineException(Throwable cause) {
        super(cause);
    }

    public PostIsNotMineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
