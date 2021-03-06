package com.simple.jvm.instructions.comparisons.if_acmp;

import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.instructions.base.impl.BranchInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

public class IF_ACMPNE extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Object ref2 = operandStack.popRef();
        Object ref1 = operandStack.popRef();

        if (!ref1.equals(ref2)) {
            Instruction.branch(frame, offset);
        }
    }

}