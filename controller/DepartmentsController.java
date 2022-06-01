package pro.sky.java.course2.hw211.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.hw211.data.Employee;
import pro.sky.java.course2.hw211.services.EmployeeService;
import pro.sky.java.course2.hw211.services.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    private final EmployeeService departmentsService;

    public DepartmentsController(EmployeeServiceImpl departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping("/min-salary")
    public Optional<Employee> getEmployeeInDepartmentMinSalary(@RequestParam int department) {
        return departmentsService.getEmployeeInDepartmentMinSalary(department);
    }

    @GetMapping("/max-salary")
    public Optional<Employee> getEmployeeInDepartmentMaxSalary(@RequestParam int department) {
        return departmentsService.getEmployeeInDepartmentMaxSalary(department);
    }

    @GetMapping("/print-department")
    public List<Employee> printEmployeesInDepartment(@RequestParam int department) {
        return departmentsService.printEmployeesInDepartment(department);
    }

    @GetMapping("/print-all")
    public List<Employee> printEmployeesInDepartment() {
        return departmentsService.printEmployeesDepartment();
    }


}
