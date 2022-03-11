package com.simple.jvm.instructions.base.impl;

import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.Frame;

/**
 * 没有操作数的指令
 */
public class NoOperandsInstruction implements Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {
        //  empty
    }

    @Override
    public void execute(Frame frame) {
        //  nothing to do
    }

}
