package edu.fjut.mybatis.controller;

import edu.fjut.mybatis.entity.Employee;
import edu.fjut.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /** 获取所有员工 */
    @GetMapping("/employees")
    public Map<String, Object> findAll() {
        Map<String, Object> result = new HashMap<>();
        List<Employee> employees = employeeService.findAll();
        employees.forEach(e -> e.setPassword(null));
        result.put("success", true);
        result.put("data", employees);
        return result;
    }

    /** 分页查询员工 */
    @GetMapping("/employees/page")
    public Map<String, Object> findByPage(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> result = new HashMap<>();
        List<Employee> employees = employeeService.findByPage(page, size);
        employees.forEach(e -> e.setPassword(null));
        int total = employeeService.count();
        result.put("success", true);
        result.put("data", employees);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        result.put("totalPages", (int) Math.ceil((double) total / size));
        return result;
    }

    /** 根据ID查询 */
    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) employee.setPassword(null);
        return employee;
    }

    /** 新增员工 */
    @PostMapping("/employees")
    public Map<String, Object> add(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String name,
                                   @RequestParam(required = false, defaultValue = "") String gender,
                                   @RequestParam(required = false, defaultValue = "0") Integer age,
                                   @RequestParam(required = false, defaultValue = "") String phone,
                                   @RequestParam(required = false, defaultValue = "") String email,
                                   @RequestParam(required = false, defaultValue = "") String position,
                                   @RequestParam(required = false, defaultValue = "0") BigDecimal salary,
                                   @RequestParam(required = false) Integer branchId,
                                   @RequestParam(required = false, defaultValue = "在职") String status) {
        Map<String, Object> result = new HashMap<>();
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setName(name);
        employee.setGender(gender);
        employee.setAge(age);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setPosition(position);
        employee.setSalary(salary);
        employee.setBranchId(branchId);
        employee.setStatus(status);

        int rows = employeeService.addEmployee(employee);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "添加成功" : "添加失败");
        return result;
    }

    /** 修改员工 */
    @PutMapping("/employees")
    public Map<String, Object> update(@RequestParam Integer id,
                                      @RequestParam(required = false) String username,
                                      @RequestParam(required = false) String password,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String gender,
                                      @RequestParam(required = false) Integer age,
                                      @RequestParam(required = false) String phone,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) String position,
                                      @RequestParam(required = false) BigDecimal salary,
                                      @RequestParam(required = false) Integer branchId,
                                      @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Employee employee = new Employee();
        employee.setId(id);
        if (username != null && !username.isEmpty()) employee.setUsername(username);
        if (password != null && !password.isEmpty()) employee.setPassword(password);
        if (name != null && !name.isEmpty()) employee.setName(name);
        if (gender != null && !gender.isEmpty()) employee.setGender(gender);
        if (age != null) employee.setAge(age);
        if (phone != null && !phone.isEmpty()) employee.setPhone(phone);
        if (email != null && !email.isEmpty()) employee.setEmail(email);
        if (position != null && !position.isEmpty()) employee.setPosition(position);
        if (salary != null) employee.setSalary(salary);
        if (branchId != null) employee.setBranchId(branchId);
        if (status != null && !status.isEmpty()) employee.setStatus(status);

        int rows = employeeService.updateEmployee(employee);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "修改成功" : "修改失败");
        return result;
    }

    /** 删除员工 */
    @DeleteMapping("/employees/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        int rows = employeeService.deleteEmployee(id);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "删除成功" : "删除失败");
        return result;
    }

    /** 搜索员工 */
    @PostMapping("/employees/search")
    public Map<String, Object> search(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String gender,
                                      @RequestParam(required = false) String position,
                                      @RequestParam(required = false) Integer branchId,
                                      @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Employee query = new Employee();
        if (name != null && !name.isEmpty()) query.setName(name);
        if (gender != null && !gender.isEmpty()) query.setGender(gender);
        if (position != null && !position.isEmpty()) query.setPosition(position);
        if (branchId != null) query.setBranchId(branchId);
        if (status != null && !status.isEmpty()) query.setStatus(status);

        List<Employee> employees = employeeService.search(query);
        employees.forEach(e -> e.setPassword(null));
        result.put("success", true);
        result.put("data", employees);
        return result;
    }
}
