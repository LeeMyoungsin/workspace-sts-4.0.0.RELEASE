package kr.hsz.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private String name;
    private String city;
    private String country;
    private Integer age;
    private String sex;

}