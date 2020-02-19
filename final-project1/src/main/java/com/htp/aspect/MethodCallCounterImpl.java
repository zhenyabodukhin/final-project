package com.htp.aspect;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("MethodCallCounter")
public class MethodCallCounterImpl implements MethodCallCounter {

    private Map<String,Integer> methodCounter;

    public  MethodCallCounterImpl() {
        methodCounter = new HashMap();
    }

    @Override
    public void count(String method) {
        if(method != null) {
            if(methodCounter.containsKey(method)) {
                methodCounter.put(method, (methodCounter.get(method) + 1));
            } else {
                methodCounter.put(method, 1);
            }
        }

    }

    @Override
    public Map<String, Integer> getMethodCounter() {
        return methodCounter;
    }
}
