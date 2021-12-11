package DataBaseLinkedPool;

import com.mchange.v2.c3p0.ConnectionTester;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class testUtils {
    public static void main(String[] args) throws SQLException {
        // 1.获取连接
        Connection conn = Utils.getConnection();
        // 2.定义sql
        String sql = "insert into account values(3, ?)";
        // 3.获取PrepareStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, 7000);
        // 4.执行sql
        pstmt.executeUpdate();
        // 5.释放资源
        Utils.close(pstmt, conn);
    }
}
