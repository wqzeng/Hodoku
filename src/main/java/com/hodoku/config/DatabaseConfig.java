package com.hodoku.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置类
 * 配置MyBatis映射器扫描路径
 */
@Configuration
@MapperScan("com.hodoku.mapper")
public class DatabaseConfig {
    
}