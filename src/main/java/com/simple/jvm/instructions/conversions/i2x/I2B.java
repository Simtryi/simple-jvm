package com.simple.jvm.instructions.conversions.i2x;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

/**
 * int类型转换为byte类型
 */
public class I2B extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int i = operandStack.popInt();
        int b = (byte) i;
        operandStack.pushInt(b);
    }

}
