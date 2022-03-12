package com.simple.jvm.instructions.control.rtn;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Thread;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * 没有返回值
 */
public class RETURN extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Thread thread = frame.getThread();
        thread.popFrame();
    }
    
}
