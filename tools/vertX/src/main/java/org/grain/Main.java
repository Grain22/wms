package org.grain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public class Main {
    public static final Map<String, Object> MAP = new ConcurrentHashMap<>();
    public static final Map<String, Future<?>> job_map = new ConcurrentHashMap<>();
    public static final Map<String, Class<? extends Runnable>> job_runnable_map = new HashMap<>();
    public static final Object job_manage_lock = new Object();
}