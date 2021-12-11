package DataBaseLinkedPool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 *  druid
 */

public class druid {
    public static void main(String[] args) throws Exception {
        // 1.导入jar包
        // 2.加载配置文件
        Properties pro = new Properties();
        InputStream is = druid.class.getClassLoader().getResourceAsStream("Druid.properties");
        pro.load(is);

        // 3.获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        // 4.获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
