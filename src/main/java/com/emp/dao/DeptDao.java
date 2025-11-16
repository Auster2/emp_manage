package com.emp.dao;

import com.emp.entity.Dept;
import java.util.List;

/**
 * 部门DAO接口
 * 定义部门表的所有数据库操作
 */
public interface DeptDao {

    /**
     * 添加部门
     * @param dept 部门对象
     * @return 影响的行数
     */
    int insert(Dept dept);

    /**
     * 根据部门编号删除部门
     * @param deptno 部门编号
     * @return 影响的行数
     */
    int deleteByDeptno(Integer deptno);

    /**
     * 更新部门信息
     * @param dept 部门对象
     * @return 影响的行数
     */
    int update(Dept dept);

    /**
     * 根据部门编号查询部门
     * @param deptno 部门编号
     * @return 部门对象，不存在返回null
     */
    Dept selectByDeptno(Integer deptno);

    /**
     * 查询所有部门
     * @return 部门列表
     */
    List<Dept> selectAll();

    /**
     * 根据部门名称模糊查询
     * @param dname 部门名称关键字
     * @return 部门列表
     */
    List<Dept> selectByDname(String dname);

    /**
     * 检查部门编号是否存在
     * @param deptno 部门编号
     * @return true存在，false不存在
     */
    boolean existsByDeptno(Integer deptno);
}