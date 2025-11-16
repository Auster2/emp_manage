package com.emp.service.impl;

import com.emp.dao.DeptDao;
import com.emp.dao.EmpDao;
import com.emp.dao.impl.DeptDaoImpl;
import com.emp.dao.impl.EmpDaoImpl;
import com.emp.entity.Dept;
import com.emp.service.DeptService;

import java.util.List;

/**
 * 部门Service实现类
 */
public class DeptServiceImpl implements DeptService {

    private final DeptDao deptDao = new DeptDaoImpl();
    private final EmpDao empDao = new EmpDaoImpl();

    @Override
    public boolean addDept(Dept dept) {
        // 业务逻辑：检查部门编号是否已存在
        if (deptDao.existsByDeptno(dept.getDeptno())) {
            System.err.println("错误：部门编号 " + dept.getDeptno() + " 已存在！");
            return false;
        }

        // 业务逻辑：验证部门名称不能为空
        if (dept.getDname() == null || dept.getDname().trim().isEmpty()) {
            System.err.println("错误：部门名称不能为空！");
            return false;
        }

        return deptDao.insert(dept) > 0;
    }

    @Override
    public boolean removeDept(Integer deptno) {
        // 业务逻辑：检查部门是否存在
        if (!deptDao.existsByDeptno(deptno)) {
            System.err.println("错误：部门编号 " + deptno + " 不存在！");
            return false;
        }

        // 业务逻辑：检查部门下是否有员工
        if (hasDeptEmployees(deptno)) {
            System.err.println("错误：部门 " + deptno + " 下还有员工，不能删除！");
            return false;
        }

        return deptDao.deleteByDeptno(deptno) > 0;
    }

    @Override
    public boolean modifyDept(Dept dept) {
        // 业务逻辑：检查部门是否存在
        if (!deptDao.existsByDeptno(dept.getDeptno())) {
            System.err.println("错误：部门编号 " + dept.getDeptno() + " 不存在！");
            return false;
        }

        // 业务逻辑：验证部门名称不能为空
        if (dept.getDname() == null || dept.getDname().trim().isEmpty()) {
            System.err.println("错误：部门名称不能为空！");
            return false;
        }

        return deptDao.update(dept) > 0;
    }

    @Override
    public Dept getDeptByDeptno(Integer deptno) {
        return deptDao.selectByDeptno(deptno);
    }

    @Override
    public List<Dept> getAllDepts() {
        return deptDao.selectAll();
    }

    @Override
    public List<Dept> searchDeptsByName(String dname) {
        if (dname == null || dname.trim().isEmpty()) {
            return getAllDepts();
        }
        return deptDao.selectByDname(dname.trim());
    }

    @Override
    public boolean isDeptExists(Integer deptno) {
        return deptDao.existsByDeptno(deptno);
    }

    @Override
    public boolean hasDeptEmployees(Integer deptno) {
        List empList = empDao.selectByDeptno(deptno);
        return empList != null && !empList.isEmpty();
    }
}