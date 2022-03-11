package com.simple.jvm.instructions.loads.aload;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

/**
 * 从局部变量表获取引用型标量，并推入操作数栈顶
 */
public class ALOAD extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        Object ref = localVars.getRef(idx);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushRef(ref);
    }

}
