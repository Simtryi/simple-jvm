package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class SourceFileAttribute implements AttributeInfo {

    private ConstantPool constantPool;  //  常量池
    private int sourceFileIdx;          //  源文件名索引，指向CONSTANT_Utf8_info常量

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        //  attribute_length的值必须是2
        sourceFileIdx = reader.readU2();
    }

    public String getFileName() {
        return constantPool.getUTF8(sourceFileIdx);
    }

}
