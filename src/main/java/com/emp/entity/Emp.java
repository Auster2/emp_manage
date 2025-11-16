package com.emp.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 员工实体类
 * 对应数据库表：EMP
 */
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer empno;      // 员工编号（主键）
    private String ename;       // 员工姓名
    private String job;         // 职位
    private Integer mgr;        // 上级经理编号
    private Date hiredate;      // 入职日期
    private Integer sal;        // 薪资
    private Integer comm;       // 奖金
    private Integer deptno;     // 所属部门编号（外键）

    // 关联对象（可选，用于关联查询）
    private Dept dept;          // 所属部门对象
    private String mgrName;     // 上级经理姓名（用于显示）

    // 无参构造方法
    public Emp() {
    }

    // 常用构造方法
    public Emp(Integer empno, String ename, String job, Integer mgr,
               Date hiredate, Integer sal, Integer comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    // Getter和Setter方法
    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    // toString方法：方便打印输出
    @Override
    public String toString() {
        return "Emp{" +
                "员工编号=" + empno +
                ", 姓名='" + ename + '\'' +
                ", 职位='" + job + '\'' +
                ", 上级经理=" + (mgrName != null ? mgrName : mgr) +
                ", 入职日期=" + hiredate +
                ", 薪资=" + sal +
                ", 奖金=" + comm +
                ", 部门编号=" + deptno +
                (dept != null ? ", 部门名称='" + dept.getDname() + '\'' : "") +
                '}';
    }

    // 简化版toString：用于列表显示
    public String toSimpleString() {
        return String.format("%-6d %-10s %-10s %-8d %-12s %-8d %-8s %-6d",
                empno, ename, job,
                mgr != null ? mgr : 0,
                hiredate != null ? hiredate.toString() : "N/A",
                sal,
                comm != null ? comm : 0,
                deptno);
    }

    // equals和hashCode方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return empno != null && empno.equals(emp.empno);
    }

    @Override
    public int hashCode() {
        return empno != null ? empno.hashCode() : 0;
    }
}