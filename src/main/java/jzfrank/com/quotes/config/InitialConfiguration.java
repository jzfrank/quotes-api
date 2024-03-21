package jzfrank.com.quotes.config;

import jzfrank.com.quotes.models.Quote;
import jzfrank.com.quotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitialConfiguration {
    @Bean
    @Autowired
    public CommandLineRunner dataInitializer(
            QuoteRepository quoteRepository
    ) {
        return args -> {
            List<Quote> quotes = getQuotes();
            for (Quote quote: quotes) {
                quoteRepository.save(quote);
            }
        };
    }

    private List<Quote> getQuotes() {
        return List.of(
                new Quote("Don't worry about what anybody else is going to do. The best way to\n" +
                "predict the future is to invent it.", "Alan Kay"),
                new Quote("Premature optimization is the root of all evil (or at least most of it)\n" +
                        "in programming.", "Donald Knuth")
        );
    }
}
