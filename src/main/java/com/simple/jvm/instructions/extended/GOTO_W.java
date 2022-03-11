package com.simple.jvm.instructions.extended;

import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * 无条件跳转，索引是4字节
 */
public class GOTO_W implements Instruction {

    private int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt();
    }

    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, offset);
    }

}
