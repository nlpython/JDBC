package exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static List<Emp> findAll() throws ClassNotFoundException, SQLException {
        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb", "root", "root");
        // 3.定义sql
        String sql = "select * from emp";
        // 4.获取执行sql的对象
        Statement stmt = conn.createStatement();
        // 5.执行sql
        ResultSet rs = stmt.executeQuery(sql);

        // 6.遍历结果, 封装对象
        Emp emp = null;
        List<Emp> list = new ArrayList<>();;
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("ename");
            int job_id = rs.getInt("job_id");
            int mgr = rs.getInt("mgr");
            Date joinDate = rs.getDate("joinDate");
            double salary = rs.getDouble("bonus");
            int dept_id = rs.getInt("dept_id");

            // 开始封装
            emp = new Emp(id, name, job_id, mgr, joinDate, salary, dept_id);
            list.add(emp);
        }
        // 7.释放资源
        rs.close();
        stmt.close();
        conn.close();

        return list;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<Emp> list = findAll();
        list.stream().forEach(emp-> System.out.println(emp));
    }
}
