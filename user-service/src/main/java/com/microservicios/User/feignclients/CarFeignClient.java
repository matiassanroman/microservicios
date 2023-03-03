package com.microservicios.User.feignclients;

import com.microservicios.User.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="car-service", url="http://localhost:8082", path = "/car")
public interface CarFeignClient {

    @PostMapping()
    Car save(@RequestBody  Car car);

    @GetMapping("/byuser/{userId}")
    List<Car> getCarsByUserId(@PathVariable("id") Long idUser);
}
