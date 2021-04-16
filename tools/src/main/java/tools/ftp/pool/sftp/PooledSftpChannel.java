package tools.ftp.pool.sftp;

import com.jcraft.jsch.ChannelSftp;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectState;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import javax.naming.OperationNotSupportedException;
import java.io.PrintWriter;
import java.util.Deque;

/**
 * @author wulifu
 */
public class PooledSftpChannel extends DefaultPooledObject<ChannelSftp> {
    public PooledSftpChannel(ChannelSftp object) {
        super(object);
    }
}
