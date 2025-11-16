# 构建全过程

## ? 项目整体规划

**第1步：项目初始化和数据库连接**（当前步骤）
**第2步：创建实体类（Entity/POJO）**
**第3步：创建DAO层（数据访问层）**
**第4步：创建Service层（业务逻辑层）**
**第5步：创建控制台界面（View层）**
**第6步：整合测试和优化**

---

## ? 第一步：项目初始化和数据库连接

### 需要完成的任务：

1. **创建Maven项目结构**
```
employee-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── emp/
│   │   │           ├── entity/     (实体类)
│   │   │           ├── dao/        (数据访问层)
│   │   │           ├── service/    (业务逻辑层)
│   │   │           ├── view/       (视图层)
│   │   │           └── util/       (工具类)
│   │   └── resources/
│   │       └── db.properties       (数据库配置)
│   └── test/
└── pom.xml
```

2. **配置pom.xml（添加MySQL驱动依赖）**

3. **创建数据库配置文件 db.properties**

4. **编写数据库连接工具类 DBUtil.java**

---

### ? 具体实现：

#### 1. pom.xml 配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.emp</groupId>
    <artifactId>employee-management-system</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- MySQL驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
    </dependencies>
</project>
```

#### 2. db.properties（在src/main/resources/下创建）
```properties
# 数据库连接配置
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/sql_class?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8
jdbc.username=root
jdbc.password=你的数据库密码
```

#### 3. DBUtil.java（数据库连接工具类）---

## ? 第一步完成标准：

1. ? Maven项目创建完成，目录结构清晰
2. ? pom.xml配置MySQL驱动依赖
3. ? db.properties文件创建并配置正确的数据库信息
4. ? DBUtil.java工具类编写完成
5. ? 运行DBUtil的main方法，控制台输出"数据库连接成功！"

---

## ? 操作步骤：

1. **使用IDE（如IntelliJ IDEA或Eclipse）创建Maven项目**
2. **将上述pom.xml内容复制进去**
3. **在src/main/resources/创建db.properties，填入你的数据库密码**
4. **在src/main/java/com/emp/util/创建DBUtil.java**
5. **运行DBUtil的main方法测试连接**

---

## ?? 注意事项：

- 确保MySQL服务已启动
- db.properties中的密码要改成你自己的
- 如果连接失败，检查数据库名称是否为 `sql_class`
- 确保sql_class数据库中已经创建了DEPT和EMP表
