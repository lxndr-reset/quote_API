package com.example.quote_api;

import com.example.quote_api.entity.Quote;
import com.example.quote_api.entity.User;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class QuoteApiApplicationTests {
    public static RestTemplate template = new RestTemplate();
    static Logger logger = LoggerFactory.getLogger(QuoteApiApplicationTests.class);

    @PostConstruct
    public static void init() {
        for (int i = 0; i < 15; i++) {
            String username = "testemail-" + i;
            User user = new User(username, username, "testname-" + i);
            template.postForObject("http://localhost:8080/api/v1/user", user, User.class);

            Quote quote = new Quote("testquote-" + i);
            quote.setUser(user);

            template.postForObject("http://localhost:8080/api/v1/quote/" + user.getEmail(), quote, Quote.class);
            logger.info("testObject-" + i + " added!");
            logger.info(quote.toString());
        }
    }

    @Test
    public void testUpvote_whenQuoteWasUpvotedAndFirstInGetTop10_thenTrue() {
        template.getForEntity("http://localhost:8080/api/v1/quote/up/7", Quote.class).getBody();
        Quote quote = template.getForEntity("http://localhost:8080/api/v1/quote/7", Quote.class).getBody();

        ResponseEntity<List<Quote>> response = template.exchange("http://localhost:8080/api/v1/quote/best", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<Quote> bestQuotes = response.getBody();

        assertNotNull(bestQuotes);
        assertEquals(10, bestQuotes.size());
        assertEquals(quote, bestQuotes.getFirst());
    }
}
