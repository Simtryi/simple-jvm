package com.simple.jvm.instructions.conversions.f2x;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * float类型转换为long类型
 */
public class F2L extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float f = operandStack.popFloat();
        long l = (long) f;
        operandStack.pushLong(l);
    }

}
