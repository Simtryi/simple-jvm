package com.simple.jvm.instructions.stores.dstore;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 将double型变量从操作数栈顶弹出，并存入局部变量表
 */
public class DSTORE extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double val = operandStack.popDouble();

        LocalVars localVars = frame.getLocalVars();
        localVars.setDouble(idx, val);
    }

}
