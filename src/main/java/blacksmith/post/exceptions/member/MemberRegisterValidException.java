package blacksmith.post.exceptions.member;

public class MemberRegisterValidException extends MemberException{
    public MemberRegisterValidException(String message) {
        super(message);
    }

    public MemberRegisterValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberRegisterValidException(Throwable cause) {
        super(cause);
    }

    public MemberRegisterValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
