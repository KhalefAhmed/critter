package com.udacity.jdnd.course3.critter.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EmployeeManagedRepository {

    @PersistenceContext
    EntityManager entityManager;
}
