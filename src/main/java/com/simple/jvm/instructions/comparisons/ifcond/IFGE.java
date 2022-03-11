package com.simple.jvm.instructions.comparisons.ifcond;

import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.instructions.base.impl.BranchInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

public class IFGE extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int val = operandStack.popInt();

        if (val >= 0) {
            Instruction.branch(frame, offset);
        }
    }

}