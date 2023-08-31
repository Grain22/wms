package org.grain.web.remote;

import java.lang.reflect.Method;

/**
 * 请求数据生成器
 */
public interface ServiceProxyParamHandler {
    /**
     * 生成请求需要发送的数据,自定义处理过程
     *
     * @param method 当前方法
     * @param args   参数表
     * @return 请求体 字符串类型 需重写{@link Object#toString()}方法
     */
    Object invoke(Method method, Object[] args);
}
