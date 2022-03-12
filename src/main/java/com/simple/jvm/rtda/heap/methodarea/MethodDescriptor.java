package com.simple.jvm.rtda.heap.methodarea;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法描述符
 */
public class MethodDescriptor {

    public List<String> parameterTypes = new ArrayList<>(); //  参数类型列表
    public String returnType;                               //  返回值类型

    public void addParameterType(String type){
        parameterTypes.add(type);
    }

}
