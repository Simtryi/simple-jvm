package com.simple.jvm.rtda.heap.constantpool;

import com.simple.jvm.rtda.heap.methodarea.Class;

public class SymRef {

    public RunTimeConstantPool runTimeConstantPool; //  运行时常量池
    public String className;                        //  类的完全限定名
    public Class clazz;                             //  解析后的类

    /**
     * 解析类符号引用
     */
    public Class resolvedClass() {
        if (null != clazz) {
            return clazz;
        }
        Class d = runTimeConstantPool.getClazz();
        Class c = d.loader.loadClass(className);;
        clazz = c;
        return clazz;
    }

}
