package edu.fjut.mybatis.controller;

import edu.fjut.mybatis.entity.Role;
import edu.fjut.mybatis.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理 Controller
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /** 获取所有角色 */
    @GetMapping
    public Map<String, Object> findAll() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", roleService.findAll());
        return result;
    }

    /** 根据ID查询 */
    @GetMapping("/{id}")
    public Map<String, Object> findById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Role role = roleService.findById(id);
        result.put("success", role != null);
        result.put("data", role);
        return result;
    }

    /** 新增角色 */
    @PostMapping
    public Map<String, Object> add(@RequestParam String roleName,
                                   @RequestParam String permissions) {
        Map<String, Object> result = new HashMap<>();
        Role role = new Role();
        role.setRoleName(roleName);
        role.setPermissions(permissions);
        int rows = roleService.addRole(role);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "添加成功" : "添加失败");
        return result;
    }

    /** 修改角色 */
    @PutMapping
    public Map<String, Object> update(@RequestParam Integer id,
                                      @RequestParam(required = false) String roleName,
                                      @RequestParam(required = false) String permissions) {
        Map<String, Object> result = new HashMap<>();
        Role role = new Role();
        role.setId(id);
        if (roleName != null && !roleName.isEmpty()) role.setRoleName(roleName);
        if (permissions != null && !permissions.isEmpty()) role.setPermissions(permissions);
        int rows = roleService.updateRole(role);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "修改成功" : "修改失败");
        return result;
    }

    /** 删除角色 */
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        int rows = roleService.deleteRole(id);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "删除成功" : "删除失败");
        return result;
    }
}
