package com.example.demo.controller;

import com.example.demo.dto.HotelFullDTO;
import com.example.demo.dto.HotelShortDTO;
import com.example.demo.entity.Hotel;
import com.example.demo.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/") // Базовый префикс задан в application.properties
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;

    @GetMapping("/hotels")
    public List<HotelShortDTO> getAll() {
        return service.getAllShort();
    }

    @GetMapping("/hotels/{id}")
    public HotelFullDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<HotelShortDTO> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenities) {
        return service.search(name, brand, city, country, amenities);
    }

    @PostMapping("/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelShortDTO create(@RequestBody Hotel hotel) {
        return service.create(hotel);
    }


    @PostMapping("/hotels/{id}/amenities")
    public void addAmenities(@PathVariable Long id, @RequestBody List<String> amenities) {
        service.addAmenities(id, amenities);
    }


    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable String param) {
        return service.getHistogram(param);
    }
}