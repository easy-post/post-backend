package blacksmith.post.exceptions.member;

public class MemberNotExistException extends MemberException{
    public MemberNotExistException(String message) {
        super(message);
    }

    public MemberNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNotExistException(Throwable cause) {
        super(cause);
    }

    public MemberNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
