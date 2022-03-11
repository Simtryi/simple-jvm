package com.simple.jvm.instructions.loads.fload;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 从局部变量表获取float型变量，并推入操作数栈顶
 */
public class FLOAD extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        Float val = localVars.getFloat(idx);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat(val);
    }

}
