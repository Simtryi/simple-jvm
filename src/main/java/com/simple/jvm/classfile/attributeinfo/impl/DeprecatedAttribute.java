package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class DeprecatedAttribute implements AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {
        //  read nothing
    }

}
