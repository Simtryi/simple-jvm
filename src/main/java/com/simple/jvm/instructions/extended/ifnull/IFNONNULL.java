package com.simple.jvm.instructions.extended.ifnull;

import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.instructions.base.impl.BranchInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 引用不是null，进行跳转
 */
public class IFNONNULL extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Object ref = operandStack.popRef();

        if (null != ref) {
            Instruction.branch(frame, offset);
        }
    }

}