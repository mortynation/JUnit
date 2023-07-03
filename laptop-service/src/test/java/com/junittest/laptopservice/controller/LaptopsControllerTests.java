package com.junittest.laptopservice.controller;

import com.junittest.laptopservice.entity.Laptop;
import com.junittest.laptopservice.service.LaptopsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LaptopsControllerTests {

    @Mock
    LaptopsService laptopsService;

    @InjectMocks
    LaptopsController laptopsController;

    @Test
    public void LaptopsController_SaveLaptop_ReturnLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        when(laptopsService.saveLaptop(Mockito.any(Laptop.class))).thenReturn(laptop);

        //Act
        Laptop savedLaptop = laptopsService.saveLaptop(laptop);

        //Assert
        Assertions.assertThat(savedLaptop).isEqualTo(laptop);
    }

    @Test
    public void LaptopsController_GetLaptopById_ReturnLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        when(laptopsService.getLaptopById(1L)).thenReturn(laptop);

        //Act
        Laptop laptopById = laptopsController.getLaptopById(1L);

        //Assert
        Assertions.assertThat(laptopById).isEqualTo(laptop);
    }

    @Test
    public void LaptopsController_GetAllLaptops_ReturnLaptops(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        Laptop laptop1 = Laptop.builder().brandName("Asus").modelName("VivoBook").os("Windows").build();
        List<Laptop> laptops = new ArrayList<>();
        laptops.add(laptop);
        laptops.add(laptop1);
        when(laptopsService.getAllLaptops()).thenReturn(laptops);

        //Act
        List<Laptop> allLaptops = laptopsController.getAllLaptops();

        //Assert
        Assertions.assertThat(allLaptops).isEqualTo(laptops);

    }

    @Test
    public void LaptopsController_UpdateLaptop_ReturnLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();
        Laptop update = Laptop.builder().brandName("Apple").modelName("M2 Macbook Air").os("MacOS").build();
        when(laptopsService.updateLaptopById(1L, laptop)).thenReturn(update);

        //Act
        Laptop updatedLaptop = laptopsController.updateLaptopById(1L, laptop);

        //Assert
        Assertions.assertThat(updatedLaptop).isEqualTo(update);

    }

    @Test
    public void LaptopsController_DeleteLaptopById_ReturnNothing(){

        //Arrange
        doNothing().when(laptopsService).deleteLaptopById(1L);

        //Act
        laptopsController.deleteLaptopById(1L);

        //Assert
        verify(laptopsService).deleteLaptopById(1L);


    }
}
