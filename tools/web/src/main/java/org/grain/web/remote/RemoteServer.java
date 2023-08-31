package org.grain.web.remote;

import java.lang.annotation.*;

/**
 * remoteServer 注册注解
 * 用户远程自动通信
 * 类似 open feign
 *
 * @author Grain
 * @since 2023年6月30日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RemoteServer {
    /**
     * 服务端 主机地址
     *
     * @return 主机name
     */
    String name();
}
