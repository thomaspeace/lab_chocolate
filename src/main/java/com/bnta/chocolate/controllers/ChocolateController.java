package com.bnta.chocolate.controllers;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.services.ChocolateService;
import com.bnta.chocolate.services.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chocolates")
public class ChocolateController {

    @Autowired
    ChocolateService chocolateService;

    EstateService estateService;

//    INDEX localhost:8080/chocolates?cocoa_percentage=50
//    INDEX localhost:8080/chocolates
        @GetMapping
        public ResponseEntity<List<Chocolate>> getAllChocolates(@RequestParam Optional<Integer> cocoa_percentage) {
            List<Chocolate> chocolates;
            if (cocoa_percentage.isPresent()) {
                chocolates = chocolateService.getChocolateWithCocoaGreater(cocoa_percentage.get());
            }  else {
                chocolates = chocolateService.getAllChocolates();
            }
            if (chocolates.isEmpty()) {
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

    //    POST
    @PostMapping
    public ResponseEntity<Chocolate> createChocolate(@RequestBody ChocolateDTO chocolate) {
        Chocolate createdChocolate = chocolateService.createChocolateDTO(chocolate);
        return new ResponseEntity<>(createdChocolate, HttpStatus.CREATED);

//        EXT - Returning NULL value
//            if(estateService.getEstateById(chocolate.getEstateId()).isPresent()) {
//                Chocolate createdChocolate = chocolateService.createChocolateDTO(chocolate);
//                return new ResponseEntity<>(createdChocolate, HttpStatus.CREATED);
//            } else {
//                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            }

    }

}
