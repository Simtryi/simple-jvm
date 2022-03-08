package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantFloatInfo implements ConstantInfo {

    private float val;

    @Override
    public void readInfo(ClassReader reader) {
        //  CONSTANT_Float_info使用8字节存储IEEE754单精度浮点数常量
        val = reader.readU8ToFloat();
    }

    @Override
    public int getTag() {
        return CONSTANT_FLOAT;
    }

    public float getVal() {
        return val;
    }

}
