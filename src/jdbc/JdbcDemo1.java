package jdbc;

import java.sql.*;

public class JdbcDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb", "root", "root");
        // 3. 定义sql语句
        String sql = "update t_student set age = 10 where sno = 1";
        // 4. 获取执行sql的对象 Statement
        Statement stmt = connection.createStatement();
        // 5. 执行sql
        int count = stmt.executeUpdate(sql);
        ResultSet rs = stmt.executeQuery("select * from t_student");

        // 6. 处理结果
        System.out.println(count);
        // 让游标向下移动一行(最开始为标题栏)
        // 判断是都否数据.
        while (rs.next()) {
            // 获取数据
            int anInt = rs.getInt(1);
            String name = rs.getString("sname");
            System.out.println(anInt + " " + name);
        }
        // 7. 释放资源
        rs.close();
        stmt.close();
        connection.close();
    }
}
