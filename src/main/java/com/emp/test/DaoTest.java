package com.emp.test;

import com.emp.dao.DeptDao;
import com.emp.dao.EmpDao;
import com.emp.dao.impl.DeptDaoImpl;
import com.emp.dao.impl.EmpDaoImpl;
import com.emp.entity.Dept;
import com.emp.entity.Emp;

import java.sql.Date;
import java.util.List;

/**
 * DAO层测试类
 */
public class DaoTest {

    public static void main(String[] args) {
        testDeptDao();
        System.out.println("\n" + "=".repeat(60) + "\n");
        testEmpDao();
    }

    /**
     * 测试部门DAO
     */
    public static void testDeptDao() {
        System.out.println("===== 测试部门DAO =====");
        DeptDao deptDao = new DeptDaoImpl();

        // 1. 测试添加部门
        System.out.println("\n1. 测试添加部门：");
        Dept dept = new Dept(50, "测试部门", "上海");
        int result = deptDao.insert(dept);
        System.out.println("添加结果：" + (result > 0 ? "成功" : "失败"));

        // 2. 测试查询所有部门
        System.out.println("\n2. 查询所有部门：");
        List<Dept> deptList = deptDao.selectAll();
        for (Dept d : deptList) {
            System.out.println(d);
        }

        // 3. 测试根据编号查询
        System.out.println("\n3. 查询部门编号50：");
        Dept queryDept = deptDao.selectByDeptno(50);
        System.out.println(queryDept);

        // 4. 测试更新部门
        System.out.println("\n4. 测试更新部门：");
        if (queryDept != null) {
            queryDept.setDname("研发部");
            queryDept.setLoc("北京");
            result = deptDao.update(queryDept);
            System.out.println("更新结果：" + (result > 0 ? "成功" : "失败"));
            System.out.println("更新后：" + deptDao.selectByDeptno(50));
        }

        // 5. 测试删除部门（注意：如果该部门下有员工会删除失败）
        System.out.println("\n5. 测试删除部门：");
        result = deptDao.deleteByDeptno(50);
        System.out.println("删除结果：" + (result > 0 ? "成功" : "失败"));
    }

    /**
     * 测试员工DAO
     */
    public static void testEmpDao() {
        System.out.println("===== 测试员工DAO =====");
        EmpDao empDao = new EmpDaoImpl();

        // 1. 测试查询所有员工
        System.out.println("\n1. 查询所有员工：");
        List<Emp> empList = empDao.selectAll();
        System.out.println("共有 " + empList.size() + " 名员工");
        for (Emp emp : empList) {
            System.out.println(emp);
        }

        // 2. 测试添加员工
        System.out.println("\n2. 测试添加员工：");
        Emp newEmp = new Emp();
        newEmp.setEmpno(9999);
        newEmp.setEname("测试员工");
        newEmp.setJob("工程师");
        newEmp.setHiredate(Date.valueOf("2024-01-01"));
        newEmp.setSal(8000);
        newEmp.setComm(500);
        newEmp.setDeptno(10);

        int result = empDao.insert(newEmp);
        System.out.println("添加结果：" + (result > 0 ? "成功" : "失败"));

        // 3. 测试关联查询
        System.out.println("\n3. 查询员工及部门信息：");
        List<Emp> empWithDeptList = empDao.selectAllWithDept();
        for (Emp emp : empWithDeptList) {
            System.out.println(emp);
        }

        // 4. 测试删除员工
        System.out.println("\n4. 测试删除员工：");
        result = empDao.deleteByEmpno(9999);
        System.out.println("删除结果：" + (result > 0 ? "成功" : "失败"));
    }
}