package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantMethodTypeInfo implements ConstantInfo {

    private int descriptorIdx;

    @Override
    public void readInfo(ClassReader reader) {
        descriptorIdx = reader.readU2();
    }

    @Override
    public int getTag() {
        return CONSTANT_METHODTYPE;
    }

}
