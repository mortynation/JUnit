package com.junittest.laptopservice.repository;

import com.junittest.laptopservice.entity.Laptop;
import com.junittest.laptopservice.respository.LaptopsDatabase;
import com.junittest.laptopservice.service.LaptopsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LaptopsDatabaseTests {

    @Autowired
    private LaptopsDatabase laptopsDatabase;

    @Test
    public void LaptopsDatabase_Save_ReturnSavedLaptop(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Apple").modelName("M2 Macbook Pro").os("MacOS").build();

        //Act
        Laptop savedLaptop = laptopsDatabase.save(laptop);

        //Assert
        Assertions.assertThat(savedLaptop).isNotNull();
        Assertions.assertThat(savedLaptop.getLaptopId()).isGreaterThan(0);
    }

    @Test
    public void LaptopsDatabase_GetAll_ReturnAllLaptops(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Lenovo").modelName("Legion").os("Ubuntu").build();
        Laptop laptop1 =  Laptop.builder().brandName("Apple").modelName("Macbook Pro").os("MacOS").build();

        //Act
        laptopsDatabase.save(laptop);
        laptopsDatabase.save(laptop1);
        List<Laptop> laptops = laptopsDatabase.findAll();

        //Assert
        Assertions.assertThat(laptops).isNotNull();
        Assertions.assertThat(laptops.size()).isGreaterThan(0);
    }

    @Test
    public void LaptopsDatabase_GetById_ReturnLaptopById(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("HP").modelName("Omen").os("Windows 11").build();
        laptopsDatabase.save(laptop);

        //Act
        Optional<Laptop> laptopById = laptopsDatabase.findById(laptop.getLaptopId());

        //Assert
        Assertions.assertThat(laptopById).isNotNull();

    }

    @Test
    public void LaptopDatabase_FindByType_ReturnLaptopNotNull(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Lenovo").modelName("Legion").os("Ubuntu").build();

        //Act
        laptopsDatabase.save(laptop);

        Laptop laptopSave = laptopsDatabase.findById(laptop.getLaptopId()).get();
        laptopSave.setBrandName("HP");
        laptopSave.setModelName("Omen");
        laptopSave.setOs("Windows OS");

        Laptop updateLaptop = laptopsDatabase.save(laptopSave);

        Assertions.assertThat(updateLaptop.getBrandName()).isNotNull();
        Assertions.assertThat(updateLaptop.getModelName()).isNotNull();
        Assertions.assertThat(updateLaptop.getOs()).isNotNull();

    }

    @Test
    public void LaptopDatabase_Delete_ReturnLaptopIsEmpty(){

        //Arrange
        Laptop laptop = Laptop.builder().brandName("Lenovo").modelName("Legion").os("Ubuntu").build();

        //Act
        laptopsDatabase.save(laptop);
        laptopsDatabase.delete(laptop);
        Optional<Laptop> deletedLaptop = laptopsDatabase.findById(laptop.getLaptopId());

        //Assert
        Assertions.assertThat(deletedLaptop).isEmpty();
    }

}
