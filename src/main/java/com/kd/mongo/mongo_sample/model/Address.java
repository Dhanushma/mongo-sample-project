package com.kd.mongo.mongo_sample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Address {

    private String city;

    private String postalCode;

    private String street;
}
