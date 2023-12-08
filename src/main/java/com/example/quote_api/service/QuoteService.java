package com.example.quote_api.service;

import com.example.quote_api.entity.Quote;
import com.example.quote_api.repository.QuoteRepository;
import com.example.quote_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote[] get10worst() {
        return quoteRepository.get10worst();
    }

    public List<Quote> get10best() {
        return quoteRepository.get10best();
    }

    public Quote getRandom() {
        return quoteRepository.getRandom().orElseThrow(() -> new NoSuchElementException("The quote table is empty"));
    }

    public Quote saveOrUpdate(Quote quote) {
        return quoteRepository.save(quote);
    }

    public void deleteByID(Long id) {
        quoteRepository.deleteById(id);
    }

    public Quote findByID(Long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Quote by id " + id + "not found"));
    }

    public Quote downvoteByID(Long id) {
        Quote currentQuote = findByID(id);
        currentQuote.downvote();

        saveOrUpdate(currentQuote);

        return currentQuote;
    }

    public Quote upvoteByID(Long id) {
        Quote currentQuote = findByID(id);
        currentQuote.upvote();

        saveOrUpdate(currentQuote);

        return currentQuote;
    }
}
