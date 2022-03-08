package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantMethodHandleInfo implements ConstantInfo {

    private int referenceKind;
    private int referenceIdx;

    @Override
    public void readInfo(ClassReader reader) {
        referenceKind = reader.readU1();
        referenceIdx = reader.readU2();
    }

    @Override
    public int getTag() {
        return CONSTANT_METHODHANDLE;
    }

}
