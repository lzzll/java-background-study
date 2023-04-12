package com.example.lzzll.javastudy.jdbctemplate;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 测试用数据库常量池和不用常量池两种方式的效率(分别打印两种方式执行相同程序的时间)
 * 需求:
 *      a.不用数据库常量池的技术将聂风的语文成绩改为90;
 *      b.用数据库常量池的技术将聂风的数学成绩改为90;
 */

public class CompareTest {

    /**
     * a.不用数据库常量池的技术将聂风的语文成绩改为90;
     */
    @Test
    public void changeChinese() {
        // [1] 打印程序执行前的时间
        long time1 = System.currentTimeMillis();

        // 提升作用域
        Connection conn = null;
        PreparedStatement pstat =null;
        try {
            // 创建properties集合对象
            Properties pro = new Properties();
            // 创建流对象
            InputStream is = ClassLoader.getSystemResourceAsStream("druid.properties");
            pro.load(is);
            // [2] 加载驱动
            String driverClassName = pro.getProperty("driverClassName");
            Class.forName(driverClassName);
            // [3] 获取连接对象
            String url = pro.getProperty("url");
            String username = pro.getProperty("username");
            String password = pro.getProperty("password");
            conn = DriverManager.getConnection(url, username, password);
            // [4] 获取执行对象
            String sql = "update student set chinese = ? where name = ?";
            pstat = conn.prepareStatement(sql);
            // [5] 赋值
            pstat.setInt(1,90);
            pstat.setString(2,"聂风");
            // [6] 执行sql
            int count = pstat.executeUpdate();
            System.out.println(count > 0 ? "执行成成功":"执行失败");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 释放资源
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        long time2 = System.currentTimeMillis();
        System.out.println("执行本次程序所花费的时长为:"+(time2-time1));

    }

    /**
     *  b.用c3p0数据库常量池的技术将聂风的数学成绩改为90;
     */
    @Test
    public void changeMath() {
        // [1] 打印程序执行前的时间
        long time1 = System.currentTimeMillis();

        // 提升作用域
        Connection conn = null;
        PreparedStatement pstat =null;

        try {
            // [2] 获取c3p0数据库连接池对象
//            ComboPooledDataSource ds = new ComboPooledDataSource();
            // [3] 获取连接对象
//            conn = ds.getConnection();
            // [4] 获取执行对象
            String sql = "update student set math = ? where name = ?";
            pstat = conn.prepareStatement(sql);
            // [5] 赋值
            pstat.setInt(1,90);
            pstat.setString(2,"聂风");
            // [6] 执行sql
            int count = pstat.executeUpdate();
            System.out.println(count > 0 ? "执行成成功":"执行失败");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 释放资源
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        long time2 = System.currentTimeMillis();
        System.out.println("执行本次程序所花费的时长为:"+(time2-time1));
    }

    /**
     *  b.用druid数据库常量池的技术将聂风的英语成绩改为90;
     */
    @Test
    public void changeEnglish() {
        // [1] 打印程序执行前的时间
        long time1 = System.currentTimeMillis();

        // 提升作用域
        Connection conn = null;
        PreparedStatement pstat = null;

        try {
            // 创建properties对象
            Properties pro = new Properties();
            // 获取流对象
            InputStream is = ClassLoader.getSystemResourceAsStream("druid.properties");
            // [2] 加载配置文件
            pro.load(is);
            // [3] 获取连接池对象
            DataSource ds = DruidDataSourceFactory.createDataSource(pro);
            // [4] 获取连接对象
            conn = ds.getConnection();
            // [5] 获取执行对象
            String sql = "update student set english = ? where name = ?";
            pstat = conn.prepareStatement(sql);
            // [6] 赋值
            pstat.setInt(1,90);
            pstat.setString(2,"聂风");
            // [7] 执行sql
            int count = pstat.executeUpdate();
            System.out.println(count > 0 ? "执行成功" : "执行失败");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 释放资源
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        long time2 = System.currentTimeMillis();
        System.out.println("执行本次程序所花费的时长为:"+(time2-time1));


    }
}
