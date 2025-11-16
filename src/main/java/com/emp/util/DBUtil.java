package com.emp.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接工具类
 * 负责数据库连接的获取和资源的释放
 */
public class DBUtil {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 静态代码块：加载配置文件和驱动
    static {
        try {
            // 加载配置文件
            Properties props = new Properties();
            InputStream is = DBUtil.class.getClassLoader()
                    .getResourceAsStream("db.properties");
            props.load(is);

            // 读取配置
            driver = props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");

            // 加载驱动
            Class.forName(driver);
            System.out.println("数据库驱动加载成功！");

        } catch (ClassNotFoundException e) {
            System.err.println("数据库驱动加载失败：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("配置文件加载失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return Connection对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 关闭数据库资源
     * @param conn 连接对象
     * @param stmt 语句对象
     * @param rs 结果集对象
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库资源（重载方法，不包含ResultSet）
     */
    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    /**
     * 测试数据库连接
     */
    public static void testConnection() {
        Connection conn = null;
        try {
            conn = getConnection();
            if (conn != null) {
                System.out.println("数据库连接成功！");
                System.out.println("连接信息：" + conn.toString());
            }
        } catch (SQLException e) {
            System.err.println("数据库连接失败：" + e.getMessage());
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    /**
     * 主方法：用于测试数据库连接
     */
    public static void main(String[] args) {
        testConnection();
    }
}