package com.emp.dao.impl;

import com.emp.dao.DeptDao;
import com.emp.entity.Dept;
import com.emp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门DAO实现类
 */
public class DeptDaoImpl implements DeptDao {

    @Override
    public int insert(Dept dept) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO DEPT (DEPTNO, DNAME, LOC) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dept.getDeptno());
            pstmt.setString(2, dept.getDname());
            pstmt.setString(3, dept.getLoc());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    @Override
    public int deleteByDeptno(Integer deptno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM DEPT WHERE DEPTNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deptno);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    @Override
    public int update(Dept dept) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE DEPT SET DNAME = ?, LOC = ? WHERE DEPTNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dept.getDname());
            pstmt.setString(2, dept.getLoc());
            pstmt.setInt(3, dept.getDeptno());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    @Override
    public Dept selectByDeptno(Integer deptno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM DEPT WHERE DEPTNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deptno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractDept(rs);
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
    public List<Dept> selectAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Dept> deptList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM DEPT ORDER BY DEPTNO";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                deptList.add(extractDept(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return deptList;
    }

    @Override
    public List<Dept> selectByDname(String dname) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Dept> deptList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM DEPT WHERE DNAME LIKE ? ORDER BY DEPTNO";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + dname + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                deptList.add(extractDept(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return deptList;
    }

    @Override
    public boolean existsByDeptno(Integer deptno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM DEPT WHERE DEPTNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deptno);
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

    /**
     * 从ResultSet中提取Dept对象
     */
    private Dept extractDept(ResultSet rs) throws SQLException {
        Dept dept = new Dept();
        dept.setDeptno(rs.getInt("DEPTNO"));
        dept.setDname(rs.getString("DNAME"));
        dept.setLoc(rs.getString("LOC"));
        return dept;
    }
}