package com.person.serivce;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {

    private int carId;
    private String brand;
    private String color;
    private int personId;
    private String transmission;
    private String motorType;

}
