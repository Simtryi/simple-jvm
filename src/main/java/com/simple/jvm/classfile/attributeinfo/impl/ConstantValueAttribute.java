package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class ConstantValueAttribute implements AttributeInfo {

    private int constantValueIdx;   //  常量池索引，具体指向哪种常量因字段类型而异

    @Override
    public void readInfo(ClassReader reader) {
        //  attribute_length的值必须是2
        constantValueIdx = reader.readU2();
    }

    public int getConstantValueIdx() {
        return constantValueIdx;
    }

}
