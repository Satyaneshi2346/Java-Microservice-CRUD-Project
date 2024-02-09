package com.example.Transportation.controller;

import com.example.Transportation.model.Employee;
import com.example.Transportation.repo.EmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api")

public class EmployeeTransport {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        try {
            List<Employee> employeeList = new ArrayList<>();
            employeeRepo.findAll().forEach(employeeList::add);

            if (employeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getEmployeebyID/{id}")
    public ResponseEntity<Employee> getEmployeebyID(@PathVariable Long id) {

        Optional<Employee> empObj = employeeRepo.findById(id);
        if (empObj.isPresent()) {
            return new ResponseEntity<>(empObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee empObj = employeeRepo.save(employee);
            return new ResponseEntity<>(empObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Optional<Employee> employeeData = employeeRepo.findById(id);
            if (employeeData.isPresent()) {
                Employee updatedEmployeeData = employeeData.get();
                updatedEmployeeData.setFirstNameLastName(employee.getFirstNameLastName());
                updatedEmployeeData.setTransportationNeeded(employee.getTransportationNeeded());
                updatedEmployeeData.setTravelDistance(employee.getTravelDistance());

                Employee empObj = employeeRepo.save(updatedEmployeeData);
                return new ResponseEntity<>(empObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        try {
            employeeRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllEmployee")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            employeeRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
