package com.udacity.jdnd.course3.critter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer extends User{

    private String phoneNumber;

    @Column(length=5000)
    private String notes;
}
