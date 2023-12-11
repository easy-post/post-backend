package blacksmith.post.exceptions.member;

public class MemberNotLoginException extends MemberException{
    public MemberNotLoginException(String message) {
        super(message);
    }

    public MemberNotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNotLoginException(Throwable cause) {
        super(cause);
    }

    public MemberNotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
