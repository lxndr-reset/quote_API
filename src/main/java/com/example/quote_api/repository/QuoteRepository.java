package com.example.quote_api.repository;

import com.example.quote_api.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(nativeQuery = true, value = "select * from \"quote\" order by rate  limit 10")
    Quote[] get10worst();

    @Query(nativeQuery = true, value = "select * from \"quote\" order by rate desc limit 10")
    List<Quote> get10best();

    @Query(nativeQuery = true, value = "select * from \"quote\" ORDER BY RAND() LIMIT 1")
    Optional<Quote> getRandom();
}
