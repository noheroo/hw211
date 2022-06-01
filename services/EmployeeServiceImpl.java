package pro.sky.java.course2.hw211.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.hw211.data.Employee;
import pro.sky.java.course2.hw211.exeptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.hw211.exeptions.EmployeeNotFoundException;
import pro.sky.java.course2.hw211.exeptions.EmployeeWrongData;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public void addEmployee(String lastNameDraft, String firstNameDraft, int salary, int department) {
        if (StringUtils.isNotBlank(lastNameDraft) && StringUtils.isNotBlank(firstNameDraft)) {
            String lastName = makeFirstLetterBig(deleteTrash(lastNameDraft));
            String firstName = makeFirstLetterBig(deleteTrash(firstNameDraft));
            Employee employee = new Employee(lastName, firstName, salary, department);
            if (employees.containsKey(createKey(lastName, firstName))) {
                throw new EmployeeAlreadyAddedException();
            }
            employees.put(createKey(lastName, firstName), employee);
        } else {
            throw new EmployeeWrongData();
        }
    }

    @Override
    public void removeEmployee(String lastName, String firstName) {
        if (!employees.containsKey(createKey(lastName, firstName))) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(createKey(lastName, firstName));
    }

    @Override
    public Employee findEmployee(String lastName, String firstName) {
        if (!employees.containsKey(createKey(lastName, firstName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(createKey(lastName, firstName));
    }

    @Override
    public Map<String, Employee> printEmployees() {
        return employees;
    }

    @Override
    public Optional<Employee> getEmployeeInDepartmentMinSalary(int department) {
        return Optional.ofNullable(employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparing(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException("Employee not found")));
    }

    @Override
    public Optional<Employee> getEmployeeInDepartmentMaxSalary(int department) {
        return Optional.ofNullable(employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparing(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException("Employee not found")));

    }

    @Override
    public List<Employee> printEmployeesInDepartment(int department) {
        return employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> printEmployeesDepartment() {
        return employees.values().stream()
                .sorted(Comparator.comparing(e -> e.getDepartment()))
                .collect(Collectors.toList());
    }

    private String createKey(String lastName, String firstName) {
        return lastName + " " + firstName;
    }

    private String makeFirstLetterBig(String s) {
        return StringUtils.capitalize(s);
    }

    private String deleteTrash(String s) {
        return StringUtils.removeAll(StringUtils.deleteWhitespace(StringUtils.removeAll(s, "\\pP")), "\\d");
    }

}


