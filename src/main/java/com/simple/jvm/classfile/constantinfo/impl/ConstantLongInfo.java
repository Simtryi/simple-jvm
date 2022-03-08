package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantLongInfo implements ConstantInfo {

    private long val;

    @Override
    public void readInfo(ClassReader reader) {
        //  CONSTANT_Long_info使用8字节存储整数常量
        val = reader.readU8ToLong();
    }

    @Override
    public int getTag() {
        return CONSTANT_LONG;
    }

    public long getVal() {
        return val;
    }

}
