package pro.sky.java.course2.hw211.services;


import pro.sky.java.course2.hw211.data.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {
    void addEmployee(String lastName, String firstName, int salary, int department);

    void removeEmployee(String lastName, String firstName);

    Employee findEmployee(String lastName, String firstName);

    Map<String, Employee> printEmployees();

    Optional<Employee> getEmployeeInDepartmentMinSalary(int department);

    Optional<Employee> getEmployeeInDepartmentMaxSalary(int department);

    List<Employee> printEmployeesInDepartment(int department);

    List<Employee> printEmployeesDepartment();
}
