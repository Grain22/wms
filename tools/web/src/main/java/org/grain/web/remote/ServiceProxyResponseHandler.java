package org.grain.web.remote;

import java.lang.reflect.Method;

public interface ServiceProxyResponseHandler {
    Object invoke(Method method, Object args);
}
