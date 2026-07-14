package edu.fjut.mybatis.service;

import edu.fjut.mybatis.entity.Employee;
import edu.fjut.mybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    public int count() {
        return employeeMapper.count();
    }

    public List<Employee> findByPage(int page, int size) {
        int offset = (page - 1) * size;
        return employeeMapper.findByPage(offset, size);
    }

    public Employee findById(Integer id) {
        return employeeMapper.findById(id);
    }

    public int addEmployee(Employee employee) {
        return employeeMapper.insert(employee);
    }

    public int updateEmployee(Employee employee) {
        return employeeMapper.update(employee);
    }

    public int deleteEmployee(Integer id) {
        return employeeMapper.deleteById(id);
    }

    public List<Employee> search(Employee employee) {
        return employeeMapper.search(employee);
    }

    /** 员工登录验证 */
    public Employee login(String username, String password) {
        return employeeMapper.findByUsernameAndPassword(username, password);
    }
}
