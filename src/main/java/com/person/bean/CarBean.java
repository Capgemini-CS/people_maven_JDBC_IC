package com.person.bean;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarBean {

    private int carId;
    private String brand;
    private String color;
    private int personId;
    private String transmission;
    private String motorType;

}