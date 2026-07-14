package edu.fjut.mybatis.service;

import edu.fjut.mybatis.entity.Role;
import edu.fjut.mybatis.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 Service
 */
@Service
public class RoleService {

    private final RoleMapper roleMapper;

    public RoleService(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public List<Role> findAll() { return roleMapper.findAll(); }

    public Role findById(Integer id) { return roleMapper.findById(id); }

    public int addRole(Role role) { return roleMapper.insert(role); }

    public int updateRole(Role role) { return roleMapper.update(role); }

    public int deleteRole(Integer id) { return roleMapper.deleteById(id); }
}
