package com.junittest.laptopservice.service;

import com.junittest.laptopservice.entity.Laptop;
import com.junittest.laptopservice.respository.LaptopsDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LaptopsServiceTests {

    @Mock
    private LaptopsDatabase laptopsDatabase;

    @InjectMocks
    private LaptopsService laptopsService;

    @Test
    public void LaptopsService_SaveLaptop_ReturnsLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        when(laptopsDatabase.save(Mockito.any(Laptop.class))).thenReturn(laptop);

        //Act
        Laptop savedLaptop = laptopsService.saveLaptop(laptop);

        //Assert
        Assertions.assertThat(savedLaptop).isNotNull();
    }

    @Test
    public void LaptopsService_GetAll_ReturnsLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        Laptop laptop1 = Laptop.builder().brandName("Lenovo").modelName("Legion").os("Ubuntu").build();
        laptopsDatabase.save(laptop);
        laptopsDatabase.save(laptop1);
        List<Laptop> laptops = laptopsDatabase.findAll();
        when(laptopsDatabase.findAll()).thenReturn(laptops);

        //Act
        List<Laptop> laptopsTest = laptopsService.getAllLaptops();

        //Assert
        Assertions.assertThat(laptopsTest).isEqualTo(laptops);

    }

    @Test
    public void LaptopsService_GetById_ReturnLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        laptopsDatabase.save(laptop);
        when(laptopsDatabase.findByLaptopId(1L)).thenReturn(laptop);

        //Act
        Laptop laptopById = laptopsService.getLaptopById(1L);

        //Assert
        Assertions.assertThat(laptopById).isNotNull();

    }
//
//    public void LaptopsService_UpdateLaptop_







}
