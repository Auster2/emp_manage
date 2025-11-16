package com.emp.test;

import com.emp.entity.Dept;
import com.emp.entity.Emp;
import com.emp.service.DeptService;
import com.emp.service.EmpService;
import com.emp.service.impl.DeptServiceImpl;
import com.emp.service.impl.EmpServiceImpl;

import java.sql.Date;
import java.util.List;

/**
 * Service层测试类
 */
public class ServiceTest {

    public static void main(String[] args) {
        testDeptService();
        System.out.println("\n" + "=".repeat(60) + "\n");
        testEmpService();
    }

    /**
     * 测试部门Service
     */
    public static void testDeptService() {
        System.out.println("===== 测试部门Service =====");
        DeptService deptService = new DeptServiceImpl();

        // 1. 测试添加部门
        System.out.println("\n1. 测试添加部门：");
        Dept dept = new Dept(60, "测试部", "深圳");
        boolean result = deptService.addDept(dept);
        System.out.println("添加结果：" + (result ? "成功" : "失败"));

        // 2. 测试重复添加（应该失败）
        System.out.println("\n2. 测试重复添加（应该失败）：");
        result = deptService.addDept(dept);
        System.out.println("添加结果：" + (result ? "成功" : "失败"));

        // 3. 测试查询所有部门
        System.out.println("\n3. 查询所有部门：");
        List<Dept> deptList = deptService.getAllDepts();
        for (Dept d : deptList) {
            System.out.println(d);
        }

        // 4. 测试修改部门
        System.out.println("\n4. 测试修改部门：");
        dept.setDname("研发部");
        dept.setLoc("杭州");
        result = deptService.modifyDept(dept);
        System.out.println("修改结果：" + (result ? "成功" : "失败"));

        // 5. 测试删除部门
        System.out.println("\n5. 测试删除部门：");
        result = deptService.removeDept(60);
        System.out.println("删除结果：" + (result ? "成功" : "失败"));
    }

    /**
     * 测试员工Service
     */
    public static void testEmpService() {
        System.out.println("===== 测试员工Service =====");
        EmpService empService = new EmpServiceImpl();

        // 1. 测试添加员工
        System.out.println("\n1. 测试添加员工：");
        Emp emp = new Emp();
        emp.setEmpno(8888);
        emp.setEname("李四");
        emp.setJob("经理");
        emp.setHiredate(Date.valueOf("2024-06-01"));
        emp.setSal(10000);
        emp.setComm(1000);
        emp.setDeptno(10);

        boolean result = empService.addEmp(emp);
        System.out.println("添加结果：" + (result ? "成功" : "失败"));

        // 2. 测试添加不合法员工（薪资<=5000，应该失败）
        System.out.println("\n2. 测试添加不合法员工（薪资<=5000）：");
        Emp invalidEmp = new Emp();
        invalidEmp.setEmpno(7777);
        invalidEmp.setEname("王五");
        invalidEmp.setJob("助理");
        invalidEmp.setHiredate(Date.valueOf("2024-06-01"));
        invalidEmp.setSal(4000);  // 不合法
        invalidEmp.setDeptno(10);

        result = empService.addEmp(invalidEmp);
        System.out.println("添加结果：" + (result ? "成功" : "失败"));

        // 3. 测试查询所有员工（包含部门信息）
        System.out.println("\n3. 查询所有员工（包含部门信息）：");
        List<Emp> empList = empService.getAllEmpsWithDept();
        for (Emp e : empList) {
            System.out.println(e);
        }

        // 4. 测试薪资调整
        System.out.println("\n4. 测试薪资调整：");
        result = empService.adjustSalary(8888, 12000);
        System.out.println("调整结果：" + (result ? "成功" : "失败"));
        if (result) {
            Emp updatedEmp = empService.getEmpByEmpno(8888);
            System.out.println("调整后薪资：" + updatedEmp.getSal());
        }

        // 5. 测试统计功能
        System.out.println("\n5. 测试统计功能：");
        int count = empService.countEmpsByDept(10);
        double avgSal = empService.getAvgSalByDept(10);
        System.out.println("部门10的员工数量：" + count);
        System.out.println("部门10的平均薪资：" + String.format("%.2f", avgSal));

        // 6. 测试删除员工
        System.out.println("\n6. 测试删除员工：");
        result = empService.removeEmp(8888);
        System.out.println("删除结果：" + (result ? "成功" : "失败"));
    }
}