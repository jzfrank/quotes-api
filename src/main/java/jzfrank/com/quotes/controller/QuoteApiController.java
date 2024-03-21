package jzfrank.com.quotes.controller;

import jzfrank.com.quotes.models.Quote;
import jzfrank.com.quotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quotes")
public class QuoteApiController {

    @Autowired
    private QuoteRepository quoteRepository;
    @GetMapping
    public Iterable<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }
}
