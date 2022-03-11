package com.simple.jvm.instructions.constants.nop;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * nop指令什么也不做
 */
public class NOP extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        //  nothing to do
    }

}
