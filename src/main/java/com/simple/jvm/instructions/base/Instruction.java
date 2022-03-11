package com.simple.jvm.instructions.base;

import com.simple.jvm.rtda.Frame;

/**
 * 指令接口
 * <p>
 *  Java虚拟机规范把已经定义的205条指令按用途分成了11类，分别是：
 *  常量(constants)指令、加载(loads)指令、存储(stores)指令、
 *  操作数栈(stack)指令、数学(math)指令、转换(conversions)指令、
 *  比较(comparisons)指令、控制(control)指令、引用(references)指令、
 *  扩展(extended)指令和保留(reserved)指令。
 * </p>
 */
public interface Instruction {

    /**
     * 从字节码中提取操作数
     */
    void fetchOperands(BytecodeReader reader);

    /**
     * 执行指令逻辑
     */
    void execute(Frame frame);

    /**
     * 跳转指令
     */
    static void branch(Frame frame, int offset) {
        int pc = frame.getThread().getPC();
        int nextPC = pc + offset;
        frame.setNextPC(nextPC);
    }

}
