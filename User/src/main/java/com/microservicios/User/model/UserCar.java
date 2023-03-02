package com.microservicios.User.model;

import com.microservicios.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCar {
    User user;
    List<Car> listCar;
}
