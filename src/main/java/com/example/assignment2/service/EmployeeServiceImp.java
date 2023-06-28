package com.example.assignment2.service;

import com.example.assignment2.entity.Employee;
import com.example.assignment2.exception.UserNotFoundException;
import com.example.assignment2.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public void createEmployee(Employee employee) {
        log.info("Method Started : " + "createEmployee()");
        employeeRepository.save(employee);
        log.info("Methode Ended : createEmployee()");
    }

    public List<Employee> getAllEmployees() {
        log.info("Method Started : getAllEmployees()");
        List<Employee> employeeList = employeeRepository.findAll();
        log.info("Method Ended : getAllEmployees()");
        return employeeList;
    }

    public Employee findUserById(int id) {
        log.info("Methode Started : findUserById()");
        Employee byId = employeeRepository.findById(id);

        if (byId == null) {
            log.warn("Exception :  UserNotFound");
            throw new UserNotFoundException();
        }
        log.info("Methode Ended : findUserById()");
        return byId;
    }

    public void deleteUserById(int id) {
        log.info("Methode Started : deleteUserById()");
        Employee byId = employeeRepository.findById(id);
        if (byId == null) {
            log.warn("Exception :  UserNotFound");
            throw new UserNotFoundException();
        } else {

            employeeRepository.deleteById(id);
            log.info("User Deleted By Id");
        }
    }

    public List<Employee> sortEmployeeByName() {
        log.info("Methode Started : sortEmployeeByName()");
        List<Employee> employeeList = employeeRepository.findAll().stream().sorted(Comparator.comparing(Employee::getFirstName)).toList();
        log.info("Methode Ended : sortEmployeeByName()");
        return employeeList;
    }
}


