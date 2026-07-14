package edu.fjut.mybatis.entity;

/**
 * 角色实体
 */
public class Role {
    private Integer id;
    private String roleName;
    private String permissions;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getPermissions() { return permissions; }
    public void setPermissions(String permissions) { this.permissions = permissions; }
}
