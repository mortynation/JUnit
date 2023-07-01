package com.junittest.laptopservice.respository;

import com.junittest.laptopservice.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface LaptopsDatabase extends JpaRepository<Laptop, Long> {

    Laptop findByLaptopId(Long laptopId);
}
