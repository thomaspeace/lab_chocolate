package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.repositories.ChocolateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChocolateService {

    @Autowired
    ChocolateRepository chocolateRepository;

    @Autowired
    EstateService estateService;

    public List<Chocolate> getAllChocolates() {
        return chocolateRepository.findAll();
    }

    public Chocolate createChocolate(Chocolate chocolate) {
        return chocolateRepository.save(chocolate);
    }

    public Chocolate createChocolateDTO(ChocolateDTO chocolate) {
        long estateID = chocolate.getEstateId();
        if(estateService.getEstateById(estateID).isPresent()){
            Chocolate newChocolate = new Chocolate(chocolate.getName(), chocolate.getCocoaPercentage(), estateService.getEstateById(estateID).get());
            return chocolateRepository.save(newChocolate);
        } else {
            return null;
        }
    }

//    EXT
    public Optional<Chocolate> getChocolateById(Long id) {
        return chocolateRepository.findById(id);
    }

    public List<Chocolate> getChocolateWithCocoaGreater (int cocoaPercentage) {
        List<Chocolate> chocolates = chocolateRepository.findByCocoaPercentageGreaterThan(cocoaPercentage);
        return chocolates;
    }

}
