package com.CSMIS.CSMISKhaingFamily.Function;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;
@Service
public class ValitdateAvoid {

    @Autowired
    private AvoidMeatRepository avoidmeatRepository;

    public ValidationResult validateAvoid(String avoidmeat) {
        ValidationResult validationResult = new ValidationResult();
        List<AvoidMeat> avoid = avoidmeatRepository.findmeat(avoidmeat.trim().toLowerCase());
        for (AvoidMeat menu : avoid) {
            if (menu.getMeatName().trim().equalsIgnoreCase(avoidmeat.trim()) || menu.getMeatName().trim().equalsIgnoreCase(avoidmeat)) {
                validationResult.addError("Duplicate Avoid Meat found: " + menu.getMeatName());
                break; 
            }
        }
        return validationResult;
    }

    public String avoidAdd(String avoidMeat) {
        AvoidMeat meat = new AvoidMeat();
        meat.setMeatName(avoidMeat);
        avoidmeatRepository.save(meat);
        return avoidMeat;
    }
}

