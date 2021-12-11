package SpringJDBC;

import DataBaseLinkedPool.Utils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *  JdbcTemplate
 */

public class demo1 {
    public static void main(String[] args) {
        // 1.导入jar包
        // 2.创建JDBCTemplate
        DataSource ds = Utils.getDataSourse();
        JdbcTemplate template = new JdbcTemplate(ds);

        // 3.调用方法
        String sql = "update account set balance = balance + 500 where id = ?";
        int count = template.update(sql, 1);  // 1表示第一个?的值
        System.out.println(count);
    }
}
