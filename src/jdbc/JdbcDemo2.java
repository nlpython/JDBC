package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  JDBC事务处理解决银行转账案例
 *  (事务管理和回滚)
 */

public class JdbcDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try{
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb", "root", "root");
            // 开启事务
            conn.setAutoCommit(false);
            // 3.定义sql
            String sql1 = "update account set balance = balance - ? where id = ?";
            String sql2 = "update account set balance = balance + ? where id = ?";

            // 4.获取PreparedStatement
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            // 5.设置参数
            pstmt1.setDouble(1, 500);
            pstmt2.setDouble(1, 500);

            pstmt1.setInt(2, 1);
            pstmt2.setInt(2, 2);
            // 6.执行sql
            pstmt1.executeUpdate();
//            int a = 1/ 0;  // 实行sql过程中中途出现异常, 会进行数据回滚
            pstmt2.executeUpdate();

            // 提交事务

            conn.commit();

        } catch (Exception throwables) {
            // 事务回滚(出现任何异常进行数据回滚)
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            // 7.释放资源
            try {
                pstmt1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                pstmt2.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
