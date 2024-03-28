package jzfrank.com.quotes.controller;

import jzfrank.com.quotes.exceptions.QuoteNotFoundException;
import jzfrank.com.quotes.models.Quote;
import jzfrank.com.quotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/quotes")
public class QuoteApiController {

    @Autowired
    private QuoteRepository quoteRepository;
    @GetMapping
    public Iterable<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @PostMapping
    public Quote addQuote(@RequestBody Quote quote) {
        return quoteRepository.save(quote);
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable("id") UUID id) {
        quoteRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Quote getOneQuote(@PathVariable("id") UUID id) {
        return quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Quote replaceQuote(@RequestBody Quote newQuote, @PathVariable("id") UUID id) {
        return quoteRepository.findById(id).map(
                quote -> {
                    quote.setQuote(newQuote.getQuote());
                    quote.setAuthorName(newQuote.getAuthorName());
                    return quoteRepository.save(quote);
                }).orElseGet(
                () -> {
                    newQuote.setId(id);
                    return quoteRepository.save(newQuote);
                });
    }
}
