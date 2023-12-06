package blacksmith.post.exceptions.member;

public class MemberRegisterBlankException extends MemberException{
    public MemberRegisterBlankException(String message) {
        super(message);
    }

    public MemberRegisterBlankException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberRegisterBlankException(Throwable cause) {
        super(cause);
    }

    public MemberRegisterBlankException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
