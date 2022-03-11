package com.simple.jvm.instructions.loads.aload;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

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
