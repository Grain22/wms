package grain.configs.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wulifu
 */
@Slf4j
public class DataSourceConfigProperties {
    private static List<Properties> propertiesList = new ArrayList<>();
    private static final String data_source_config_file_prefix = "data-source";
    private static final String data_source_config_file_suffix = "properties";

  }
