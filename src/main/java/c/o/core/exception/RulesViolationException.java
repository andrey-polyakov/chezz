package c.o.core.exception;

/**
 * Indicates rules violation for this operation.
 */
public class RulesViolationException extends Error {

    public RulesViolationException(String message) {
        super(message);
    }
}
