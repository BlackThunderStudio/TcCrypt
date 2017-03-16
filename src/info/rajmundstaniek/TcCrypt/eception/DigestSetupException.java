package info.rajmundstaniek.TcCrypt.eception;

/**
 * Created by rajmu on 17.03.16.
 */
public class DigestSetupException extends Exception {
    public DigestSetupException() {
        super();
    }

    public DigestSetupException(String message) {
        super(message);
    }

    public DigestSetupException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigestSetupException(Throwable cause) {
        super(cause);
    }

    protected DigestSetupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
