package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantIntegerInfo implements ConstantInfo {

    private int val;

    @Override
    public void readInfo(ClassReader reader) {
        //  CONSTANT_Integer_info使用4字节存储整数常量
        val = reader.readU4ToInteger();
    }

    @Override
    public int getTag() {
        return CONSTANT_INTEGER;
    }

    public int getVal() {
        return val;
    }

}
