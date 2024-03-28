package jzfrank.com.quotes.config;

import jzfrank.com.quotes.exceptions.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class QuoteNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(QuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String quoteNotFoundHandler(QuoteNotFoundException ex) {
        return ex.getMessage();
    }
}
