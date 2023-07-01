package com.junittest.laptopservice.service;

import com.junittest.laptopservice.entity.Laptop;
import com.junittest.laptopservice.respository.LaptopsDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopsService {

    @Autowired
    private LaptopsDatabase laptopsDatabase;

    public Laptop saveLaptop(Laptop laptop) {
        return laptopsDatabase.save(laptop);
    }

    public Laptop getLaptopById(Long laptopId) {
        return laptopsDatabase.findByLaptopId(laptopId);
    }

    public List<Laptop> getAllLaptops() {
        return laptopsDatabase.findAll();
    }


    public Laptop updateLaptopById(Long laptopId, Laptop laptop) {
        Laptop existingLaptop = laptopsDatabase.findByLaptopId(laptopId);
        existingLaptop.setBrandName(laptop.getBrandName());
        existingLaptop.setModelName(laptop.getModelName());
        existingLaptop.setOs(laptop.getOs());
        return laptopsDatabase.save(existingLaptop);
    }

    public void deleteLaptopById(Long laptopId) {
        laptopsDatabase.deleteById(laptopId);
    }
}
