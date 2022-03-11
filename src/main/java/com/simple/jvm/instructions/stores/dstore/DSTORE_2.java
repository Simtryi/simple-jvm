package com.simple.jvm.instructions.stores.dstore;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

public class DSTORE_2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double val = operandStack.popDouble();

        LocalVars localVars = frame.getLocalVars();
        localVars.setDouble(2, val);
    }

}

