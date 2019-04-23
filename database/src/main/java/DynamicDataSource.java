import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *  @author     laowu
 *  @version    4/15/2019 5:46 PM
 *
*/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }
}
