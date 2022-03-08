package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class ExceptionsAttribute implements AttributeInfo {

    private int[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) {
        exceptionIndexTable = reader.readU2s();
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

}
