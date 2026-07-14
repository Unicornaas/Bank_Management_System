package edu.fjut.mybatis.controller;

import edu.fjut.mybatis.entity.Admin;
import edu.fjut.mybatis.entity.Employee;
import edu.fjut.mybatis.entity.User;
import edu.fjut.mybatis.entity.UserVip;
import edu.fjut.mybatis.service.AdminService;
import edu.fjut.mybatis.service.BussService;
import edu.fjut.mybatis.service.EmployeeService;
import edu.fjut.mybatis.service.UserService;
import edu.fjut.mybatis.service.UserVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BussService bussService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserVipService userVipService;

    /**
     * 登录接口
     * role=admin  → 查询 admin 表
     * role=manager → 查询 employee 表
     * role=user   → 查询 user 表
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username,
                                     @RequestParam String password,
                                     @RequestParam(defaultValue = "user") String role) {
        Map<String, Object> result = new HashMap<>();

        switch (role) {
            case "admin": {
                Admin admin = adminService.login(username, password);
                if (admin != null) {
                    admin.setPassword(null);
                    result.put("success", true);
                    result.put("role", "admin");
                    result.put("message", "管理员登录成功");
                    result.put("user", admin);
                } else {
                    result.put("success", false);
                    result.put("message", "管理员用户名或密码错误");
                }
                return result;
            }
            case "manager": {
                Employee employee = employeeService.login(username, password);
                if (employee != null) {
                    employee.setPassword(null);
                    Map<String, Object> empMap = new HashMap<>();
                    empMap.put("id", employee.getId());
                    empMap.put("username", employee.getUsername());
                    empMap.put("name", employee.getName());
                    empMap.put("position", employee.getPosition());
                    empMap.put("branchName", employee.getBranchName());
                    empMap.put("phone", employee.getPhone());
                    empMap.put("email", employee.getEmail());
                    result.put("success", true);
                    result.put("role", "manager");
                    result.put("message", "客户经理登录成功");
                    result.put("user", empMap);
                } else {
                    result.put("success", false);
                    result.put("message", "客户经理用户名或密码错误");
                }
                return result;
            }
            default: { // user
                User user = userService.login(username, password);
                if (user != null) {
                    user.setPassword(null);
                    // 查询VIP等级
                    UserVip vip = userVipService.getLevelByBalance(user.getMoney());
                    result.put("success", true);
                    result.put("role", user.getRole() != null ? user.getRole() : "user");
                    result.put("message", "储户登录成功");
                    result.put("user", user);
                    result.put("vip", vip);
                } else {
                    result.put("success", false);
                    result.put("message", "用户名或密码错误");
                }
                return result;
            }
        }
    }

    /** 获取所有用户列表 */
    @GetMapping("/users")
    public Map<String, Object> findAll() {
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.findAll();
        // 脱敏
        users.forEach(u -> u.setPassword(null));
        result.put("success", true);
        result.put("data", users);
        return result;
    }

    /** 根据ID查询单个用户 */
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user != null) user.setPassword(null);
        return user;
    }

    /** 新增用户 */
    @PostMapping("/users")
    public Map<String, Object> addUser(@RequestParam String username,
                                       @RequestParam String password,
                                       @RequestParam String name,
                                       @RequestParam(required = false, defaultValue = "") String gender,
                                       @RequestParam(required = false, defaultValue = "0") Integer age,
                                       @RequestParam(required = false, defaultValue = "") String card,
                                       @RequestParam(required = false, defaultValue = "") String phone,
                                       @RequestParam(required = false, defaultValue = "0") Double money,
                                       @RequestParam(required = false, defaultValue = "user") String role) {
        Map<String, Object> result = new HashMap<>();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setGender(gender);
        user.setAge(age);
        user.setCard(card);
        user.setPhone(phone);
        user.setMoney(money);
        user.setRole(role);

        int rows = userService.addUser(user);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "添加成功" : "添加失败");
        result.put("data", rows);
        return result;
    }

    /** 删除用户 */
    @DeleteMapping("/users/{id}")
    public Map<String, Object> deleteUser(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        int rows = userService.deleteUser(id);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "删除成功" : "删除失败");
        return result;
    }

    /** 修改用户信息 */
    @PutMapping("/users")
    public Map<String, Object> updateUser(@RequestParam Integer id,
                                          @RequestParam(required = false) String username,
                                          @RequestParam(required = false) String password,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) String gender,
                                          @RequestParam(required = false) Integer age,
                                          @RequestParam(required = false) String card,
                                          @RequestParam(required = false) String phone,
                                          @RequestParam(required = false) Double money,
                                          @RequestParam(required = false) String role) {
        Map<String, Object> result = new HashMap<>();
        User user = new User();
        user.setId(id);
        if (username != null && !username.isEmpty()) user.setUsername(username);
        if (password != null && !password.isEmpty()) user.setPassword(password);
        if (name != null && !name.isEmpty()) user.setName(name);
        if (gender != null && !gender.isEmpty()) user.setGender(gender);
        if (age != null) user.setAge(age);
        if (card != null && !card.isEmpty()) user.setCard(card);
        if (phone != null && !phone.isEmpty()) user.setPhone(phone);
        if (money != null) user.setMoney(money);
        if (role != null && !role.isEmpty()) user.setRole(role);

        int rows = userService.updateUser(user);
        result.put("success", rows > 0);
        result.put("message", rows > 0 ? "修改成功" : "修改失败");
        return result;
    }

    /** 条件搜索用户 */
    @PostMapping("/users/search")
    public Map<String, Object> search(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String gender,
                                      @RequestParam(required = false) Integer age,
                                      @RequestParam(required = false) Double money,
                                      @RequestParam(required = false) String username) {
        Map<String, Object> result = new HashMap<>();
        User query = new User();
        if (name != null && !name.isEmpty()) query.setName(name);
        if (gender != null && !gender.isEmpty()) query.setGender(gender);
        if (age != null && age > 0) query.setAge(age);
        if (money != null && money > 0) query.setMoney(money);
        if (username != null && !username.isEmpty()) query.setUsername(username);

        List<User> users = userService.search(query);
        users.forEach(u -> u.setPassword(null));
        result.put("success", true);
        result.put("data", users);
        return result;
    }

    /** 查询某用户的流水账单 */
    @GetMapping("/bills/{uid}")
    public Map<String, Object> getBills(@PathVariable Integer uid) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", bussService.findByUid(uid));
        return result;
    }

    /** 查询所有流水 */
    @GetMapping("/bills/all")
    public Map<String, Object> getAllBills() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", bussService.findAll());
        return result;
    }

    /** 查询流水总数 */
    @GetMapping("/bills/all-count")
    public Map<String, Object> getBillsCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("total", bussService.count());
        return result;
    }

    // ==================== 存款 ====================
    @PostMapping("/deposit")
    public Map<String, Object> deposit(@RequestParam Integer userId,
                                       @RequestParam Double amount) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.deposit(userId, amount);
        result.put("success", success);
        result.put("message", success ? "存款成功" : "存款失败，请检查用户ID和金额");
        return result;
    }

    // ==================== 取款 ====================
    @PostMapping("/withdraw")
    public Map<String, Object> withdraw(@RequestParam Integer userId,
                                        @RequestParam Double amount) {
        Map<String, Object> result = new HashMap<>();
        String err = userService.withdraw(userId, amount);
        result.put("success", err == null);
        result.put("message", err == null ? "取款成功" : err);
        return result;
    }

    // ==================== 转账 ====================
    @PostMapping("/transfer")
    public Map<String, Object> transfer(@RequestParam Integer fromId,
                                        @RequestParam String toCard,
                                        @RequestParam String toName,
                                        @RequestParam Double amount) {
        Map<String, Object> result = new HashMap<>();
        // 根据银行卡号查找收款方
        User toUser = userService.findByCard(toCard);
        if (toUser == null) {
            result.put("success", false);
            result.put("message", "收款方银行卡号不存在");
            return result;
        }
        // 验证收款方姓名是否匹配
        if (!toUser.getName().equals(toName)) {
            result.put("success", false);
            result.put("message", "收款方姓名与银行卡号不匹配");
            return result;
        }
        String err = userService.transfer(fromId, toUser.getId(), amount);
        result.put("success", err == null);
        result.put("message", err == null ? "转账成功" : err);
        return result;
    }

    // ==================== 修改密码 ====================
    @PostMapping("/changePassword")
    public Map<String, Object> changePassword(@RequestParam Integer userId,
                                              @RequestParam String oldPassword,
                                              @RequestParam String newPassword) {
        Map<String, Object> result = new HashMap<>();
        String err = userService.changePassword(userId, oldPassword, newPassword);
        result.put("success", err == null);
        result.put("message", err == null ? "密码修改成功" : err);
        return result;
    }

    // ==================== 分页查询 ====================
    @GetMapping("/users/page")
    public Map<String, Object> findByPage(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.findByPage(page, size);
        users.forEach(u -> u.setPassword(null));
        int total = userService.count();
        result.put("success", true);
        result.put("data", users);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        result.put("totalPages", (int) Math.ceil((double) total / size));
        return result;
    }

    // ==================== VIP 等级 ====================

    /** 获取所有VIP等级 */
    @GetMapping("/vip/levels")
    public Map<String, Object> getVipLevels() {
        Map<String, Object> result = new HashMap<>();
        List<UserVip> levels = userVipService.findAll();
        result.put("success", true);
        result.put("data", levels);
        return result;
    }

    /** 根据用户ID获取VIP等级 */
    @GetMapping("/vip/{userId}")
    public Map<String, Object> getUserVip(@PathVariable Integer userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.findById(userId);
        if (user == null) {
            result.put("success", false);
            result.put("message", "用户不存在");
            return result;
        }
        UserVip vip = userVipService.getLevelByBalance(user.getMoney());
        result.put("success", true);
        result.put("vip", vip);
        result.put("balance", user.getMoney());
        return result;
    }
}
