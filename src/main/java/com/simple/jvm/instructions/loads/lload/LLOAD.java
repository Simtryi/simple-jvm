package com.simple.jvm.instructions.loads.lload;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

/**
 * 从局部变量表获取long型变量，并推入操作数栈顶
 */
public class LLOAD extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        Long val = localVars.getLong(idx);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushLong(val);
    }

}
