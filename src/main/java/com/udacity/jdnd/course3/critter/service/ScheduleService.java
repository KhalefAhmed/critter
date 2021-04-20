package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exceptions.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.exceptions.PetNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserService userService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Optional<Schedule> findSchedule(Long id) {
        return scheduleRepository.findById(id);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Transactional
    public Schedule save(Schedule s)
            throws PetNotFoundException, EmployeeNotFoundException {

        s = scheduleRepository.save(s);

        // save the schedule to employees
        for (Employee employee : s.getEmployees()){
            employee.getSchedules().add(s);
            employeeRepository.save(employee);
        }

        // save the schedule to pets
        for (Pet pet : s.getPets()) {
            pet.getSchedules().add(s);
            petRepository.save(pet);
        }

        return s;
    }

    public List<Schedule> findSchedulesForPet(long petId) {
        Pet p = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("ID: " + petId));
        return p.getSchedules();
    }

    public List<Schedule> findSchedulesForEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("ID: " + employeeId));

        return employee.getSchedules();
    }

}
