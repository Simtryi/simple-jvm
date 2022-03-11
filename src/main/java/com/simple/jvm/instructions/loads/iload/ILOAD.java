package com.simple.jvm.instructions.loads.iload;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 从局部变量表获取int型变量，并推入操作数栈顶
 */
public class ILOAD extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(idx);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt(val);
    }

}

