package com.property.manager.controllers;

import com.property.manager.models.Property;
import com.property.manager.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by EKAC on 12.10.2017 г..
 */

@RestController
public class PropertyController {

    @Autowired
    private IPropertyService propertyService;

    @RequestMapping("/")
    public ResponseEntity<List<Property>> getAllProperties() {
        System.out.println("properties get controller");
        List<Property> list = propertyService.getAllProperties();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
