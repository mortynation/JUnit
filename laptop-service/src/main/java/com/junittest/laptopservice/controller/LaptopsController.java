package com.junittest.laptopservice.controller;

import com.junittest.laptopservice.entity.Laptop;
import com.junittest.laptopservice.service.LaptopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laptops")
public class LaptopsController {

    @Autowired
    private LaptopsService laptopsService;


    @PostMapping ("/")
    public Laptop saveLaptop(@RequestBody Laptop laptop){
        return laptopsService.saveLaptop(laptop);
    }

    @GetMapping("/{laptopId}")
    public Laptop getLaptopById(@PathVariable Long laptopId){
        return laptopsService.getLaptopById(laptopId);
    }

    @GetMapping("/getAll")
    public List<Laptop> getAllLaptops(){
        return laptopsService.getAllLaptops();
    }

    @PutMapping("/update/{laptopId}")
    public Laptop updateLaptopById(@PathVariable Long laptopId, @RequestBody Laptop laptop){
        return laptopsService.updateLaptopById(laptopId, laptop);

    }

    @DeleteMapping("/delete/{laptopId}")
    public void deleteLaptopById(@PathVariable Long laptopId){
        laptopsService.deleteLaptopById(laptopId);
    }
}
