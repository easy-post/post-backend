package blacksmith.post.exceptions.member;

public class MemberNicknameDuplicateException extends MemberException{
    public MemberNicknameDuplicateException(String message) {
        super(message);
    }

    public MemberNicknameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNicknameDuplicateException(Throwable cause) {
        super(cause);
    }

    public MemberNicknameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
