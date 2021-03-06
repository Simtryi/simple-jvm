package com.simple.jvm.instructions.stores.astore;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 将引用类型变量从操作数栈顶弹出，并存入局部变量表
 */
public class ASTORE extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Object ref = operandStack.popRef();

        LocalVars localVars = frame.getLocalVars();
        localVars.setRef(idx, ref);
    }

}
