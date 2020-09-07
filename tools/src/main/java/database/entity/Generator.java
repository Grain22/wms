package database.entity;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author wulifu
 */
public class Generator {
        private static String url = "jdbc:mysql://39.105.117.177:3306/test?serverTimezone=UTC";

        public static void main(String[] args) {
            Generator(args);
        }

        public static void Generator(String[] args) {
            //1. 全局配置
            GlobalConfig config = new GlobalConfig();
            config.setActiveRecord(true)
                    .setAuthor("Grain")
                    .setOutputDir(System.getProperty("user.dir") + "/outPut")
                    .setFileOverride(true)
                    .setIdType(IdType.AUTO)
                    .setServiceName("%sService")
                    .isSwagger2();
            //2. 数据源配置
            DataSourceConfig dsConfig = new DataSourceConfig();
            dsConfig.setDbType(DbType.MYSQL)
                    .setDriverName("com.mysql.jdbc.Driver")
                    .setUrl(url)
                    .setUsername("root")
                    .setPassword("1qaz@WSX");

            //3. 策略配置globalConfiguration中
            StrategyConfig stConfig = new StrategyConfig();
            stConfig.setCapitalMode(true)
                    .setNaming(NamingStrategy.underline_to_camel)
                    .setEntityLombokModel(true)
                    .setRestControllerStyle(true)
                    .setInclude("test");

            //4. 包名策略配置
            PackageConfig pkConfig = new PackageConfig();
            pkConfig.setParent("com.ebupt")
                    .setMapper("dao")
                    .setService("service")
                    .setController("controller")
                    .setEntity("entity")
                    .setXml("mapper");
            //5. 整合配置
            AutoGenerator ag = new AutoGenerator();
            ag.setGlobalConfig(config)
                    .setDataSource(dsConfig)
                    .setStrategy(stConfig)
                    .setPackageInfo(pkConfig);
            //6. 执行
            ag.execute();
        }
}
