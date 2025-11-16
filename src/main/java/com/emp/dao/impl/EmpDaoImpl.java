package com.emp.dao.impl;

import com.emp.dao.EmpDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;
import com.emp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工DAO实现类
 */
public class EmpDaoImpl implements EmpDao {

    @Override
    public int insert(Emp emp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, emp.getEmpno());
            pstmt.setString(2, emp.getEname());
            pstmt.setString(3, emp.getJob());
            if (emp.getMgr() != null) {
                pstmt.setInt(4, emp.getMgr());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setDate(5, emp.getHiredate());
            pstmt.setInt(6, emp.getSal());
            if (emp.getComm() != null) {
                pstmt.setInt(7, emp.getComm());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            pstmt.setInt(8, emp.getDeptno());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    @Override
    public int deleteByEmpno(Integer empno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM EMP WHERE EMPNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empno);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    @Override
    public int update(Emp emp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE EMP SET ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? " +
                    "WHERE EMPNO=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getEname());
            pstmt.setString(2, emp.getJob());
            if (emp.getMgr() != null) {
                pstmt.setInt(3, emp.getMgr());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setDate(4, emp.getHiredate());
            pstmt.setInt(5, emp.getSal());
            if (emp.getComm() != null) {
                pstmt.setInt(6, emp.getComm());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            pstmt.setInt(7, emp.getDeptno());
            pstmt.setInt(8, emp.getEmpno());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    @Override
    public Emp selectByEmpno(Integer empno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM EMP WHERE EMPNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractEmp(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Emp> selectAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Emp> empList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM EMP ORDER BY EMPNO";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                empList.add(extractEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return empList;
    }

    @Override
    public List<Emp> selectByDeptno(Integer deptno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Emp> empList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM EMP WHERE DEPTNO = ? ORDER BY EMPNO";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deptno);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                empList.add(extractEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return empList;
    }

    @Override
    public List<Emp> selectByEname(String ename) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Emp> empList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM EMP WHERE ENAME LIKE ? ORDER BY EMPNO";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + ename + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                empList.add(extractEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return empList;
    }

    @Override
    public List<Emp> selectByJob(String job) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Emp> empList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM EMP WHERE JOB = ? ORDER BY EMPNO";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, job);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                empList.add(extractEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return empList;
    }

    @Override
    public boolean existsByEmpno(Integer empno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM EMP WHERE EMPNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Emp> selectAllWithDept() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Emp> empList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT e.*, d.DNAME, d.LOC, m.ENAME AS MGR_NAME " +
                    "FROM EMP e " +
                    "LEFT JOIN DEPT d ON e.DEPTNO = d.DEPTNO " +
                    "LEFT JOIN EMP m ON e.MGR = m.EMPNO " +
                    "ORDER BY e.EMPNO";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                empList.add(extractEmpWithDept(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return empList;
    }

    @Override
    public Emp selectByEmpnoWithDept(Integer empno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT e.*, d.DNAME, d.LOC, m.ENAME AS MGR_NAME " +
                    "FROM EMP e " +
                    "LEFT JOIN DEPT d ON e.DEPTNO = d.DEPTNO " +
                    "LEFT JOIN EMP m ON e.MGR = m.EMPNO " +
                    "WHERE e.EMPNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractEmpWithDept(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Emp> selectBySalRange(Integer minSal, Integer maxSal) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Emp> empList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM EMP WHERE SAL BETWEEN ? AND ? ORDER BY SAL DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, minSal);
            pstmt.setInt(2, maxSal);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                empList.add(extractEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return empList;
    }

    /**
     * 从ResultSet中提取Emp对象
     */
    private Emp extractEmp(ResultSet rs) throws SQLException {
        Emp emp = new Emp();
        emp.setEmpno(rs.getInt("EMPNO"));
        emp.setEname(rs.getString("ENAME"));
        emp.setJob(rs.getString("JOB"));
        int mgr = rs.getInt("MGR");
        emp.setMgr(rs.wasNull() ? null : mgr);
        emp.setHiredate(rs.getDate("HIREDATE"));
        emp.setSal(rs.getInt("SAL"));
        int comm = rs.getInt("COMM");
        emp.setComm(rs.wasNull() ? null : comm);
        emp.setDeptno(rs.getInt("DEPTNO"));
        return emp;
    }

    /**
     * 从ResultSet中提取Emp对象（包含部门信息）
     */
    private Emp extractEmpWithDept(ResultSet rs) throws SQLException {
        Emp emp = extractEmp(rs);

        // 设置部门信息
        Dept dept = new Dept();
        dept.setDeptno(emp.getDeptno());
        dept.setDname(rs.getString("DNAME"));
        dept.setLoc(rs.getString("LOC"));
        emp.setDept(dept);

        // 设置经理姓名
        emp.setMgrName(rs.getString("MGR_NAME"));

        return emp;
    }
}