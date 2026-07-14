package edu.fjut.mybatis.service;

import edu.fjut.mybatis.entity.Buss;
import edu.fjut.mybatis.mapper.BussMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BussService {

    @Autowired
    private BussMapper bussMapper;

    /** 根据用户ID查流水 */
    public List<Buss> findByUid(Integer uid) {
        return bussMapper.findByUid(uid);
    }

    public List<Buss> findAll() {
        return bussMapper.findAll();
    }

    /** 统计流水总数 */
    public int count() {
        return bussMapper.count();
    }

    /** 新增流水记录 */
    public int addBuss(Integer uid, String type, Double amount) {
        Buss buss = new Buss();
        buss.setUid(uid);
        buss.setType(type);
        buss.setAmount(amount);
        return bussMapper.insert(buss);
    }
}
