package com.simple.jvm.instructions.comparisons.if_acmp;

import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.instructions.base.impl.BranchInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

public class IF_ACMPEQ extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Object ref2 = operandStack.popRef();
        Object ref1 = operandStack.popRef();

        if (ref1.equals(ref2)) {
            Instruction.branch(frame, offset);
        }
    }

}
