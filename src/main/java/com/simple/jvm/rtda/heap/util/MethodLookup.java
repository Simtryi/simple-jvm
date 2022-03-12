package com.simple.jvm.rtda.heap.util;

import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Method;

public class MethodLookup {

    /**
     * 从类的继承中查找方法
     */
    static public Method lookupMethodInClass(Class clazz, String name, String descriptor) {
        for (Class c = clazz; c != null; c = c.superClass) {
            for (Method method : c.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * 从类的接口中查找方法
     */
    static public Method lookupMethodInInterfaces(Class[] ifaces, String name, String descriptor) {
        for (Class inface : ifaces) {
            for (Method method : inface.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

}
