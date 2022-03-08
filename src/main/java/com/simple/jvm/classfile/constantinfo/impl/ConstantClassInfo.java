package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantClassInfo implements ConstantInfo {

    public ConstantPool constantPool;   //  常量池
    public int nameIdx;                 //  常量池索引，指向CONSTANT_Utf8_info常量

    public ConstantClassInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        nameIdx = reader.readU2();
    }

    @Override
    public int getTag() {
        return CONSTANT_CLASS;
    }

    public String getName() {
        return constantPool.getUTF8(nameIdx);
    }

}
