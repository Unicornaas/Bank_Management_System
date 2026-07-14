package edu.fjut.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 应用启动时自动初始化数据库表结构和初始数据
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            // ========== 1. 创建网点表 ==========
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS branch (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  name VARCHAR(100) NOT NULL," +
                "  address VARCHAR(200)," +
                "  phone VARCHAR(20)," +
                "  manager_name VARCHAR(50)," +
                "  description TEXT," +
                "  status VARCHAR(20) DEFAULT '营业中'," +
                "  create_time DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            );

            // ========== 2. 创建员工表 ==========
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS employee (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  username VARCHAR(50) NOT NULL UNIQUE," +
                "  password VARCHAR(100) NOT NULL," +
                "  name VARCHAR(50) NOT NULL," +
                "  gender VARCHAR(10)," +
                "  age INT," +
                "  phone VARCHAR(20)," +
                "  email VARCHAR(100)," +
                "  position VARCHAR(50)," +
                "  salary DECIMAL(12,2)," +
                "  branch_id INT," +
                "  status VARCHAR(20) DEFAULT '在职'," +
                "  create_time DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            );

            // ========== 3. 创建用户(储户)表 ==========
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS user (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  username VARCHAR(50) NOT NULL UNIQUE," +
                "  password VARCHAR(100) NOT NULL," +
                "  name VARCHAR(50) NOT NULL," +
                "  gender VARCHAR(10)," +
                "  age INT," +
                "  card VARCHAR(30)," +
                "  phone VARCHAR(20)," +
                "  money DOUBLE DEFAULT 0," +
                "  role VARCHAR(20) DEFAULT 'user'" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            );

            // 兼容旧数据库：如果 user 表已存在但没有 role 列，则添加
            try {
                stmt.executeUpdate("ALTER TABLE user ADD COLUMN role VARCHAR(20) DEFAULT 'user'");
                System.out.println("[DataInitializer] 已为 user 表添加 role 列");
            } catch (Exception ignored) {
                // role 列已存在，忽略
            }

            // ========== 4. 创建交易流水表 ==========
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS bussioness (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  uid INT NOT NULL," +
                "  type VARCHAR(50)," +
                "  amount DOUBLE," +
                "  create_time DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            );

            // ========== 5. 创建角色表 ==========
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS role (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  role_name VARCHAR(50) NOT NULL," +
                "  permissions TEXT" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            );

            // ========== 6. 创建管理员表 ==========
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS admin (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  username VARCHAR(50) NOT NULL UNIQUE," +
                "  password VARCHAR(100) NOT NULL," +
                "  name VARCHAR(50) NOT NULL," +
                "  phone VARCHAR(20)," +
                "  email VARCHAR(100)," +
                "  status VARCHAR(20) DEFAULT '在职'," +
                "  create_time DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            );

            // ========== 7. 创建VIP等级表 ==========
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS user_vip (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  level_name VARCHAR(50) NOT NULL," +
                "  level_code VARCHAR(20) NOT NULL UNIQUE," +
                "  min_balance DOUBLE DEFAULT 0," +
                "  discount_rate DOUBLE DEFAULT 1.0 COMMENT '手续费折扣'," +
                "  color VARCHAR(30) COMMENT '展示颜色'," +
                "  icon VARCHAR(10) COMMENT '图标'," +
                "  benefits TEXT COMMENT '权益描述'," +
                "  sort_order INT DEFAULT 0" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='VIP等级定义表'"
            );

            System.out.println("[DataInitializer] 表结构初始化完成");

            // ========== 8. 插入网点初始数据（仅当表为空时） ==========
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM branch");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate(
                    "INSERT INTO branch (name, address, phone, manager_name, description, status) VALUES " +
                    "('华瑞银行总行营业部', '福建省福州市鼓楼区五四路128号', '0591-87888888', '陈志远', '总行直属营业网点，提供全方位金融服务', '营业中')," +
                    "('华瑞银行仓山支行', '福建省福州市仓山区闽江大道168号', '0591-83561234', '林明辉', '仓山区主要服务网点', '营业中')," +
                    "('华瑞银行晋安支行', '福建省福州市晋安区长乐北路99号', '0591-87324567', '黄丽华', '晋安区综合金融服务网点', '营业中')"
                );
                System.out.println("[DataInitializer] 网点初始数据插入完成 (3条)");
            } else {
                System.out.println("[DataInitializer] 网点表已有数据，跳过插入");
            }
            rs.close();

            // ========== 9. 插入员工初始数据（仅当表为空时） ==========
            rs = stmt.executeQuery("SELECT COUNT(*) FROM employee");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate(
                    "INSERT INTO employee (username, password, name, gender, age, phone, email, position, salary, branch_id, status) VALUES " +
                    "('e001',     '123456', '张三', '男', 32, '13800138001', 'zhangsan@hrbank.com', '柜员', 8000.00, 1, '在职')," +
                    "('e002',     '123456', '李四', '女', 28, '13800138002', 'lisi@hrbank.com',     '客户经理', 12000.00, 1, '在职')," +
                    "('e003',     '123456', '王五', '男', 35, '13800138003', 'wangwu@hrbank.com',   '支行行长', 20000.00, 2, '在职')," +
                    "('e004',     '123456', '赵六', '女', 26, '13800138004', 'zhaoliu@hrbank.com',  '大堂经理', 9000.00, 2, '在职')," +
                    "('e005',     '123456', '孙七', '男', 30, '13800138005', 'sunqi@hrbank.com',    '柜员', 7500.00, 3, '在职')," +
                    "('e006',     '123456', '周八', '女', 29, '13800138006', 'zhouba@hrbank.com',   '理财顾问', 11000.00, 3, '在职')"
                );
                System.out.println("[DataInitializer] 员工初始数据插入完成 (6条)");
            } else {
                System.out.println("[DataInitializer] 员工表已有数据，跳过插入");
            }
            rs.close();

            // ========== 10. 插入用户(储户)初始数据（仅当表为空时） ==========
            rs = stmt.executeQuery("SELECT COUNT(*) FROM user");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate(
                    "INSERT INTO user (username, password, name, gender, age, card, phone, money, role) VALUES " +
                    "('zhangsan', '123456', '张三', '男', 32, '350102199201010011', '13811110001', 50000.00, 'user')," +
                    "('lisi',     '123456', '李四', '女', 28, '350102199503020022', '13811110002', 80000.00, 'user')," +
                    "('wangwu',   '123456', '王五', '男', 45, '350102197808090033', '13811110003', 150000.00, 'user')," +
                    "('zhaoliu',  '123456', '赵六', '女', 35, '350102198812010044', '13811110004', 32000.00, 'user')," +
                    "('sunqi',    '123456', '孙七', '男', 52, '350102197103150055', '13811110005', 200000.00, 'user')," +
                    "('zhouba',   '123456', '周八', '女', 26, '350102199907200066', '13811110006', 12000.00, 'user')," +
                    "('wujiu',    '123456', '吴九', '男', 38, '350102198505120077', '13811110007', 95000.00, 'user')," +
                    "('zhengshi', '123456', '郑十', '女', 41, '350102198211280088', '13811110008', 68000.00, 'user')," +
                    "('u101',     '123456', '刘小明', '男', 27, '350102199904150101', '13911110001', 25000.00, 'user')," +
                    "('u102',     '123456', '陈小红', '女', 31, '350102199506220102', '13911110002', 45000.00, 'user')," +
                    "('manager1', '123456', '陈经理', '男', 36, '350102198804150099', '13811110009', 0.00, 'manager')," +
                    "('manager2', '123456', '林经理', '女', 33, '350102199106220100', '13811110010', 0.00, 'manager')"
                );
                System.out.println("[DataInitializer] 用户初始数据插入完成 (12条)");
            } else {
                System.out.println("[DataInitializer] 用户表已有数据，跳过插入");
            }
            rs.close();

            // ========== 11. 插入角色初始数据（仅当表为空时） ==========
            rs = stmt.executeQuery("SELECT COUNT(*) FROM role");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate(
                    "INSERT INTO role (role_name, permissions) VALUES " +
                    "('管理员', '系统所有权限：用户管理、员工管理、网点管理、账户管理、交易管理、角色管理、数据仪表盘')," +
                    "('客户经理', '业务管理权限：客户管理、员工管理、网点管理、数据仪表盘')," +
                    "('储户', '个人账户权限：存款、取款、转账、账户查询、修改密码')"
                );
                System.out.println("[DataInitializer] 角色初始数据插入完成 (3条)");
            } else {
                System.out.println("[DataInitializer] 角色表已有数据，跳过插入");
            }
            rs.close();

            // ========== 12. 插入管理员初始数据（仅当表为空时） ==========
            rs = stmt.executeQuery("SELECT COUNT(*) FROM admin");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate(
                    "INSERT INTO admin (username, password, name, phone, email, status) VALUES " +
                    "('admin', 'admin123', '系统管理员', '13800000000', 'admin@hrbank.com', '在职')," +
                    "('admin2', 'admin123', '张主管', '13800000001', 'zhang@hrbank.com', '在职')"
                );
                System.out.println("[DataInitializer] 管理员初始数据插入完成 (2条)");
            } else {
                System.out.println("[DataInitializer] 管理员表已有数据，跳过插入");
            }
            rs.close();

            // ========== 13. 插入VIP等级初始数据（仅当表为空时） ==========
            rs = stmt.executeQuery("SELECT COUNT(*) FROM user_vip");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate(
                    "INSERT INTO user_vip (level_name, level_code, min_balance, discount_rate, color, icon, benefits, sort_order) VALUES " +
                    "('普通会员', 'normal', 0, 1.00, '#94a3b8', '👤', '基础金融服务；在线查询余额；小额存取款', 1)," +
                    "('VIP铜卡会员', 'copper', 10000, 0.98, '#d97706', '🥉', '享受9.8折手续费优惠；优先客服通道；生日祝福', 2)," +
                    "('VIP银卡会员', 'silver', 50000, 0.95, '#64748b', '🥈', '享受9.5折手续费优惠；专属客户经理；免费账户提醒', 3)," +
                    "('VIP金卡会员', 'gold', 100000, 0.90, '#f59e0b', '🥇', '享受9折手续费优惠；VIP专属柜台；理财顾问服务；免费跨行转账', 4)"
                );
                System.out.println("[DataInitializer] VIP等级初始数据插入完成 (4条)");
            } else {
                System.out.println("[DataInitializer] VIP等级表已有数据，跳过插入");
            }
            rs.close();

            System.out.println("[DataInitializer] 全部初始化完成");

        } catch (Exception e) {
            System.err.println("[DataInitializer] 初始化异常(可忽略): " + e.getMessage());
        }
    }
}
