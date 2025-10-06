# Maven Profiles动态配置说明

## 配置概述

本项目已将Spring Boot的profile激活配置通过Maven Profiles实现动态管理。现在可以在构建时根据不同环境自动设置激活的配置文件。

## 配置文件结构

### 1. pom.xml 配置
- 添加了3个Maven Profile：`local`（本地）、`pro`（生产）、`test`（测试）
- 默认激活 `local` 环境
- 启用了资源文件过滤功能

### 2. application.yml 配置
- 使用Maven属性占位符：`@spring.profiles.active@`
- 在构建时会被替换为对应环境的值

## 使用方法

### 本地开发环境（默认）
```bash
mvn clean compile
# 或
mvn clean compile -Plocal
```

### 生产环境
```bash
mvn clean compile -Ppro
```

### 测试环境
```bash
mvn clean compile -Ptest
```

### Spring Boot 打包
```bash
# 本地环境打包
mvn clean package -Plocal

# 生产环境打包
mvn clean package -Ppro

# 测试环境打包
mvn clean package -Ptest
```

## 构建后的效果

构建完成后，`target/classes/application.yml` 中的占位符会被替换为实际值：

- `local` 环境：`active: local`
- `pro` 环境：`active: pro`
- `test` 环境：`active: test`

## 环境配置文件对应关系

- `local` → `application-local.yml`
- `pro` → `application-pro.yml`
- `test` → `application-test.yml`（如需要可创建）

## 注意事项

1. 资源文件过滤功能已启用，Maven会处理所有 `@...@` 占位符
2. 默认激活本地环境，适合开发调试
3. 生产部署时使用 `-Ppro` 参数确保正确的环境配置
4. 可根据需要添加更多环境（如：staging、uat等）

## IDE 集成

在IDE中运行时，可以通过以下方式指定Profile：
- IntelliJ IDEA：Run/Debug Configuration → VM options → `-Dspring.profiles.active=local`
- Eclipse：Run Configuration → Arguments → VM arguments → `-Dspring.profiles.active=local`