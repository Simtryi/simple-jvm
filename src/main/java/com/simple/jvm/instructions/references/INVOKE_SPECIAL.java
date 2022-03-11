package com.simple.jvm.instructions.references;

import com.simple.jvm.instructions.base.impl.Index16Instruction;
import com.simple.jvm.rtda.jvmstack.Frame;

public class INVOKE_SPECIAL  extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popRef();
    }

}