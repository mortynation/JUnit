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
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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
        laptopsDatabase.save(Mockito.any(Laptop.class));
        when(laptopsDatabase.save(Mockito.any(Laptop.class))).thenReturn(laptop);

        //Act
        Laptop savedLaptop = laptopsService.saveLaptop(laptop);

        //Assert
        Assertions.assertThat(savedLaptop).isNotNull();
    }

    @Test
    public void LaptopsService_GetAll_ReturnsLaptop(){

        //Arrange
        Laptop laptop1 = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        Laptop laptop2 = Laptop.builder().brandName("Lenovo").modelName("Legion").os("Ubuntu").build();
        List<Laptop> laptops = new ArrayList<>();
        laptops.add(laptop1);
        laptops.add(laptop2);


//        List<Laptop> laptops = Collections.singletonList(mock(Laptop.class));
        List<Laptop> tempLaptop = laptopsDatabase.findAll();
        when(laptopsDatabase.findAll()).thenReturn(laptops);
        //List<Laptop> resultLaptop = laptopsDatabase.findAll();

        //Act
        List<Laptop> laptopsTest = laptopsService.getAllLaptops();

        //Assert
        Assertions.assertThat(laptopsTest).isEqualTo(laptops);

    }

    @Test
    public void LaptopsService_GetById_ReturnLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();

        when(laptopsDatabase.findByLaptopId(1L)).thenReturn(laptop);

        //Act
        Laptop laptopById = laptopsService.getLaptopById(1L);

        //Assert
        Assertions.assertThat(laptopById).isNotNull();

    }

    @Test
    public void LaptopsService_UpdateLaptop_ReturnLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        when(laptopsDatabase.findByLaptopId(1L)).thenReturn(laptop);
        when(laptopsDatabase.save(Mockito.any(Laptop.class))).thenReturn(laptop);

        //Act
        Laptop updatedLaptop = laptopsService.updateLaptopById(1L, laptop);

        //Assert
        Assertions.assertThat(updatedLaptop).isNotNull();

    }

    @Test
    public void LaptopsService_DeleteLaptop_ReturnNothing(){

        //Arrange

        //Act
        laptopsService.deleteLaptopById(1L);

        //Assert
        verify(laptopsDatabase).deleteById(1L);

    }







}
