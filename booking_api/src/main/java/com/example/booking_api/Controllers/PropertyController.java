package com.example.booking_api.Controllers;

import com.example.booking_api.Models.Property;
import com.example.booking_api.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperty() {
        return new ResponseEntity<>(propertyService.allProperty(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Property>> getRequestProperty(
            @RequestParam String location,
            @RequestParam Date checkInDate,
            @RequestParam Date checkOutDate,
            @RequestParam int room,
            @RequestParam int adult,
            @RequestParam int children,
            @RequestParam boolean petAllow) {
        List<Property> properties = propertyService.getRequestProperty(location, checkInDate, checkOutDate, room, adult, children, petAllow);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<Property> verifyProperty(
            @RequestParam int propertyId,
            @RequestParam Date checkInDate,
            @RequestParam Date checkOutDate,
            @RequestParam int room,
            @RequestParam int adult,
            @RequestParam int children,
            @RequestParam boolean petAllow) {
        Property property = propertyService.verifyProperty(propertyId, checkInDate, checkOutDate, room, adult, children, petAllow);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }
}
