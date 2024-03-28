package jzfrank.com.quotes.config;

import jzfrank.com.quotes.controller.QuoteApiController;
import jzfrank.com.quotes.models.Quote;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class QuoteModelAssembler implements RepresentationModelAssembler<Quote, EntityModel<Quote>> {
    @Override
    public EntityModel<Quote> toModel(Quote quote) {
        return EntityModel.of(
                quote,
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(QuoteApiController.class)
                                .getOneQuote(quote.getId())
                ).withSelfRel(),
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(QuoteApiController.class)
                                .getAllQuotes()
                ).withRel("quotes")
        );
    }
}
