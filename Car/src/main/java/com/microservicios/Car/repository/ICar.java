package com.microservicios.Car.repository;

import com.microservicios.Car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICar extends JpaRepository<Car, Long> {
    List<Car> findByUserId(Long userId);
}
