package com.simple.jvm.instructions.control;

import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.instructions.base.impl.BranchInstruction;
import com.simple.jvm.rtda.Frame;

/**
 * 无条件跳转
 */
public class GOTO extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, offset);
    }

}
