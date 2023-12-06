package blacksmith.post.exceptions.member;

public class MemberLoginIdDuplicateException extends MemberException{
    public MemberLoginIdDuplicateException(String message) {
        super(message);
    }

    public MemberLoginIdDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberLoginIdDuplicateException(Throwable cause) {
        super(cause);
    }

    public MemberLoginIdDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
