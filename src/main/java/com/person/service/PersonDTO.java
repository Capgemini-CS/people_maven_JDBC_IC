package com.person.serivce;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PersonDTO {

    private int personId;
    private String firstName;
    private String lastName;
    private int age;
    private String cnp;
    private int addressId;
    private int jobId;


}

