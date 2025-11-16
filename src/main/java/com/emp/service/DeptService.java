package com.emp.service;

import com.emp.entity.Dept;
import java.util.List;

/**
 * 部门Service接口
 * 定义部门相关的业务逻辑
 */
public interface DeptService {

    /**
     * 添加部门
     * @param dept 部门对象
     * @return true成功，false失败
     */
    boolean addDept(Dept dept);

    /**
     * 删除部门
     * @param deptno 部门编号
     * @return true成功，false失败
     */
    boolean removeDept(Integer deptno);

    /**
     * 修改部门信息
     * @param dept 部门对象
     * @return true成功，false失败
     */
    boolean modifyDept(Dept dept);

    /**
     * 根据部门编号查询部门
     * @param deptno 部门编号
     * @return 部门对象
     */
    Dept getDeptByDeptno(Integer deptno);

    /**
     * 查询所有部门
     * @return 部门列表
     */
    List<Dept> getAllDepts();

    /**
     * 根据部门名称模糊查询
     * @param dname 部门名称关键字
     * @return 部门列表
     */
    List<Dept> searchDeptsByName(String dname);

    /**
     * 检查部门编号是否已存在
     * @param deptno 部门编号
     * @return true存在，false不存在
     */
    boolean isDeptExists(Integer deptno);

    /**
     * 检查部门下是否有员工
     * @param deptno 部门编号
     * @return true有员工，false无员工
     */
    boolean hasDeptEmployees(Integer deptno);
}