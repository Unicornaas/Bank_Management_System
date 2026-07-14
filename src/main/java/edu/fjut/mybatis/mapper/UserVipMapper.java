package edu.fjut.mybatis.mapper;

import edu.fjut.mybatis.entity.UserVip;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserVipMapper {

    /** 查询所有VIP等级（按sort_order排序） */
    List<UserVip> findAll();

    /** 根据余额匹配VIP等级 */
    UserVip findByBalance(Double balance);
}
