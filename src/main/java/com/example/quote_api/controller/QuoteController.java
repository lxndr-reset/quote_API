package com.example.quote_api.controller;

import com.example.quote_api.entity.Quote;
import com.example.quote_api.service.QuoteService;
import com.example.quote_api.service.UserService;
import org.hibernate.boot.model.relational.QualifiedTableName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quote")
class QuoteController {

    private final QuoteService quoteService;
    private final UserService userService;

    QuoteController(QuoteService quoteService, UserService userService) {
        this.quoteService = quoteService;
        this.userService = userService;
    }

    @GetMapping("/worst")
    Quote[] get10WorstQuotes() {
        return quoteService.get10worst();
    }

    @GetMapping("/best")
    ResponseEntity<List<Quote>> get10BestQuotes() {
        return new ResponseEntity<>(quoteService.get10best(), HttpStatus.OK);
    }

    @GetMapping("/random")
    Quote getRandomQuote() {
        return quoteService.getRandom();
    }

    @PostMapping("/{email}")
    Quote saveOrUpdateQuote(@RequestBody Quote quote, @PathVariable String email) {
        quote.setUser(userService.getUserByEmail(email));

        Quote savedQuote = quoteService.saveOrUpdate(quote);
        savedQuote.getUser().setPassword("[PROTECTED]");

        return savedQuote;
    }

    @DeleteMapping("/{id}")
    void deleteQuote(@PathVariable Long id) {
        quoteService.deleteByID(id);
    }

    @GetMapping("/{id}")
    Quote getQuote(@PathVariable Long id) {
        return quoteService.findByID(id);
    }

    @GetMapping("/up/{id}")
    Quote upvoteQuote(@PathVariable Long id) {
        return quoteService.upvoteByID(id);
    }
    @GetMapping("/down/{id}")
    Quote downvoteQuote(@PathVariable Long id) {
        return quoteService.downvoteByID(id);
    }
}
