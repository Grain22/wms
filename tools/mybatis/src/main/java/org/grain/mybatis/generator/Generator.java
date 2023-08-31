package org.grain.mybatis.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import org.apache.ibatis.annotations.Mapper;

import java.io.File;

/**
 * @author grain
 */
public class Generator {
    public static final String URL = "jdbc:mysql://10.201.15.86:3306/cf_rfms";
    public static final String username = "cfgdc";
    public static final String password = "Qwer1234@cfgdc";


    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(URL, username, password).build();
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        autoGenerator.global(new GlobalConfig.Builder().author("grain")
                .outputDir(System.getProperty("user.dir") + File.separator + "target")
                .enableSwagger()
                .build()
        );
        autoGenerator.packageInfo(new PackageConfig.Builder()
                .parent("org.grain")
                .build()
        );
        autoGenerator.strategy(new StrategyConfig.Builder()
                .addInclude("rfm_film_released_view")
                .entityBuilder()
                .enableColumnConstant()
                .enableChainModel()
                .enableLombok()
                .enableTableFieldAnnotation()
                .enableFileOverride()
                .controllerBuilder()
                .enableRestStyle()
                .enableFileOverride()
                .serviceBuilder()
                .enableFileOverride()
                .mapperBuilder()
                .mapperAnnotation(Mapper.class)
                .enableFileOverride()
                .enableBaseResultMap()
                .enableBaseColumnList()
                .build());
        autoGenerator.execute();
    }
}
