package edu.fjut.mybatis.mapper;

import edu.fjut.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /** 查询所有用户 */
    List<User> findAll();

    /** 统计用户总数 */
    int count();

    /** 根据用户名和密码查询用户（储户登录） */
    User findByUsernameAndPassword(User user);

    /** 根据用户名查询用户 */
    User findByUsername(String username);

    /** 根据银行卡号查询用户 */
    User findByCard(String card);

    /** 新增用户 */
    int insert(User user);

    /** 根据ID删除用户 */
    int deleteById(Integer id);

    /** 根据ID查询用户 */
    User findById(Integer id);

    /** 动态更新用户信息 */
    int update(User user);

    /** 条件搜索用户（动态SQL） */
    List<User> search(User user);

    /** 更新用户余额（存款/取款/转账） */
    int updateMoney(User user);

    /** 修改密码 */
    int updatePassword(User user);

    /** 分页查询 */
    List<User> findByPage(@Param("offset") int offset, @Param("size") int size);
}
