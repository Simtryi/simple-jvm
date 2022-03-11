package com.simple.jvm.instructions.base.impl;

import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.Frame;

/**
 * 跳转指令
 */
public class BranchInstruction implements Instruction {

    protected int offset;   //  跳转偏移量

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }

}
