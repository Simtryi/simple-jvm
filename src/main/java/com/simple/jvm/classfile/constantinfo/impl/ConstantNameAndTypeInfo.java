package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantNameAndTypeInfo implements ConstantInfo {

    public int nameIdx;     //  字段或方法名索引，指向CONSTANT_Utf8_info常量
    public int descIdx;     //  字段或方法描述符索引，指向CONSTANT_Utf8_info常量

    @Override
    public void readInfo(ClassReader reader) {
        nameIdx = reader.readU2();
        descIdx = reader.readU2();
    }

    @Override
    public int getTag() {
        return CONSTANT_NAMEANDTYPE;
    }

}
