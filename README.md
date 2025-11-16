# 构建全过程

# 1

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

# 2

## ? 第二步：创建实体类（Entity/POJO）

### 需要完成的任务：

根据数据库表结构创建对应的Java实体类，用于在程序中表示数据库中的数据。

---

### ? 具体实现：

#### 1. Dept.java（部门实体类）#### 2. Emp.java（员工实体类）---

## ? 第二步完成标准：

1. ? 在 `src/main/java/com/emp/entity/` 目录下创建 `Dept.java`
2. ? 在 `src/main/java/com/emp/entity/` 目录下创建 `Emp.java`
3. ? 两个实体类都包含完整的属性、构造方法、getter/setter、toString等方法
4. ? 确保代码无编译错误

---

## ? 实体类设计说明：

### Dept类特点：
- 对应DEPT表的3个字段
- 简单的POJO类
- 实现了Serializable接口（便于序列化）

### Emp类特点：
- 对应EMP表的8个字段
- 额外添加了`dept`对象属性（用于关联查询时存储部门信息）
- 添加了`mgrName`属性（用于显示上级经理姓名）
- 提供了`toSimpleString()`方法用于格式化列表显示
- 使用`java.sql.Date`类型对应数据库的DATE类型

### 关键点：
- **属性名与数据库字段名对应**（小写）
- **数据类型匹配**：INT → Integer, CHAR/VARCHAR → String, DATE → Date
- **允许null值**：使用包装类型（Integer）而非基本类型（int）
- **toString方法**：便于调试和显示

---

## ? 操作步骤：

1. 在 `src/main/java/com/emp/` 下创建 `entity` 包
2. 创建 `Dept.java` 并复制代码
3. 创建 `Emp.java` 并复制代码
4. 确保没有编译错误

---

## ? 可选测试：

你可以创建一个简单的测试类来验证实体类：

```java
package com.emp.entity;

import java.sql.Date;

public class EntityTest {
    public static void main(String[] args) {
        // 测试Dept类
        Dept dept = new Dept(10, "研发部", "北京");
        System.out.println(dept);
        
        // 测试Emp类
        Emp emp = new Emp(1001, "张三", "工程师", null, 
                         Date.valueOf("2024-01-01"), 8000, 500, 10);
        System.out.println(emp);
        
        System.out.println("实体类测试成功！");
    }
}
```

# 3

## ? 第三步：创建DAO层（数据访问层）

### 需要完成的任务：

创建DAO（Data Access Object）接口和实现类，负责所有数据库操作（增删改查）。

---

### ? 具体实现：

#### 1. DeptDao.java（部门DAO接口）#### 2. DeptDaoImpl.java（部门DAO实现类）#### 3. EmpDao.java（员工DAO接口）#### 4. EmpDaoImpl.java（员工DAO实现类）---

## ? 第三步完成标准：

1. ? 创建 `DeptDao.java` 接口
2. ? 创建 `DeptDaoImpl.java` 实现类
3. ? 创建 `EmpDao.java` 接口
4. ? 创建 `EmpDaoImpl.java` 实现类
5. ? 所有代码无编译错误

---

## ? 目录结构：

```
src/main/java/com/emp/
├── dao/
│   ├── DeptDao.java          (接口)
│   ├── EmpDao.java           (接口)
│   └── impl/
│       ├── DeptDaoImpl.java  (实现)
│       └── EmpDaoImpl.java   (实现)
```

---

## ? DAO层设计说明：

### 核心功能：
- **增（Insert）**：添加新记录
- **删（Delete）**：根据主键删除
- **改（Update）**：更新记录
- **查（Select）**：多种查询方式

### 特色功能：
1. **关联查询**：`selectAllWithDept()` - 查询员工同时获取部门信息
2. **模糊查询**：`selectByEname()` - 支持姓名模糊搜索
3. **范围查询**：`selectBySalRange()` - 按薪资范围查询
4. **存在性检查**：`existsByEmpno()` - 防止重复添加

### 技术要点：
- 使用 **PreparedStatement** 防止SQL注入
- 正确处理 **NULL值**（MGR和COMM字段）
- 统一的 **异常处理** 和 **资源关闭**
- **LEFT JOIN** 实现关联查询

---

## ? 可选测试代码：---

## ? 操作步骤：

1. 在 `src/main/java/com/emp/` 下创建 `dao` 包
2. 在 `dao` 包下创建 `impl` 子包
3. 创建4个DAO文件（2个接口 + 2个实现类）
4. （可选）创建测试类验证功能

---

## ?? 重要提示：

在运行测试之前，确保数据库中：
1. **已创建DEPT和EMP表**
2. **DEPT表中至少有一个部门**（例如部门10），否则无法添加员工（外键约束）
3. **测试完成后记得删除测试数据**

示例初始化SQL：
```sql
-- 插入测试部门
INSERT INTO DEPT VALUES (10, '研发部', '北京');
INSERT INTO DEPT VALUES (20, '销售部', '上海');
INSERT INTO DEPT VALUES (30, '市场部', '广州');
```

# 4

## ? 第四步：创建Service层（业务逻辑层）

### 需要完成的任务：

创建Service层，封装业务逻辑，为上层（View层）提供简洁的业务接口。Service层调用DAO层完成数据操作。

---

### ? 具体实现：

#### 1. DeptService.java（部门Service接口）#### 2. DeptServiceImpl.java（部门Service实现类）#### 3. EmpService.java（员工Service接口）#### 4. EmpServiceImpl.java（员工Service实现类）---

## ? 第四步完成标准：

1. ? 创建 `DeptService.java` 接口
2. ? 创建 `DeptServiceImpl.java` 实现类
3. ? 创建 `EmpService.java` 接口
4. ? 创建 `EmpServiceImpl.java` 实现类
5. ? 所有代码无编译错误

---

## ? 目录结构：

```
src/main/java/com/emp/
├── service/
│   ├── DeptService.java          (接口)
│   ├── EmpService.java           (接口)
│   └── impl/
│       ├── DeptServiceImpl.java  (实现)
│       └── EmpServiceImpl.java   (实现)
```

---

## ? Service层设计说明：

### 核心职责：
1. **业务逻辑验证**：检查数据合法性
2. **业务规则执行**：实现复杂的业务流程
3. **调用DAO层**：完成数据持久化
4. **异常处理**：统一处理业务异常

### 关键业务逻辑：

#### DeptService：
- ? 添加部门前检查编号是否重复
- ? 删除部门前检查是否有员工
- ? 验证部门名称不能为空

#### EmpService：
- ? 添加员工前验证编号唯一性
- ? 验证薪资必须>5000（符合数据库约束）
- ? 验证部门和上级经理是否存在
- ? 提供薪资调整、部门转移等业务功能
- ? 统计功能：员工数量、平均薪资

### 设计模式：
- **接口+实现类**：便于扩展和测试
- **依赖DAO层**：通过组合方式使用DAO
- **单一职责**：每个方法只做一件事

---

## ? 可选测试代码：---

## ? 操作步骤：

1. 在 `src/main/java/com/emp/` 下创建 `service` 包
2. 在 `service` 包下创建 `impl` 子包
3. 创建4个Service文件（2个接口 + 2个实现类）
4. （可选）创建测试类验证业务逻辑

---

## ?? 重要说明：

### Service层的价值：
1. **数据验证**：确保数据符合业务规则
2. **业务保护**：如删除部门前检查是否有员工
3. **代码复用**：统一的业务逻辑，避免重复
4. **易于维护**：业务规则变化只需修改Service层

### 与DAO层的区别：
- **DAO层**：只负责数据库操作，不管业务逻辑
- **Service层**：负责业务逻辑，调用DAO完成数据操作

# 5

太好了！现在进入第五步，这是最后一个主要步骤。

## ? 第五步：创建控制台界面（View层）

### 需要完成的任务：

创建用户交互界面，通过控制台菜单实现完整的员工管理功能。

---

### ? 具体实现：

#### 1. MainView.java（主菜单）#### 2. DeptView.java（部门管理界面）#### 3. EmpView.java（员工管理界面）---

## ? 第五步完成标准：

1. ? 创建 `MainView.java` 主菜单
2. ? 创建 `DeptView.java` 部门管理界面
3. ? 创建 `EmpView.java` 员工管理界面
4. ? 所有代码无编译错误
5. ? 运行主程序测试所有功能

---

## ? 最终项目结构：

```
employee-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── emp/
│   │   │           ├── entity/           (实体类)
│   │   │           │   ├── Dept.java
│   │   │           │   └── Emp.java
│   │   │           ├── dao/              (DAO层)
│   │   │           │   ├── DeptDao.java
│   │   │           │   ├── EmpDao.java
│   │   │           │   └── impl/
│   │   │           │       ├── DeptDaoImpl.java
│   │   │           │       └── EmpDaoImpl.java
│   │   │           ├── service/          (Service层)
│   │   │           │   ├── DeptService.java
│   │   │           │   ├── EmpService.java
│   │   │           │   └── impl/
│   │   │           │       ├── DeptServiceImpl.java
│   │   │           │       └── EmpServiceImpl.java
│   │   │           ├── view/             (View层)
│   │   │           │   ├── MainView.java
│   │   │           │   ├── DeptView.java
│   │   │           │   └── EmpView.java
│   │   │           └── util/             (工具类)
│   │   │               └── DBUtil.java
│   │   └── resources/
│   │       └── db.properties
│   └── test/
└── pom.xml
```

---

## ? 系统功能清单：

### 部门管理模块：
- ? 查看所有部门
- ? 添加部门
- ? 修改部门
- ? 删除部门（防止删除有员工的部门）
- ? 查询部门
- ? 按名称搜索部门

### 员工管理模块：
- ? 查看所有员工（含部门信息）
- ? 添加员工（完整验证）
- ? 修改员工
- ? 删除员工
- ? 查询员工
- ? 按姓名搜索员工
- ? 按部门查询员工
- ? 按职位查询员工
- ? 按薪资范围查询
- ? 调整员工薪资
- ? 转移员工部门
- ? 统计信息（员工数量、平均薪资）

---

## ? 运行系统：

### 1. 准备测试数据（可选）：
```sql
-- 插入部门数据
INSERT INTO DEPT VALUES (10, '研发部', '北京');
INSERT INTO DEPT VALUES (20, '销售部', '上海');
INSERT INTO DEPT VALUES (30, '市场部', '广州');

-- 插入员工数据
INSERT INTO EMP VALUES (1001, '张三', '经理', NULL, '2020-01-01', 15000, 2000, 10);
INSERT INTO EMP VALUES (1002, '李四', '工程师', 1001, '2021-03-15', 10000, 1000, 10);
INSERT INTO EMP VALUES (1003, '王五', '销售', NULL, '2021-06-01', 8000, 1500, 20);
```

### 2. 运行主程序：
```bash
# 找到MainView.java的main方法，点击运行
# 或使用命令行
mvn clean compile
mvn exec:java -Dexec.mainClass="com.emp.view.MainView"
```

---

## ? 系统特色：

1. **分层架构清晰**：Entity → DAO → Service → View
2. **业务逻辑完善**：数据验证、外键检查、约束处理
3. **用户体验友好**：菜单导航、错误提示、确认操作
4. **功能全面丰富**：CRUD + 查询 + 统计
5. **代码规范统一**：命名规范、注释完整

---

## ? 恭喜你完成了职工管理系统！

现在你可以：
1. 运行 `MainView.main()` 启动系统
2. 测试各个功能模块
3. 添加更多自定义功能
4. 优化界面和交互体验

如果遇到任何问题或需要添加新功能，随时告诉我！?