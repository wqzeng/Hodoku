# MySQL数据库和MyBatis集成说明

## 概述
本次更新为Hodoku项目添加了MySQL数据库连接能力和MyBatis组件，以支持数独题目的持久化存储。

## 新增组件

### 1. 依赖配置 (pom.xml)
- **mysql-connector-java**: MySQL数据库驱动
- **spring-boot-starter-data-jpa**: Spring Data JPA支持
- **mybatis-spring-boot-starter**: MyBatis Spring Boot集成

### 2. 数据库配置 (application.properties)
- 数据库连接地址: `10.9.4.70:3306`
- 数据库名: `sudoku`
- 用户名/密码: `sudoku/123456`
- MyBatis映射文件路径: `classpath:mapper/*.xml`

### 3. 新增文件结构
```
src/main/java/com/hodoku/
├── config/
│   └── DatabaseConfig.java          # 数据库配置类
├── dto/
│   └── SudokuPuzzle.java            # 数独题目数据传输对象
├── mapper/
│   └── SudokuMapper.java            # MyBatis映射接口
└── service/
    └── SudokuDatabaseService.java    # 数据库服务类

src/main/resources/
├── mapper/
│   └── SudokuMapper.xml             # MyBatis SQL映射文件
└── db/
    └── init.sql                     # 数据库初始化脚本

src/test/java/com/hodoku/service/
└── SudokuDatabaseServiceTest.java   # 数据库服务测试类
```

## 数据库表结构

### sudoku_puzzles表
- `id`: 主键ID (BIGINT, 自增)
- `puzzle`: 数独题目字符串 (VARCHAR(81), 81个字符，0表示空格)
- `solution`: 数独解答字符串 (VARCHAR(81))
- `level`: 难度级别 (INT, 1-简单, 2-中等, 3-困难, 4-专家)
- `description`: 题目描述 (VARCHAR(500))
- `create_time`: 创建时间 (TIMESTAMP)
- `update_time`: 更新时间 (TIMESTAMP)

## 使用方法

### 1. 数据库初始化
执行 `src/main/resources/db/init.sql` 文件创建表和初始化数据。

### 2. 在代码中使用
```java
@Autowired
private SudokuDatabaseService sudokuDatabaseService;

// 保存数独题目
SudokuPuzzle puzzle = sudokuDatabaseService.savePuzzle(
    puzzleString, solutionString, level, description);

// 根据难度获取题目
List<SudokuPuzzle> puzzles = sudokuDatabaseService.getPuzzlesByLevel(2);

// 根据ID获取题目
SudokuPuzzle puzzle = sudokuDatabaseService.getPuzzleById(1L);
```

### 3. 运行测试
运行 `SudokuDatabaseServiceTest` 类中的测试方法验证数据库连接和操作功能。

## 注意事项

1. **数据库连接**: 确保MySQL服务器在10.9.4.70:3306上运行，且sudoku数据库和用户已创建。

2. **字符编码**: 配置了UTF-8字符编码支持中文。

3. **时区设置**: 使用了Asia/Shanghai时区。

4. **自动建表**: 配置了JPA自动更新表结构 (`spring.jpa.hibernate.ddl-auto=update`)。

5. **SQL日志**: 开启了MyBatis和JPA的SQL日志输出，便于调试。

## 扩展建议

1. 可以添加更多的查询方法，如按创建时间范围查询、模糊搜索等。
2. 可以增加数据库连接池配置优化性能。
3. 可以添加Redis缓存层提高查询效率。
4. 可以实现数据库备份和恢复功能。