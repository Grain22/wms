package org.grain.tools.ftp.pool.sftp;

import com.jcraft.jsch.ChannelSftp;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author grain
 */
public class PooledSftpChannelBuilder {
    public static GenericObjectPool<ChannelSftp> build() {
        GenericObjectPoolConfig<ChannelSftp> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setLifo(true);
        genericObjectPoolConfig.setMaxTotal(15);
        genericObjectPoolConfig.setMaxIdle(15);
        genericObjectPoolConfig.setMaxWait(Duration.of(20000, ChronoUnit.MILLIS));
        genericObjectPoolConfig.setMinIdle(0);
        genericObjectPoolConfig.setNumTestsPerEvictionRun(15);
        genericObjectPoolConfig.setTestOnBorrow(false);
        genericObjectPoolConfig.setTestOnReturn(false);
        genericObjectPoolConfig.setTestWhileIdle(true);
        genericObjectPoolConfig.setTestOnCreate(false);
        genericObjectPoolConfig.setTimeBetweenEvictionRuns(Duration.of(10000, ChronoUnit.MILLIS));
        return new GenericObjectPool<>(new PooledSftpChannelFactory("gansu","1qaz@WSX","10.1.63.26","19222"), genericObjectPoolConfig);
    }
}
