package blacksmith.post.exceptions.member;

public class MemberLoginValidException extends MemberException{
    public MemberLoginValidException(String message) {
        super(message);
    }

    public MemberLoginValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberLoginValidException(Throwable cause) {
        super(cause);
    }

    public MemberLoginValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
