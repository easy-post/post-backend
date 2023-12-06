package blacksmith.post.exceptions.hash;

public class HashNoSuchAlgorithmException extends RuntimeException{
    public HashNoSuchAlgorithmException(String message) {
        super(message);
    }

    public HashNoSuchAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public HashNoSuchAlgorithmException(Throwable cause) {
        super(cause);
    }

    public HashNoSuchAlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
