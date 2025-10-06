# SudokuUser CRUD API 说明文档

## 概述
基于`sudoku_users`表结构生成的完整MyBatis CRUD接口，包含数据访问层、业务服务层、控制器层和测试类。

## 表结构说明
```sql
CREATE TABLE `sudoku_users` (
    `id` BIGINT(19) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `open_id` VARCHAR(36) NOT NULL COMMENT '账号',
    `user_name` VARCHAR(30) NULL DEFAULT '' COMMENT '用户昵称',
    `platform` VARCHAR(30) NULL DEFAULT '' COMMENT '平台',
    `login_ip` VARCHAR(128) NULL DEFAULT '' COMMENT '最后登录IP',
    `login_date` DATETIME NULL DEFAULT NULL COMMENT '最后登录时间',
    `login_times` INT(10) NULL DEFAULT '0' COMMENT '登录次数',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
);
```

## 项目结构

### 1. 数据传输对象 (DTO)
- **[SudokuUser.java](file://e:\Hodoku\src\main\java\com\hodoku\dto\SudokuUser.java)**: 用户实体类，包含所有表字段对应的属性

### 2. 数据访问层 (Mapper)
- **[SudokuUserMapper.java](file://e:\Hodoku\src\main\java\com\hodoku\mapper\SudokuUserMapper.java)**: MyBatis映射接口，定义CRUD方法
- **[SudokuUserMapper.xml](file://e:\Hodoku\src\main\resources\mapper\SudokuUserMapper.xml)**: MyBatis SQL映射文件

### 3. 业务服务层 (Service)
- **[SudokuUserService.java](file://e:\Hodoku\src\main\java\com\hodoku\service\SudokuUserService.java)**: 用户业务逻辑服务类

### 4. 控制器层 (Controller)
- **[SudokuUserController.java](file://e:\Hodoku\src\main\java\com\hodoku\controller\SudokuUserController.java)**: REST API控制器

### 5. 测试类
- **[SudokuUserServiceTest.java](file://e:\Hodoku\src\test\java\com\hodoku\service\SudokuUserServiceTest.java)**: 完整的单元测试

## 主要功能

### Mapper层功能
1. **查询功能**
   - `findById`: 根据ID查询用户
   - `findByOpenId`: 根据openId查询用户
   - `findByPlatform`: 根据平台查询用户列表
   - `findByUserNameLike`: 用户名模糊查询
   - `findAll`: 查询所有用户
   - `findByPage`: 分页查询
   - `countAll`: 统计总用户数
   - `countByPlatform`: 按平台统计用户数

2. **新增功能**
   - `insert`: 新增单个用户
   - `insertBatch`: 批量新增用户

3. **更新功能**
   - `update`: 更新用户信息
   - `updateSelective`: 选择性更新（只更新非空字段）
   - `updateLoginInfo`: 更新登录信息
   - `incrementLoginTimes`: 增加登录次数

4. **删除功能**
   - `deleteById`: 根据ID删除
   - `deleteByOpenId`: 根据openId删除
   - `deleteBatch`: 批量删除
   - `deleteByPlatform`: 根据平台删除

### Service层功能
提供高级业务方法，包括：
- 用户注册和登录逻辑
- 事务管理
- 业务规则验证
- 数据统计分析

### REST API接口

#### 用户查询
- `GET /api/users/{id}` - 根据ID查询用户
- `GET /api/users/openid/{openId}` - 根据openId查询用户
- `GET /api/users/platform/{platform}` - 根据平台查询用户
- `GET /api/users/search?userName={name}` - 用户名模糊搜索
- `GET /api/users?page={page}&size={size}` - 分页查询用户

#### 用户操作
- `POST /api/users` - 创建新用户
- `PUT /api/users/{id}` - 更新用户信息
- `POST /api/users/login` - 用户登录
- `DELETE /api/users/{id}` - 删除用户
- `DELETE /api/users/openid/{openId}` - 根据openId删除用户

#### 统计信息
- `GET /api/users/stats/platform` - 获取平台用户统计

## 使用示例

### 1. 创建用户
```java
@Autowired
private SudokuUserService userService;

// 创建新用户
SudokuUser user = userService.createUser("wx_123456", "张三", "微信小程序");
```

### 2. 用户登录
```java
// 用户登录（不存在则注册，存在则更新登录信息）
SudokuUser user = userService.registerOrLogin(
    "wx_123456", "张三", "微信小程序", "192.168.1.100"
);
```

### 3. 查询用户
```java
// 根据openId查询
SudokuUser user = userService.getUserByOpenId("wx_123456");

// 分页查询
List<SudokuUser> users = userService.getUsersByPage(1, 10);

// 模糊搜索
List<SudokuUser> searchResults = userService.searchUsersByName("张");
```

### 4. 更新用户
```java
SudokuUser user = userService.getUserByOpenId("wx_123456");
user.setUserName("新用户名");
userService.updateUserSelective(user);
```

### 5. REST API调用示例
```bash
# 创建用户
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"openId":"wx_123456","userName":"张三","platform":"微信小程序"}'

# 用户登录
curl -X POST http://localhost:8080/api/users/login \
  -H "Content-Type: application/json" \
  -d '{"openId":"wx_123456","userName":"张三","platform":"微信小程序","loginIp":"192.168.1.100"}'

# 查询用户
curl http://localhost:8080/api/users/openid/wx_123456

# 分页查询
curl "http://localhost:8080/api/users?page=1&size=10"

# 搜索用户
curl "http://localhost:8080/api/users/search?userName=张"
```

## 特性说明

### 1. 事务支持
所有写操作（增删改）都添加了`@Transactional`注解，确保数据一致性。

### 2. 智能登录逻辑
`registerOrLogin`方法实现了智能用户注册/登录逻辑：
- 如果用户不存在，自动创建新用户
- 如果用户存在，更新登录信息（IP、时间、次数）

### 3. 灵活的查询方式
- 支持精确查询、模糊查询
- 支持分页查询
- 支持按多个维度统计

### 4. 批量操作
- 支持批量创建用户
- 支持批量删除用户

### 5. 完整的测试覆盖
提供了全面的单元测试，覆盖所有主要功能场景。

## 注意事项

1. **数据库连接**: 确保MySQL数据库连接配置正确
2. **唯一性约束**: openId应该保证唯一性，建议在数据库层面添加唯一索引
3. **参数验证**: 在实际使用中应添加更严格的参数校验
4. **安全性**: 生产环境中应添加权限验证和安全控制
5. **性能优化**: 对于大数据量查询，建议添加适当的数据库索引

## 扩展建议

1. 添加用户角色权限管理
2. 实现用户头像上传功能
3. 添加用户活跃度统计
4. 实现用户标签系统
5. 添加用户行为日志记录