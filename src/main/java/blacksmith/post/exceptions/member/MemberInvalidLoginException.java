package blacksmith.post.exceptions.member;

public class MemberInvalidLoginException extends MemberException{
    public MemberInvalidLoginException(String message) {
        super(message);
    }

    public MemberInvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberInvalidLoginException(Throwable cause) {
        super(cause);
    }

    public MemberInvalidLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
