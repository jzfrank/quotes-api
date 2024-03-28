package jzfrank.com.quotes.controller;

import jzfrank.com.quotes.config.QuoteModelAssembler;
import jzfrank.com.quotes.exceptions.QuoteNotFoundException;
import jzfrank.com.quotes.models.Quote;
import jzfrank.com.quotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quotes")
public class QuoteApiController {

    private final QuoteRepository quoteRepository;
    private final QuoteModelAssembler quoteModelAssembler;

    public QuoteApiController(QuoteRepository quoteRepository, QuoteModelAssembler quoteModelAssembler) {
        this.quoteRepository = quoteRepository;
        this.quoteModelAssembler = quoteModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Quote>> getAllQuotes() {
        List<EntityModel<Quote>> quotes = quoteRepository.findAll()
                .stream()
                .map(quoteModelAssembler::toModel).
                toList();
        return CollectionModel.of(
                quotes,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuoteApiController.class).getAllQuotes()).withSelfRel()
                );
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
    public EntityModel<Quote> getOneQuote(@PathVariable("id") UUID id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
        return quoteModelAssembler.toModel(quote);
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
