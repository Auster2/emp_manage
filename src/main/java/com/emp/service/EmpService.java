package com.emp.service;

import com.emp.entity.Emp;
import java.util.List;

/**
 * 员工Service接口
 * 定义员工相关的业务逻辑
 */
public interface EmpService {

    /**
     * 添加员工
     * @param emp 员工对象
     * @return true成功，false失败
     */
    boolean addEmp(Emp emp);

    /**
     * 删除员工
     * @param empno 员工编号
     * @return true成功，false失败
     */
    boolean removeEmp(Integer empno);

    /**
     * 修改员工信息
     * @param emp 员工对象
     * @return true成功，false失败
     */
    boolean modifyEmp(Emp emp);

    /**
     * 根据员工编号查询员工
     * @param empno 员工编号
     * @return 员工对象
     */
    Emp getEmpByEmpno(Integer empno);

    /**
     * 查询所有员工
     * @return 员工列表
     */
    List<Emp> getAllEmps();

    /**
     * 查询所有员工（包含部门信息）
     * @return 员工列表
     */
    List<Emp> getAllEmpsWithDept();

    /**
     * 根据部门编号查询员工
     * @param deptno 部门编号
     * @return 员工列表
     */
    List<Emp> getEmpsByDeptno(Integer deptno);

    /**
     * 根据员工姓名模糊查询
     * @param ename 员工姓名关键字
     * @return 员工列表
     */
    List<Emp> searchEmpsByName(String ename);

    /**
     * 根据职位查询员工
     * @param job 职位
     * @return 员工列表
     */
    List<Emp> getEmpsByJob(String job);

    /**
     * 根据薪资范围查询员工
     * @param minSal 最低薪资
     * @param maxSal 最高薪资
     * @return 员工列表
     */
    List<Emp> getEmpsBySalRange(Integer minSal, Integer maxSal);

    /**
     * 检查员工编号是否已存在
     * @param empno 员工编号
     * @return true存在，false不存在
     */
    boolean isEmpExists(Integer empno);

    /**
     * 调整员工薪资
     * @param empno 员工编号
     * @param newSal 新薪资
     * @return true成功，false失败
     */
    boolean adjustSalary(Integer empno, Integer newSal);

    /**
     * 转移员工部门
     * @param empno 员工编号
     * @param newDeptno 新部门编号
     * @return true成功，false失败
     */
    boolean transferDept(Integer empno, Integer newDeptno);

    /**
     * 统计部门员工数量
     * @param deptno 部门编号
     * @return 员工数量
     */
    int countEmpsByDept(Integer deptno);

    /**
     * 计算部门平均薪资
     * @param deptno 部门编号
     * @return 平均薪资
     */
    double getAvgSalByDept(Integer deptno);
}