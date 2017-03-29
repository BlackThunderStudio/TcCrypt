package info.rajmundstaniek.TcCrypt.exception;

/**
 * Created by rajmu on 17.03.16.
 */
public class DigestRuntimeException extends Exception {
    public DigestRuntimeException() {
        super();
    }

    public DigestRuntimeException(String message) {
        super(message);
    }

    public DigestRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigestRuntimeException(Throwable cause) {
        super(cause);
    }

    protected DigestRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
