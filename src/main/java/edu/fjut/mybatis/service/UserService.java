package edu.fjut.mybatis.service;

import edu.fjut.mybatis.entity.User;
import edu.fjut.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BussService bussService;

    /** 查询所有用户 */
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /** 统计用户总数 */
    public int count() {
        return userMapper.count();
    }

    /** 分页查询 */
    public List<User> findByPage(int page, int size) {
        int offset = (page - 1) * size;
        return userMapper.findByPage(offset, size);
    }

    /** 储户登录验证 */
    public User login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.findByUsernameAndPassword(user);
    }

    /** 新增用户 */
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    /** 删除用户 */
    public int deleteUser(Integer id) {
        return userMapper.deleteById(id);
    }

    /** 根据ID查询 */
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    /** 更新用户信息 */
    public int updateUser(User user) {
        return userMapper.update(user);
    }

    /** 根据用户名查询 */
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /** 根据银行卡号查询 */
    public User findByCard(String card) {
        return userMapper.findByCard(card);
    }

    /** 条件搜索 */
    public List<User> search(User user) {
        return userMapper.search(user);
    }

    /** 存款 */
    @Transactional
    public boolean deposit(Integer userId, Double amount) {
        User user = userMapper.findById(userId);
        if (user == null || amount <= 0) return false;
        double newMoney = user.getMoney() + amount;
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setMoney(newMoney);
        userMapper.updateMoney(updateUser);
        bussService.addBuss(userId, "存款", amount);
        return true;
    }

    /** 取款 */
    @Transactional
    public String withdraw(Integer userId, Double amount) {
        User user = userMapper.findById(userId);
        if (user == null) return "用户不存在";
        if (amount <= 0) return "金额必须大于0";
        if (user.getMoney() < amount) return "余额不足";
        double newMoney = user.getMoney() - amount;
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setMoney(newMoney);
        userMapper.updateMoney(updateUser);
        bussService.addBuss(userId, "取款", amount);
        return null; // null 表示成功
    }

    /** 转账 */
    @Transactional
    public String transfer(Integer fromId, Integer toId, Double amount) {
        if (fromId.equals(toId)) return "不能转账给自己";
        if (amount <= 0) return "金额必须大于0";
        User fromUser = userMapper.findById(fromId);
        User toUser = userMapper.findById(toId);
        if (fromUser == null) return "转出账户不存在";
        if (toUser == null) return "转入账户不存在";
        if (fromUser.getMoney() < amount) return "余额不足";

        // 扣款
        User updateFrom = new User();
        updateFrom.setId(fromId);
        updateFrom.setMoney(fromUser.getMoney() - amount);
        userMapper.updateMoney(updateFrom);
        bussService.addBuss(fromId, "转账-转出", amount);

        // 入账
        User updateTo = new User();
        updateTo.setId(toId);
        updateTo.setMoney(toUser.getMoney() + amount);
        userMapper.updateMoney(updateTo);
        bussService.addBuss(toId, "转账-转入", amount);

        return null; // null 表示成功
    }

    /** 修改密码 */
    public String changePassword(Integer userId, String oldPassword, String newPassword) {
        if (newPassword == null || newPassword.length() < 6) return "新密码至少6位";
        User user = userMapper.findById(userId);
        if (user == null) return "用户不存在";
        if (!user.getPassword().equals(oldPassword)) return "原密码错误";
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setPassword(newPassword);
        userMapper.updatePassword(updateUser);
        return null; // null 表示成功
    }
}
