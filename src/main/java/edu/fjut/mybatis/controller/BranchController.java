package edu.fjut.mybatis.controller;

import edu.fjut.mybatis.entity.Branch;
import edu.fjut.mybatis.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BranchController {

    @Autowired
    private BranchService branchService;

    /** 获取所有网点 */
    @GetMapping("/branches")
    public Map<String, Object> findAll() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", branchService.findAll());
        return result;
    }

    /** 分页查询 */
    @GetMapping("/branches/page")
    public Map<String, Object> findByPage(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> result = new HashMap<>();
        List<Branch> branches = branchService.findByPage(page, size);
        int total = branchService.count();
        result.put("success", true);
        result.put("data", branches);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        result.put("totalPages", (int) Math.ceil((double) total / size));
        return result;
    }

    /** 根据ID查询 */
    @GetMapping("/branches/{id}")
    public Branch getById(@PathVariable Integer id) {
        return branchService.findById(id);
    }

    /** 新增网点 */
    @PostMapping("/branches")
    public Map<String, Object> add(@RequestParam String name,
                                   @RequestParam(required = false, defaultValue = "") String address,
                                   @RequestParam(required = false, defaultValue = "") String phone,
                                   @RequestParam(required = false, defaultValue = "") String managerName,
                                   @RequestParam(required = false, defaultValue = "") String description,
                                   @RequestParam(required = false, defaultValue = "营业中") String status) {
        Map<String, Object> result = new HashMap<>();
        Branch branch = new Branch();
        branch.setName(name);
        branch.setAddress(address);
        branch.setPhone(phone);
        branch.setManagerName(managerName);
        branch.setDescription(description);
        branch.setStatus(status);

        int rows = branchService.addBranch(branch);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "添加成功" : "添加失败");
        return result;
    }

    /** 修改网点 */
    @PutMapping("/branches")
    public Map<String, Object> update(@RequestParam Integer id,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String address,
                                      @RequestParam(required = false) String phone,
                                      @RequestParam(required = false) String managerName,
                                      @RequestParam(required = false) String description,
                                      @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Branch branch = new Branch();
        branch.setId(id);
        if (name != null && !name.isEmpty()) branch.setName(name);
        if (address != null && !address.isEmpty()) branch.setAddress(address);
        if (phone != null && !phone.isEmpty()) branch.setPhone(phone);
        if (managerName != null && !managerName.isEmpty()) branch.setManagerName(managerName);
        if (description != null && !description.isEmpty()) branch.setDescription(description);
        if (status != null && !status.isEmpty()) branch.setStatus(status);

        int rows = branchService.updateBranch(branch);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "修改成功" : "修改失败");
        return result;
    }

    /** 删除网点 */
    @DeleteMapping("/branches/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        int rows = branchService.deleteBranch(id);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "删除成功" : "删除失败");
        return result;
    }

    /** 搜索网点 */
    @PostMapping("/branches/search")
    public Map<String, Object> search(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String address,
                                      @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Branch query = new Branch();
        if (name != null && !name.isEmpty()) query.setName(name);
        if (address != null && !address.isEmpty()) query.setAddress(address);
        if (status != null && !status.isEmpty()) query.setStatus(status);

        result.put("success", true);
        result.put("data", branchService.search(query));
        return result;
    }
}
