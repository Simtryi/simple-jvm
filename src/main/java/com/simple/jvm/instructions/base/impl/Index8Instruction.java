package com.simple.jvm.instructions.base.impl;

import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.Frame;

/**
 * 根据索引存取局部变量表，索引由单字节操作数给出
 */
public class Index8Instruction implements Instruction {

    public int idx; //  局部变量表索引

    @Override
    public void fetchOperands(BytecodeReader reader) {
        idx = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {

    }

}
