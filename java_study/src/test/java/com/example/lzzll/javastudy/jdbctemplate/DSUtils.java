package com.example.lzzll.javastudy.jdbctemplate;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 定义一个数据库连接池的工具类
 */

public class DSUtils {

    // 定义一个私有化静态成员变量
    private static DataSource ds;

    // 定义一个静态代码块,来加载配置文件
    static {
        try {
            // [1] 创建一个properties集合
            Properties pro = new Properties();
            // [2] 获取配置文件的流对象
            InputStream is = ClassLoader.getSystemResourceAsStream("druid.properties");
            // [3] 加载配置文件
            pro.load(is);
            // [4] 获取数据库连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 定义一个方法获取数据库连接池对象
    public static DataSource getDataSource() {
        return ds;
    }

    // 定义一个方法获取连接对象
    public static Connection getConn() throws SQLException {
        Connection connection = ds.getConnection();
        return connection;
    }

    // 定义一个方法归还资源
    public static void release(PreparedStatement pstat , Connection conn) {
        // 判断pstat对象不为空
        if (pstat != null) {
            try {
                pstat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 定义一个重载方法归还资源
    public static void release(ResultSet rs , PreparedStatement pstat , Connection conn) {
        // 判断rs对象不为空
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstat != null) {
            try {
                pstat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
