package com.property.manager.controllers;

import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by EKAC on 12.10.2017 г..
 */

@RestController
public class PropertyController {

    @Autowired
    private IPropertyService propertyService;

    @RequestMapping(value="/",method=GET)
    public ResponseEntity<List<Property>> getAllProperties() {
        System.out.println("properties get controller");
        List<Property> list = propertyService.getAllProperties();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //Accepts form-data through post requests
    //TO DO: check for for null values and duplicate entries(cancel automatically incremented id-s) 
    @RequestMapping(value="/",method=POST)
    public ResponseEntity<String> addProperty(@ModelAttribute Property property) {
        System.out.print(property);
        boolean flag = propertyService.addProperty(property);
        if (flag) {
            return new ResponseEntity("Created",HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity("Failed",HttpStatus.I_AM_A_TEAPOT);
        }

    }



}
