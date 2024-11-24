package com.example.cashcard;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardRepository cashCardRepository;

    private CashCardController(CashCardRepository cashCardRepository)
    {
        this.cashCardRepository = cashCardRepository;
    }


    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId)
    {
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId);
        if(cashCardOptional.isPresent())
        {
            return ResponseEntity.ok(cashCardOptional.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }


    }

    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb){

        CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
        URI locationOfNewCashCard = ucb.path("cashcards/{id}").buildAndExpand(savedCashCard.id()).toUri();


        return ResponseEntity.created(locationOfNewCashCard).build();


    }

}

/*
Searching through the output, we find this line:

org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'example.cashcard.CashCardRepository' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
Clues such as NoSuchBeanDefinitionException, No qualifying bean, and expected at least 1 bean which qualifies as autowire candidate tell us that Spring is trying to find a properly configured class to provide during the dependency injection phase of Auto Configuration, but none qualify.

We can satisfy this DI requirement by extending the CrudRepository.
 */