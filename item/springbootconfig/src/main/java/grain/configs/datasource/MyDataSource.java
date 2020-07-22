package grain.configs.datasource;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import javax.validation.constraints.NotEmpty;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author wulifu
 * @apiNote 可创建 内含主备的数据源,在某个数据源丢失后自动切换其他数据源
 * @see javax.sql.DataSource
 */
@Slf4j
public class MyDataSource implements DataSource {
    private List<DataSource> dataSources;
    private DataSource inUse;

    private void refresh() throws SQLException {
        Iterator<DataSource> iterator = dataSources.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            try {
                log.info("数据库连接测试 当前数据源编号为 {} 数据源总数 {} ", ++count, dataSources.size());
                Connection connection = iterator.next().getConnection();
                this.inUse = iterator.next();
                connection.close();
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new SQLException("全部数据源连接测试失败");
    }

    public MyDataSource(@NotEmpty List<DataSource> dataSources) {
        this.dataSources = dataSources;
        this.inUse = dataSources.get(0);
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return inUse.getConnection();
        } catch (Exception e) {
            log.info("当前数据库连接丢失");
            e.printStackTrace();
            refresh();
            return inUse.getConnection();
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        try {
            return inUse.getConnection(username, password);
        } catch (Exception e) {
            log.info("当前数据库连接丢失");
            e.printStackTrace();
            refresh();
            return inUse.getConnection(username, password);
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            return inUse.unwrap(iface);
        } catch (Exception e) {
            log.info("当前数据库连接丢失");
            e.printStackTrace();
            refresh();
            return inUse.unwrap(iface);
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            return inUse.isWrapperFor(iface);
        } catch (Exception e) {
            log.info("当前数据库连接丢失");
            e.printStackTrace();
            refresh();
            return inUse.isWrapperFor(iface);
        }
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        try {
            return inUse.getLogWriter();
        } catch (Exception e) {
            log.info("当前数据库连接丢失");
            e.printStackTrace();
            refresh();
            return inUse.getLogWriter();
        }
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        try {
            inUse.setLogWriter(out);
        } catch (Exception e) {
            log.info("当前数据库连接丢失");
            e.printStackTrace();
            refresh();
            inUse.setLogWriter(out);
        }
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        try {
            inUse.setLoginTimeout(seconds);
        } catch (Exception e) {
            log.info("当前数据库连接丢失");
            e.printStackTrace();
            refresh();
            inUse.setLoginTimeout(seconds);
        }
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        try {
            return inUse.getLoginTimeout();
        } catch (Exception e) {
            log.info("当前数据库连接丢失");
            e.printStackTrace();
            refresh();
            return inUse.getLoginTimeout();
        }
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return inUse.getParentLogger();
    }
}
