package edu.fjut.mybatis.mapper;

import edu.fjut.mybatis.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    /** 管理员登录验证 */
    Admin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
