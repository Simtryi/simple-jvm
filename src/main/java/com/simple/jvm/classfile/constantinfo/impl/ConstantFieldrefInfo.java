package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

import java.util.Map;

public class ConstantFieldrefInfo implements ConstantInfo {

    private ConstantPool constantPool;  //  常量池
    private int classIdx;               //  常量池索引，指向CONSTANT_Class_info常量
    private int nameAndTypeIdx;         //  常量池索引，指向CONSTANT_NameAndType_info常量

    public ConstantFieldrefInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        classIdx = reader.readU2();
        nameAndTypeIdx = reader.readU2();
    }

    @Override
    public int getTag() {
        return CONSTANT_FIELDREF;
    }

    public String getClassName() {
        return constantPool.getClassName(classIdx);
    }

    public Map<String, String> getNameAndDescriptor() {
        return constantPool.getNameAndType(nameAndTypeIdx);
    }

}
