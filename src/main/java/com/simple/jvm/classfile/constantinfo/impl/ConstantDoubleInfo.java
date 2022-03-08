package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantDoubleInfo implements ConstantInfo {

    private double val;

    @Override
    public void readInfo(ClassReader reader) {
        //  CONSTANT_Double_info使用8字节存储IEEE754双精度浮点数
        val = reader.readU8ToDouble();
    }

    @Override
    public int getTag() {
        return CONSTANT_DOUBLE;
    }

    public double getVal() {
        return val;
    }

}
