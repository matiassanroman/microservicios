package com.microservicios.User.service;

import com.microservicios.User.config.RestTemplateConfig;
import com.microservicios.User.entity.User;
import com.microservicios.User.feignclients.CarFeignClient;
import com.microservicios.User.model.Car;
import com.microservicios.User.model.UserCar;
import com.microservicios.User.repository.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    IUser iUser;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    public User save(User user){
        return iUser.save(user);
    }

    public User getUserById(Long idUser){
        return iUser.findById(idUser).orElse(null);
    }

    //Metodo con Rest Template
    public UserCar getCarsByUserId(Long idUser){
        User user = getUserById(idUser);
        List<Car> listCar = restTemplate.getForObject("http://car-service/car/getcarsbyuserid/" + idUser, List.class);
        return new UserCar(user,listCar);
    }

    //Metodo con Feign
    public Car saveCar(Long userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

}
