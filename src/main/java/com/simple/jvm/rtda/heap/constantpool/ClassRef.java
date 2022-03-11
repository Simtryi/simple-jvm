package com.simple.jvm.rtda.heap.constantpool;

import com.simple.jvm.classfile.constantinfo.impl.ConstantClassInfo;

/**
 * 类符号引用
 */
public class ClassRef extends SymRef {

    /**
     * 根据class文件中存储的类常量创建类符号引用
     */
    public static ClassRef create(RunTimeConstantPool runTimeConstantPool, ConstantClassInfo classInfo) {
        ClassRef ref = new ClassRef();
        ref.runTimeConstantPool = runTimeConstantPool;
        ref.className = classInfo.getName();
        return ref;
    }

}
