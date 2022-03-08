package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class UnparsedAttribute implements AttributeInfo {

    private String name;
    private int length;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen) {
        name = attrName;
        length = attrLen;
    }

    @Override
    public void readInfo(ClassReader reader) {
        info = reader.readBytes(length);
    }

    public byte[] getInfo() {
        return info;
    }

}
