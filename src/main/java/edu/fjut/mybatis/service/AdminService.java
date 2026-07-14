package edu.fjut.mybatis.service;

import edu.fjut.mybatis.entity.Admin;
import edu.fjut.mybatis.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /** 管理员登录验证 */
    public Admin login(String username, String password) {
        return adminMapper.findByUsernameAndPassword(username, password);
    }
}
