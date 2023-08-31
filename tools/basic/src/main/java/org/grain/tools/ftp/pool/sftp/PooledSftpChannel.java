package org.grain.tools.ftp.pool.sftp;

import com.jcraft.jsch.ChannelSftp;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author grain
 */
public class PooledSftpChannel extends DefaultPooledObject<ChannelSftp> {
    public PooledSftpChannel(ChannelSftp object) {
        super(object);
    }
}
