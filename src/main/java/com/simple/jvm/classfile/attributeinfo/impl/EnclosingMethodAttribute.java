package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

import java.util.HashMap;
import java.util.Map;

public class EnclosingMethodAttribute implements AttributeInfo {

    private ConstantPool constantPool;
    private int classIdx;
    private int methodIdx;

    public EnclosingMethodAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        classIdx = reader.readU2();
        methodIdx = reader.readU2();
    }

    public String getClassName() {
        return constantPool.getClassName(classIdx);
    }

    public Map<String, String> getMethodNameAndDescriptor() {
        if (methodIdx <= 0) {
            return new HashMap<>();
        }
        return constantPool.getNameAndType(methodIdx);
    }

}
