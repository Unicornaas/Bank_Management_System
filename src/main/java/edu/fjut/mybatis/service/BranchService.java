package edu.fjut.mybatis.service;

import edu.fjut.mybatis.entity.Branch;
import edu.fjut.mybatis.mapper.BranchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchMapper branchMapper;

    public List<Branch> findAll() {
        return branchMapper.findAll();
    }

    public int count() {
        return branchMapper.count();
    }

    public List<Branch> findByPage(int page, int size) {
        int offset = (page - 1) * size;
        return branchMapper.findByPage(offset, size);
    }

    public Branch findById(Integer id) {
        return branchMapper.findById(id);
    }

    public int addBranch(Branch branch) {
        return branchMapper.insert(branch);
    }

    public int updateBranch(Branch branch) {
        return branchMapper.update(branch);
    }

    public int deleteBranch(Integer id) {
        return branchMapper.deleteById(id);
    }

    public List<Branch> search(Branch branch) {
        return branchMapper.search(branch);
    }
}
