package com.simple.jvm.instructions.conversions.f2x;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * float类型转换为int类型
 */
public class F2I extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float f = operandStack.popFloat();
        int i = (int) f;
        operandStack.pushInt(i);
    }

}
