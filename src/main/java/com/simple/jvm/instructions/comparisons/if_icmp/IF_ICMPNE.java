package com.simple.jvm.instructions.comparisons.if_icmp;

import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.instructions.base.impl.BranchInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

public class IF_ICMPNE extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int val2 = operandStack.popInt();
        int val1 = operandStack.popInt();
        if (val1 != val2) {
            Instruction.branch(frame, offset);
        }
    }

}
