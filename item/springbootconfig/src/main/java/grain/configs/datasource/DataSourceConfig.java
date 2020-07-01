package grain.configs.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wulifu
 */
@Configuration
public class DataSourceConfig {

    /*@Bean*/
    public DataSource createDateSource() {
        List<DataSource> dataSources = new ArrayList<>();
        dataSources.add(createDataSource(new Properties()));
        return new MyDataSource(dataSources);
    }

    private DataSource createDataSource(Properties properties) {
        HikariConfig hikariConfig = new HikariConfig(properties);
        return new HikariDataSource(hikariConfig);
    }

}
