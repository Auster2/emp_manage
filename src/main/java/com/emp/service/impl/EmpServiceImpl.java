package com.emp.service.impl;

import com.emp.dao.DeptDao;
import com.emp.dao.EmpDao;
import com.emp.dao.impl.DeptDaoImpl;
import com.emp.dao.impl.EmpDaoImpl;
import com.emp.entity.Emp;
import com.emp.service.EmpService;

import java.util.List;

/**
 * 员工Service实现类
 */
public class EmpServiceImpl implements EmpService {

    private final EmpDao empDao = new EmpDaoImpl();
    private final DeptDao deptDao = new DeptDaoImpl();

    @Override
    public boolean addEmp(Emp emp) {
        // 业务逻辑：检查员工编号是否已存在
        if (empDao.existsByEmpno(emp.getEmpno())) {
            System.err.println("错误：员工编号 " + emp.getEmpno() + " 已存在！");
            return false;
        }

        // 业务逻辑：验证员工姓名不能为空
        if (emp.getEname() == null || emp.getEname().trim().isEmpty()) {
            System.err.println("错误：员工姓名不能为空！");
            return false;
        }

        // 业务逻辑：验证职位不能为空
        if (emp.getJob() == null || emp.getJob().trim().isEmpty()) {
            System.err.println("错误：职位不能为空！");
            return false;
        }

        // 业务逻辑：验证薪资必须大于5000（数据库约束）
        if (emp.getSal() == null || emp.getSal() <= 5000) {
            System.err.println("错误：薪资必须大于5000！");
            return false;
        }

        // 业务逻辑：验证部门是否存在
        if (emp.getDeptno() != null && !deptDao.existsByDeptno(emp.getDeptno())) {
            System.err.println("错误：部门编号 " + emp.getDeptno() + " 不存在！");
            return false;
        }

        // 业务逻辑：验证上级经理是否存在
        if (emp.getMgr() != null && !empDao.existsByEmpno(emp.getMgr())) {
            System.err.println("错误：上级经理编号 " + emp.getMgr() + " 不存在！");
            return false;
        }

        return empDao.insert(emp) > 0;
    }

    @Override
    public boolean removeEmp(Integer empno) {
        // 业务逻辑：检查员工是否存在
        if (!empDao.existsByEmpno(empno)) {
            System.err.println("错误：员工编号 " + empno + " 不存在！");
            return false;
        }

        // 业务逻辑：检查该员工是否是其他员工的上级
        // 这里简化处理，实际可以通过查询MGR字段判断

        return empDao.deleteByEmpno(empno) > 0;
    }

    @Override
    public boolean modifyEmp(Emp emp) {
        // 业务逻辑：检查员工是否存在
        if (!empDao.existsByEmpno(emp.getEmpno())) {
            System.err.println("错误：员工编号 " + emp.getEmpno() + " 不存在！");
            return false;
        }

        // 业务逻辑：验证员工姓名不能为空
        if (emp.getEname() == null || emp.getEname().trim().isEmpty()) {
            System.err.println("错误：员工姓名不能为空！");
            return false;
        }

        // 业务逻辑：验证职位不能为空
        if (emp.getJob() == null || emp.getJob().trim().isEmpty()) {
            System.err.println("错误：职位不能为空！");
            return false;
        }

        // 业务逻辑：验证薪资必须大于5000
        if (emp.getSal() == null || emp.getSal() <= 5000) {
            System.err.println("错误：薪资必须大于5000！");
            return false;
        }

        // 业务逻辑：验证部门是否存在
        if (emp.getDeptno() != null && !deptDao.existsByDeptno(emp.getDeptno())) {
            System.err.println("错误：部门编号 " + emp.getDeptno() + " 不存在！");
            return false;
        }

        // 业务逻辑：验证上级经理是否存在
        if (emp.getMgr() != null && !empDao.existsByEmpno(emp.getMgr())) {
            System.err.println("错误：上级经理编号 " + emp.getMgr() + " 不存在！");
            return false;
        }

        return empDao.update(emp) > 0;
    }

    @Override
    public Emp getEmpByEmpno(Integer empno) {
        return empDao.selectByEmpno(empno);
    }

    @Override
    public List<Emp> getAllEmps() {
        return empDao.selectAll();
    }

    @Override
    public List<Emp> getAllEmpsWithDept() {
        return empDao.selectAllWithDept();
    }

    @Override
    public List<Emp> getEmpsByDeptno(Integer deptno) {
        return empDao.selectByDeptno(deptno);
    }

    @Override
    public List<Emp> searchEmpsByName(String ename) {
        if (ename == null || ename.trim().isEmpty()) {
            return getAllEmps();
        }
        return empDao.selectByEname(ename.trim());
    }

    @Override
    public List<Emp> getEmpsByJob(String job) {
        return empDao.selectByJob(job);
    }

    @Override
    public List<Emp> getEmpsBySalRange(Integer minSal, Integer maxSal) {
        // 业务逻辑：验证薪资范围
        if (minSal == null || maxSal == null) {
            System.err.println("错误：薪资范围不能为空！");
            return null;
        }
        if (minSal > maxSal) {
            System.err.println("错误：最低薪资不能大于最高薪资！");
            return null;
        }
        return empDao.selectBySalRange(minSal, maxSal);
    }

    @Override
    public boolean isEmpExists(Integer empno) {
        return empDao.existsByEmpno(empno);
    }

    @Override
    public boolean adjustSalary(Integer empno, Integer newSal) {
        // 业务逻辑：验证薪资必须大于5000
        if (newSal == null || newSal <= 5000) {
            System.err.println("错误：新薪资必须大于5000！");
            return false;
        }

        Emp emp = empDao.selectByEmpno(empno);
        if (emp == null) {
            System.err.println("错误：员工编号 " + empno + " 不存在！");
            return false;
        }

        emp.setSal(newSal);
        return empDao.update(emp) > 0;
    }

    @Override
    public boolean transferDept(Integer empno, Integer newDeptno) {
        // 业务逻辑：验证新部门是否存在
        if (!deptDao.existsByDeptno(newDeptno)) {
            System.err.println("错误：部门编号 " + newDeptno + " 不存在！");
            return false;
        }

        Emp emp = empDao.selectByEmpno(empno);
        if (emp == null) {
            System.err.println("错误：员工编号 " + empno + " 不存在！");
            return false;
        }

        emp.setDeptno(newDeptno);
        return empDao.update(emp) > 0;
    }

    @Override
    public int countEmpsByDept(Integer deptno) {
        List<Emp> empList = empDao.selectByDeptno(deptno);
        return empList != null ? empList.size() : 0;
    }

    @Override
    public double getAvgSalByDept(Integer deptno) {
        List<Emp> empList = empDao.selectByDeptno(deptno);
        if (empList == null || empList.isEmpty()) {
            return 0.0;
        }

        int totalSal = 0;
        for (Emp emp : empList) {
            totalSal += emp.getSal();
        }

        return (double) totalSal / empList.size();
    }
}