package com.example.lzzll.javastudy.jdbctemplate;

import com.example.lzzll.javastudy.jdbctemplate.domain.Student;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 使用template对象执行增删改的操作
 * 流程:
 *      1.导包(五个jar包)
 *      2.创建template对象,将数据库连接池作为参数传入其中
 *      3.定义sql语句
 *      4.执行sql语句
 * 使用template对象和单元测试Junit完成以下需求,数据库名为edu_test ; 表名为student
 *      a.修改步惊云的数学成绩为90;
 *      b.增加一条数据 ("破军", 80 , 80 , 80)
 *      c.删除破军的数据
 *      d.查询聂风的数据,并将结果封装成一个map集合
 *      e.查询所有的数据,将结果封装成一个装载map的list集合
 *      f.查询所有的数据,将结果封装成一个装在student对象的list集合
 *      g.查询表中数据的条数
 *      h.遍历数学成绩大于85的人的信息
 *
 *  注意:
 *      a.注意temp方法的返回值是[类对象]的list集合的时候,其中的可变参传递需要创建一个Object对象,在大括号中传参. new Object(){传入的参数}
 *          参数包括日期类型
 *      b.sql语句中,通配符?可以单独定义在小括号中,例如"h"的实例.
 *
 *
 */

public class TotalTest {

    // 提升temp对象的定义域
    private static JdbcTemplate temp = null;
    // 定义一个静态代码块获取template对象
    static {
        // [1] 获取temp对象
        temp = new JdbcTemplate(DSUtils.getDataSource());
    }

    /**
     *  a.修改步惊云的数学成绩为90;
     */
    // 定义一个方法完成修改数据的功能
    @Test
    public void change() {
        // [2] 定义sql语句
        String sql = "update student set math = ? where name = ?";
        // [3] 执行sql语句
        int count = temp.update(sql, 90, "步惊云");
        System.out.println(count);
    }

    /**
     * b.增加一条数据 ("破军", 80 , 80 , 80)
     */
    // 定义一个方法完成增加数据的功能
    @Test
    public void add() {
        // [2] 定义sql语句
        String sql = "insert into student values (null,?,?,?,?)";
        // [3] 执行sql语句
        int count = temp.update(sql, "破军", 80, 80, 80);
        System.out.println(count);
    }

    /**
     * c.删除破军的数据
     */
    @Test
    public void delete() {
        // [2] 定义sql语句
        String sql = "delete from student where name = ?";
        // [3] 执行sql语句
        int count = temp.update(sql, "破军");
        System.out.println(count);
    }

    /**
     * d.查询聂风的数据,并将结果封装成一个map集合
     */
    @Test
    public void setForMap() {
        // [2] 定义sql语句
        String sql = "select * from student where name = ?";
        // [3] 执行sql语句
        Map<String, Object> stringObjectMap = temp.queryForMap(sql,"聂风");
        System.out.println(stringObjectMap);
    }

    /**
     * e.查询所有的数据,将结果封装成一个装载map的list集合
     */
    @Test
    public void setForList1() {
        // [2] 定义sql语句
        String sql = "select * from student WHERE chinese > (?)";
        // [3] 执行sql语句
        List<Map<String, Object>> list = temp.queryForList(sql,75);
        // 遍历list集合
        System.out.println("集合的长度为:" + list.size());
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    /**
     * f.查询所有的数据,将结果封装成一个装在student对象的list集合
     */
    @Test
    public void setForList2_1() {
        // [2] 定义sql语句
        String sql = "select * from student";
        // [3] 执行sql语句
        List<Object> list = temp.query(sql, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {

                // 在结果集中通过键找值得方式获取字段的值
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Float chinese = rs.getFloat("chinese");
                double math = rs.getDouble("math");
                float english = rs.getFloat("english");

                // 创建学生对象并赋值
                Student student = new Student(id, name, chinese, math, english);

                return student;
            }
        });
        // 遍历集合
        for (Object student : list) {
            System.out.println(student);
        }
    }

    /**
     * f.查询所有的数据,将结果封装成一个装在student对象的list集合
     */
    @Test
    public void setForList2_2() {
        // [2] 定义sql语句
        String sql = "select * from student";
        // [3] 执行sql语句
        List<Student> list = temp.query(sql, new BeanPropertyRowMapper<>(Student.class));
        // 遍历集合
        for (Student student : list) {
            System.out.println(student);
        }
    }

    /**
     *  g.查询表中数据的条数
     *
     */
    @Test
    public void count() {
        // [2] 定义sql语句
        String sql = "select count(id) from student";
        // [3] 执行sql语句
        Long number = temp.queryForObject(sql, Long.class);
        System.out.println("数据的条数为:" + number);
    }

    /**
     *  h.遍历数学成绩大于85的人的信息
     *
     *  // 注意temp方法的返回值是类对象的list集合的时候,其中的可变参传递需要创建一个Object对象,在大括号中传参.
     */
    @Test
    public void look() {
        // [2] 定义sql语句
        String sql = "select * from student where math > (?)";
        // [3] 执行sql语句
        List<Student> list = temp.query(sql, new Object[]{75}, new BeanPropertyRowMapper<>(Student.class));
        // 遍历集合
        for (Student student : list) {
            System.out.println(student);
        }
    }
}
