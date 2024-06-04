package com.bnta.chocolate.components;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.repositories.EstateRepository;
import com.bnta.chocolate.services.ChocolateService;
import com.bnta.chocolate.services.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    EstateService estateService;

    @Autowired
    ChocolateService chocolateService;

    public void run(ApplicationArguments args) throws Exception {

        Estate cadbury = new Estate("Cadbury", "UK");
        Estate mars = new Estate("Mars", "USA");

        Chocolate dairyMilk = new Chocolate("Dairy Milk", 55, cadbury);
        Chocolate marsBar = new Chocolate("Mars Bar", 45, mars);

        estateService.createEstate(cadbury);
        estateService.createEstate(mars);

        chocolateService.createChocolate(dairyMilk);
        chocolateService.createChocolate(marsBar);


    }




}
