package jzfrank.com.quotes.controller;

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
    public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
        Quote saved = quoteRepository.save(quote);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable("id") UUID id) {
        quoteRepository.deleteById(id);
    }
}
