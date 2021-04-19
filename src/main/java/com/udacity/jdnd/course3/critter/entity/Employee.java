package com.udacity.jdnd.course3.critter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="employee")
public class Employee extends User{
}
