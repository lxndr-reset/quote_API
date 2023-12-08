package com.example.quote_api;

import com.example.quote_api.entity.Quote;
import com.example.quote_api.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class QuoteApiApplicationTests {
    public static RestTemplate template = new RestTemplate();

    @BeforeAll
    public static void init() {
        for (int i = 0; i < 15; i++) {
            String username = "testemail-" + i;
            User user = new User(username, username, "testname-" + i);
            template.postForObject("http://localhost:8080/api/v1/user", HttpMethod.POST, User.class, user);

            Quote quote = new Quote("testquote-" + i);
            quote.setUser(user);

            template.postForObject("http://localhost:8080/api/v1/quote/" + user.getEmail(), HttpMethod.POST, Quote.class, quote);
        }
    }

    @Test
    public void testUpvote_whenQuoteWasUpvotedAndFirstInGetTop10_thenTrue() {
        template.getForEntity("http://localhost:8080/api/v1/quote/up/testquote-7", Quote.class).getBody();
        Quote quote = template.getForEntity("http://localhost:8080/api/v1/quote/testquote-7", Quote.class).getBody();

        ResponseEntity<List<Quote>> response = template.exchange("http://localhost:8080/api/v1/quote/best", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        List<Quote> bestQuotes = response.getBody();

        assertNotNull(bestQuotes);
        assertEquals(10, bestQuotes.size());
        assertEquals(quote, bestQuotes.getFirst());
    }
}
