package com.microservicios.Car.controller;

import com.microservicios.Car.entity.Car;
import com.microservicios.Car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car){
        Car carNew = carService.save(car);
        return ResponseEntity.ok(carNew);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id){
        Car car = carService.getCarById(id);
        if(car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);
    }

    @GetMapping("/getcarsbyuserid/{id}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("id") Long idUser){
        List<Car> listCar = carService.getCarsByUserId(idUser);
        if(listCar == null || listCar.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(listCar);
    }
}
