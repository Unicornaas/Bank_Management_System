package edu.fjut.mybatis.service;

import edu.fjut.mybatis.entity.UserVip;
import edu.fjut.mybatis.mapper.UserVipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserVipService {

    @Autowired
    private UserVipMapper userVipMapper;

    /** 获取所有VIP等级 */
    public List<UserVip> findAll() {
        return userVipMapper.findAll();
    }

    /** 根据余额获取VIP等级 */
    public UserVip getLevelByBalance(Double balance) {
        if (balance == null) balance = 0.0;
        return userVipMapper.findByBalance(balance);
    }
}
