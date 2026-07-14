package edu.fjut.mybatis.mapper;

import edu.fjut.mybatis.entity.Buss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BussMapper {

    /** 根据用户ID查询流水账单 */
    List<Buss> findByUid(Integer uid);

    /** 查询所有流水 */
    List<Buss> findAll();

    /** 新增流水记录 */
    int insert(Buss buss);

    /** 统计流水总数 */
    int count();
}
