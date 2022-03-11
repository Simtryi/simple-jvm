package com.simple.jvm.instructions.comparisons.ifcond;

import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.instructions.base.impl.BranchInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

public class IFEQ extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int val = operandStack.popInt();

        if (0 == val) {
            Instruction.branch(frame, offset);
        }
    }

}
