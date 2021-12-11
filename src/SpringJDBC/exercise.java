package SpringJDBC;

import DataBaseLinkedPool.Utils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import exercise.Emp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *  1.修改1号数据的bonus为10000
 *  2.添加一条记录
 *  3.删除刚才添加的记录
 *  4.查询id为1的记录, 将其封装为Map集合
 *  5.查询所有记录, 将其封装List
 *  6.查询所有记录, 将其封装为Emp对象的List集合
 *  7.查询总记录数
 */

public class exercise {

    private JdbcTemplate template = new JdbcTemplate(Utils.getDataSourse());

    // 1.修改1号数据的bonus为10000
    @Test
    public void test1() {
        // 定义sql
        String sql = "update emp set bonus = 10000 where id = 1001";
        template.update(sql);
    }

    // 2.添加一条记录
    @Test
    public void test2() {
        String sql = "insert into emp (id, ename, job_id, mgr, joinDate, bonus, dept_id) values(1007, '郭靖', 10, 1009, '2010-9-1', 8700, 20)";
        template.update(sql);
    }

    // 3.删除刚才添加的记录
    @Test
    public void test3() {
        String sql = "delete from emp where id = 1007";
        template.update(sql);
    }

    // 4.查询id为1的记录, 将其封装为Map集合
    // 注意: 这个方法只能查询一条记录, 将列名作为key, 数据作为value
    @Test
    public void test4() {
        String sql = "select * from emp where id = ?";
        Map<String, Object> map = template.queryForMap(sql, 1002);
        System.out.println(map);
    }

    // 5.查询所有记录, 将其封装List
    @Test
    public void test5() {
        String sql = "select * from emp";
        List<Map<String, Object>> maps = template.queryForList(sql);
        maps.stream().forEach(map-> System.out.println(map));
    }

    // 6.查询所有记录, 将其封装为Emp对象的List集合
    @Test
    public void test6() {
        String sql = "select * from emp";
//        List<Emp> list = template.query(sql, new RowMapper<Emp>() {
//            @Override
//            public Emp mapRow(ResultSet rs, int i) throws SQLException {
//                Emp emp = new Emp(rs.getInt("id"), rs.getString("ename"), rs.getInt("job_id"),
//                        rs.getInt("mgr"), rs.getDate("joinDate"), rs.getDouble("bonus"), rs.getInt("dept_id"));
 //            }
//        });
        // Lambda表达式写法
//        List<Emp> list = template.query(sql, (rs, i)->{
//            Emp emp = new Emp(rs.getInt("id"), rs.getString("ename"), rs.getInt("job_id"),
//                        rs.getInt("mgr"), rs.getDate("joinDate"), rs.getDouble("bonus"), rs.getInt("dept_id"));
//            return emp;
//        });
        // Spring框架简化写法
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        // 注意, 此事需将Emp中的基本数据类型转换为引用数据类型, 否则会不匹配. 且需要无参构造器
        list.stream().forEach(e-> System.out.println(e));
    }

    // 7.查询总记录数
    @Test
    public void test7() {
        String sql = "select count(id) from emp";


        Long count = template.queryForObject(sql, Long.class); // 第二个参数为返回值的类字节码文件
        System.out.println(count);
    }
}
