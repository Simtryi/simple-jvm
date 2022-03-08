package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class LineNumberTableAttribute implements AttributeInfo {

    private LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readU2();
        lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(reader.readU2(), reader.readU2());
        }
    }

    public int getLineNumber(int pc) {
        for (int i = lineNumberTable.length - 1; i >= 0; i--) {
            LineNumberTableEntry entry = lineNumberTable[i];
            if (pc >= entry.startPC){
                return entry.lineNumber;
            }
        }
        return -1;
    }

    static class LineNumberTableEntry {

        private int startPC;
        private int lineNumber;

        LineNumberTableEntry(int startPC, int lineNumber) {
            this.startPC = startPC;
            this.lineNumber = lineNumber;
        }

    }

}
