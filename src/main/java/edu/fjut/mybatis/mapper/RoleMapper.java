package edu.fjut.mybatis.mapper;

import edu.fjut.mybatis.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色 Mapper
 */
@Mapper
public interface RoleMapper {

    List<Role> findAll();

    Role findById(Integer id);

    int insert(Role role);

    int update(Role role);

    int deleteById(Integer id);
}
