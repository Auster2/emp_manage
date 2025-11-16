package com.emp.dao;

import com.emp.entity.Emp;
import java.util.List;

/**
 * 员工DAO接口
 * 定义员工表的所有数据库操作
 */
public interface EmpDao {

    /**
     * 添加员工
     * @param emp 员工对象
     * @return 影响的行数
     */
    int insert(Emp emp);

    /**
     * 根据员工编号删除员工
     * @param empno 员工编号
     * @return 影响的行数
     */
    int deleteByEmpno(Integer empno);

    /**
     * 更新员工信息
     * @param emp 员工对象
     * @return 影响的行数
     */
    int update(Emp emp);

    /**
     * 根据员工编号查询员工
     * @param empno 员工编号
     * @return 员工对象，不存在返回null
     */
    Emp selectByEmpno(Integer empno);

    /**
     * 查询所有员工
     * @return 员工列表
     */
    List<Emp> selectAll();

    /**
     * 根据部门编号查询员工
     * @param deptno 部门编号
     * @return 员工列表
     */
    List<Emp> selectByDeptno(Integer deptno);

    /**
     * 根据员工姓名模糊查询
     * @param ename 员工姓名关键字
     * @return 员工列表
     */
    List<Emp> selectByEname(String ename);

    /**
     * 根据职位查询员工
     * @param job 职位
     * @return 员工列表
     */
    List<Emp> selectByJob(String job);

    /**
     * 检查员工编号是否存在
     * @param empno 员工编号
     * @return true存在，false不存在
     */
    boolean existsByEmpno(Integer empno);

    /**
     * 查询员工及其部门信息（关联查询）
     * @return 员工列表（包含部门信息）
     */
    List<Emp> selectAllWithDept();

    /**
     * 根据员工编号查询员工及其部门信息
     * @param empno 员工编号
     * @return 员工对象（包含部门信息）
     */
    Emp selectByEmpnoWithDept(Integer empno);

    /**
     * 根据薪资范围查询员工
     * @param minSal 最低薪资
     * @param maxSal 最高薪资
     * @return 员工列表
     */
    List<Emp> selectBySalRange(Integer minSal, Integer maxSal);
}