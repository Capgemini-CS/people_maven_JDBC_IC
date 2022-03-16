package com.person.bean;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonBean {

    private int personId;
    private String firstName;
    private String lastName;
    private int age;
    private String cnp;
    private int addressId;
    private int jobId;


}