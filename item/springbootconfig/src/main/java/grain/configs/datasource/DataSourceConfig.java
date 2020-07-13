package grain.configs.datasource;

/**
 * @author wulifu
 */
/*@Configuration*/
public class DataSourceConfig {
/*
    *//*@Bean*//*
    public DataSource createDateSource() {
        List<DataSource> dataSources = new ArrayList<>();
        dataSources.add(createDataSource(new Properties()));
        return new MyDataSource(dataSources);
    }

    @NotNull
    private DataSource createDataSource(Properties properties) {
        HikariConfig hikariConfig = new HikariConfig(properties);
        return new HikariDataSource(hikariConfig);
    }*/

}
