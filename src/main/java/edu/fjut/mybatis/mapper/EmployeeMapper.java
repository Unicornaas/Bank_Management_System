package edu.fjut.mybatis.mapper;

import edu.fjut.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<Employee> findAll();

    int count();

    Employee findById(Integer id);

    int insert(Employee employee);

    int update(Employee employee);

    int deleteById(Integer id);

    List<Employee> search(Employee employee);

    List<Employee> findByPage(@Param("offset") int offset, @Param("size") int size);

    /** 员工登录 */
    Employee findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
