package com.bnta.chocolate.controllers;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.services.ChocolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chocolates")
public class ChocolateController {

    @Autowired
    ChocolateService chocolateService;

//    INDEX
    @GetMapping
    public ResponseEntity<List<Chocolate>> getAllChocolates() {
        List<Chocolate> chocolates = chocolateService.getAllChocolates();
        if(chocolates.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chocolates, HttpStatus.OK);
    }

//    SHOW
    @GetMapping(value = "/{id}")
    public ResponseEntity<Chocolate> getChocolateById(@PathVariable Long id) {
        Optional<Chocolate> selectedChocolate = chocolateService.getChocolateById(id);
        if (selectedChocolate.isPresent()){
            return new ResponseEntity<>(selectedChocolate.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
