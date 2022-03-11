package com.simple.jvm.instructions.base.impl;

import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * 访问运行时常量池，常量池索引由2字节操作数给出
 */
public class Index16Instruction implements Instruction {

    protected int idx;  //  常量池索引

    @Override
    public void fetchOperands(BytecodeReader reader) {
        idx = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }

}
