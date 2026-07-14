package edu.fjut.mybatis.mapper;

import edu.fjut.mybatis.entity.Branch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BranchMapper {

    List<Branch> findAll();

    int count();

    Branch findById(Integer id);

    int insert(Branch branch);

    int update(Branch branch);

    int deleteById(Integer id);

    List<Branch> search(Branch branch);

    List<Branch> findByPage(@Param("offset") int offset, @Param("size") int size);
}
