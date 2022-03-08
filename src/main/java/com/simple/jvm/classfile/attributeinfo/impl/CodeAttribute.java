package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class CodeAttribute implements AttributeInfo {

    private ConstantPool constantPool;          //  常量池
    private int maxStack;                       //  操作数栈的最大深度
    private int maxLocals;                      //  局部变量表大小
    private byte[] data;                        //  字节码
    private ExceptionTableEntry[] exceptions;   //  异常处理表
    private AttributeInfo[] attributes;         //  属性表

    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        maxStack = reader.readU2();
        maxLocals = reader.readU2();
        int dataLength = (int) reader.readU4();
        data = reader.readBytes(dataLength);
        exceptions = ExceptionTableEntry.readExceptionTable(reader);
        attributes = AttributeInfo.read(reader, constantPool);
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getData() {
        return data;
    }

    public ExceptionTableEntry[] getExceptions() {
        return exceptions;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }



    static class ExceptionTableEntry {

        private int startPC;
        private int endPC;
        private int handlerPC;
        private int catchType;

        ExceptionTableEntry(int startPC, int endPC, int handlerPC, int catchType) {
            this.startPC = startPC;
            this.endPC = endPC;
            this.handlerPC = handlerPC;
            this.catchType = catchType;
        }

        static ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
            int exceptionTableLength = reader.readU2();
            ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
            for (int i = 0; i < exceptionTableLength; i++) {
                exceptionTable[i] = new ExceptionTableEntry(reader.readU2(), reader.readU2(), reader.readU2(), reader.readU2());
            }
            return exceptionTable;
        }

        public int getStartPC() {
            return startPC;
        }

        public int getEndPC() {
            return endPC;
        }

        public int getHandlerPC() {
            return handlerPC;
        }

        public int getCatchType() {
            return catchType;
        }

    }
}
