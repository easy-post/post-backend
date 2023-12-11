package blacksmith.post.exceptions.member;

import blacksmith.post.exceptions.UserException;

public class MemberException extends UserException {
    public MemberException(String message) {
        super(message);
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberException(Throwable cause) {
        super(cause);
    }

    public MemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
