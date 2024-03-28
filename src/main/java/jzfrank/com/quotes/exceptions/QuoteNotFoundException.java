package jzfrank.com.quotes.exceptions;

import java.util.UUID;

public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException(UUID id) {
        super("Could not find quote with id: " + id);
    }
}
