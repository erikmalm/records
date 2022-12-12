package api.demo.records.service;

import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;

import java.util.List;


public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, String email) throws RecordsFieldValueMissingException;
    List<Employee> getAllEmployees();
    Employee findEmployeeByEmail(String email);
    void removeEmployee(String email);

}
