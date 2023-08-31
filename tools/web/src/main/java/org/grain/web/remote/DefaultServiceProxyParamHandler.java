package org.grain.web.remote;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class DefaultServiceProxyParamHandler implements ServiceProxyParamHandler {
    @Override
    public String invoke(Method method, Object[] args) {
        Map<String, Object> param = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            param.put(parameter.getName(), args[i]);
        }
        JsonObjectBuilder builder = Json.createObjectBuilder(param);
        return builder.build().toString();
    }
}
