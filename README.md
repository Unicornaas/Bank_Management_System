# 🏦 华瑞银行管理系统 (Huarui Bank Management System)

基于 **Spring Boot 3** + **MyBatis** + **MySQL** 的全栈银行管理系统，支持多角色（管理员、银行员工、储户）登录，涵盖网点管理、员工管理、客户管理、交易（存取款/转账/流水）、VIP 等级体系和数据仪表盘等功能。

---

## 📋 目录

- [技术栈](#-技术栈)
- [功能概览](#-功能概览)
- [项目结构](#-项目结构)
- [数据库设计](#-数据库设计)
- [快速开始](#-快速开始)
- [API 接口](#-api-接口)
- [默认账号](#-默认账号)
- [注意事项](#-注意事项)

---

## 🛠 技术栈

| 层级 | 技术 | 版本 |
|------|------|------|
| **构建工具** | Maven (含 Maven Wrapper) | — |
| **语言** | Java | 17 |
| **后端框架** | Spring Boot | 3.4.1 |
| **ORM** | MyBatis (mybatis-spring-boot-starter) | 3.0.4 |
| **数据库** | MySQL | 8.0+ |
| **前端** | HTML + CSS + JavaScript (原生) | — |
| **图表** | ECharts (CDN) | 5.5.0 |
| **字体** | Google Fonts — Inter | — |

---

## ✨ 功能概览

### 🔐 多角色登录认证
- **管理员 (admin)** — 系统管理、角色管理
- **银行员工/经理 (manager)** — 网点管理、员工管理、账户管理
- **储户/客户 (user)** — 查看余额、存取款、转账、改密

### 🏢 网点管理 (Branch)
- 网点 CRUD，支持分页和动态搜索（按名称、地址、状态）

### 👨‍💼 员工管理 (Employee)
- 员工 CRUD，支持分页和动态搜索
- 关联所属网点（LEFT JOIN 显示网点名称）

### 👤 客户/储户管理 (User)
- 客户 CRUD，支持分页和动态搜索
- 银行卡号、余额等核心信息管理

### 💰 金融交易 (Transaction)
| 操作 | 说明 |
|------|------|
| 💵 **存款** | 更新余额 + 记录流水 |
| 🏧 **取款** | 校验余额 → 扣款 + 记录流水 |
| 🔄 **转账** | 校验收款人（卡号+姓名）→ 扣款/入账 + 双录流水 |

> 所有交易均使用 `@Transactional` 保证数据原子性。

### 🧾 交易流水 (Ledger)
- 记录每笔交易（用户ID、类型、金额、时间）
- 支持按用户查询或全部查询

### 👑 VIP 等级体系
| 等级 | 最低余额 | 折扣率 |
|------|----------|--------|
| 普通 | ¥0 | 100% |
| 铜牌 | ¥10,000 | 95% |
| 银牌 | ¥50,000 | 90% |
| 金牌 | ¥100,000 | 85% |

- 自动根据用户当前余额匹配最高适用等级

### 📊 数据仪表盘 (Dashboard)
- 汇总统计：用户数、员工数、网点数、总存款、总交易笔数、今日交易额
- 7天交易趋势图
- VIP 等级存款分布饼图
- 各等级存款进度条

### 🎭 角色管理 (Role)
- 系统角色的简单 CRUD

---

## 📁 项目结构

```
Mybatis/
├── pom.xml                                  # Maven 构建配置
├── mvnw / mvnw.cmd                          # Maven Wrapper
├── bank.sql                                 # 数据库完整导出（含种子数据）
├── README.md
└── src/
    ├── main/
    │   ├── java/edu/fjut/mybatis/
    │   │   ├── MybatisApplication.java      # Spring Boot 入口 + @MapperScan
    │   │   ├── DataInitializer.java         # 启动时自动建表 & 初始化数据
    │   │   ├── entity/                      # 实体类
    │   │   │   ├── Branch.java              #   银行网点
    │   │   │   ├── Employee.java            #   员工
    │   │   │   ├── User.java                #   储户
    │   │   │   ├── Buss.java                #   交易流水
    │   │   │   ├── Role.java                #   角色
    │   │   │   ├── Admin.java               #   管理员
    │   │   │   └── UserVip.java             #   VIP等级
    │   │   ├── mapper/                      # MyBatis Mapper 接口
    │   │   ├── service/                     # 业务逻辑层
    │   │   └── controller/                  # REST API 控制器
    │   └── resources/
    │       ├── application.properties       # 数据库连接 & MyBatis 配置
    │       ├── mapper/                      # MyBatis XML 映射文件
    │       └── static/                      # 前端静态页面
    │           ├── login.html               #   登录页
    │           ├── dashboard.html           #   数据仪表盘
    │           ├── admin.html               #   管理后台（含侧边栏导航）
    │           ├── branch_manage.html        #   网点管理
    │           ├── employee_manage.html      #   员工管理
    │           ├── user.html                #   用户管理
    │           ├── customer_manage.html      #   客户管理
    │           ├── account_manage.html       #   账户管理
    │           ├── transaction_manage.html   #   交易管理
    │           └── role_manage.html          #   角色管理
    └── test/java/edu/fjut/mybatis/
        └── MybatisApplicationTests.java     # 测试类
```

---

## 🗄 数据库设计

**数据库名:** `bank` | **字符集:** `utf8mb4` | **共 7 张表**

| 表名 | 说明 | 核心字段 |
|------|------|----------|
| `branch` | 银行网点 | id, name, address, phone, manager_name, status |
| `employee` | 员工 | id, username, password, position, salary, branch_id (FK) |
| `user` | 储户 | id, username, password, card (银行卡), money (余额) |
| `bussioness` | 交易流水 | id, uid (FK), type, amount, create_time |
| `admin` | 系统管理员 | id, username, password, name, email |
| `role` | 系统角色 | id, role_name, permissions |
| `user_vip` | VIP等级定义 | id, level_name, min_balance, discount_rate, color, benefits |

---

## 🚀 快速开始

### 环境要求
- **JDK** 17+
- **MySQL** 8.0+ (运行在 `localhost:3306`)
- **Maven** (或使用项目自带的 Maven Wrapper，无需安装)

### 1. 克隆项目
```bash
git clone <仓库地址>
cd Mybatis
```

### 2. 创建数据库
登录 MySQL 并执行：
```sql
CREATE DATABASE bank CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
```

> 可选：直接导入 `bank.sql` 获得完整种子数据：
> ```bash
> mysql -u root -p bank < bank.sql
> ```

### 3. 配置数据库连接
编辑 `src/main/resources/application.properties`，修改数据库用户名和密码：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8
spring.datasource.username=root        # ← 修改为你的用户名
spring.datasource.password=123456      # ← 修改为你的密码
```

### 4. 启动项目
```bash
# 方式一：使用 Maven Wrapper（推荐，无需安装 Maven）
./mvnw spring-boot:run

# 方式二：打包运行
./mvnw package
java -jar target/Mybatis-0.0.1-SNAPSHOT.jar
```

### 5. 访问系统
打开浏览器访问：**http://localhost:8080**

> 首次启动时，`DataInitializer` 会自动创建所有表并插入初始数据。

---

## 🔌 API 接口

> 所有接口前缀: `/api`

### 认证
| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/api/login` | 登录 (`username`, `password`, `role=admin\|manager\|user`) |

### 数据仪表盘
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/dashboard/stats` | 汇总统计 |
| GET | `/api/dashboard/trend` | 7天交易趋势 |
| GET | `/api/dashboard/depositTypes` | VIP 等级存款分布 |
| GET | `/api/dashboard/depositProgress` | 存款进度条 |

### 网点管理 `/api/branches`
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/branches` | 全部网点 |
| GET | `/api/branches/page` | 分页网点 |
| GET | `/api/branches/{id}` | 单个网点 |
| POST | `/api/branches` | 新增网点 |
| PUT | `/api/branches` | 更新网点 |
| DELETE | `/api/branches/{id}` | 删除网点 |
| POST | `/api/branches/search` | 搜索网点 |

### 员工管理 `/api/employees`
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/employees` | 全部员工 |
| GET | `/api/employees/page` | 分页员工 |
| GET | `/api/employees/{id}` | 单个员工 |
| POST | `/api/employees` | 新增员工 |
| PUT | `/api/employees` | 更新员工 |
| DELETE | `/api/employees/{id}` | 删除员工 |
| POST | `/api/employees/search` | 搜索员工 |

### 客户管理 `/api/users`
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/users` | 全部客户 |
| GET | `/api/users/page` | 分页客户 |
| GET | `/api/users/{id}` | 单个客户 |
| POST | `/api/users` | 新增客户 |
| PUT | `/api/users` | 更新客户 |
| DELETE | `/api/users/{id}` | 删除客户 |
| POST | `/api/users/search` | 搜索客户 |

### 金融交易
| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/api/deposit` | 存款 (`userId`, `amount`) |
| POST | `/api/withdraw` | 取款 (`userId`, `amount`) |
| POST | `/api/transfer` | 转账 (`fromId`, `toCard`, `toName`, `amount`) |
| POST | `/api/changePassword` | 改密 (`userId`, `oldPassword`, `newPassword`) |

### 交易流水
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/bills/{uid}` | 按用户查询流水 |
| GET | `/api/bills/all` | 全部流水 |
| GET | `/api/bills/all-count` | 流水总数 |

### VIP 等级
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/vip/levels` | 全部 VIP 等级 |
| GET | `/api/vip/{userId}` | 用户 VIP 等级 |

### 角色管理 `/api/roles`
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/roles` | 全部角色 |
| GET | `/api/roles/{id}` | 单个角色 |
| POST | `/api/roles` | 新增角色 |
| PUT | `/api/roles` | 更新角色 |
| DELETE | `/api/roles/{id}` | 删除角色 |

---

## 🔑 默认账号

> 项目首次启动后自动创建（在 `application.properties` 中配置管理员账号）：

| 角色 | 用户名 | 密码 | 说明 |
|------|--------|------|------|
| 🔴 管理员 | `admin` | `admin123` | 系统管理员，可管理所有模块 |
| 🔵 员工 | `emp001` | `123456` | 银行员工示例账号 |
| 🟢 储户 | `user001` | `123456` | 客户示例账号 |

---

## ⚠️ 注意事项

1. **密码安全** — 当前密码以明文存储，生产环境建议使用 BCrypt 等哈希算法加密。
2. **金额精度** — `user` 表余额字段使用 `DOUBLE` 类型，金融场景建议改用 `DECIMAL` / `BigDecimal` 以避免浮点精度问题。
3. **认证安全** — 未使用 Spring Security，认证为自定义实现，不适合直接用于生产环境。
4. **CORS** — 未配置跨域；前端作为静态资源由同一 Spring Boot 实例提供服务，因此不影响正常使用。
5. **前端** — 使用原生 HTML/CSS/JS 编写，未使用 Vue/React/Angular 等框架。
6. **编码** — `application.properties` 中部分中文注释可能存在编码问题，不影响功能。
