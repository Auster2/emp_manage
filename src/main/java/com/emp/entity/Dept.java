package com.emp.entity;

import java.io.Serializable;

/**
 * 部门实体类
 * 对应数据库表：DEPT
 */
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer deptno;    // 部门编号（主键）
    private String dname;      // 部门名称
    private String loc;        // 部门位置

    // 无参构造方法
    public Dept() {
    }

    // 全参构造方法
    public Dept(Integer deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    // Getter和Setter方法
    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    // toString方法：方便打印输出
    @Override
    public String toString() {
        return "Dept{" +
                "部门编号=" + deptno +
                ", 部门名称='" + dname + '\'' +
                ", 部门位置='" + loc + '\'' +
                '}';
    }

    // equals和hashCode方法：用于对象比较
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return deptno != null && deptno.equals(dept.deptno);
    }

    @Override
    public int hashCode() {
        return deptno != null ? deptno.hashCode() : 0;
    }
}