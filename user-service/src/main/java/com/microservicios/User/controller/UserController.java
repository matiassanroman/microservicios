package com.microservicios.User.controller;

import com.microservicios.User.entity.User;
import com.microservicios.User.model.Car;
import com.microservicios.User.model.UserCar;
import com.microservicios.User.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getcarsbyuserid/{id}")
    public ResponseEntity<UserCar> getCarsByUserId(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        UserCar userCar = userService.getCarsByUserId(userId);
        return ResponseEntity.ok(userCar);
    }

    @CircuitBreaker(name = "carCB", fallbackMethod = "fallbackSaveCar")
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") Long userId, @RequestBody Car car){
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Car carNew = userService.saveCar(userId,car);
        return ResponseEntity.ok(carNew);
    }
    
    private ResponseEntity<Car> fallbackSaveCar(@PathVariable("userId") Long userId, @RequestBody Car car, RuntimeException e){
        return ResponseEntity("Por favor contactarse con el admin", HttpStatus.OK);
    }
}
