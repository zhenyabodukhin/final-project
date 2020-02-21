package com.htp.aspect;

import java.util.Map;

public interface MethodCallCounter {
    void count(String method);
    Map<String, Integer> getMethodCounter();
}
