package com.microservicios.Car.service;

import com.microservicios.Car.entity.Car;
import com.microservicios.Car.repository.ICar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    ICar iCar;

    public Car save(Car car){
        return iCar.save(car);
    }

    public Car getCarById(Long idCar){
        return iCar.findById(idCar).orElse(null);
    }

    public List<Car> getCarsByUserId(Long idUser){
        return iCar.findByUserId(idUser);
    }

}
