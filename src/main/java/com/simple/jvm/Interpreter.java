package com.simple.jvm;

import com.alibaba.fastjson.JSON;
import com.simple.jvm.instructions.Factory;
import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.heap.methodarea.Method;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.Thread;

/**
 * 解释器
 */
public class Interpreter {

    Interpreter(Method method) {
        //  创建一个Thread实例
        Thread thread = new Thread();
        //  创建一个帧并推入Java虚拟机栈
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);

        loop(thread, method.getCode());
    }

    /**
     * 循环执行"计算pc、解码指令、执行指令"这三个步骤，直到遇到错误
     */
    private void loop(Thread thread, byte[] byteCode) {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();

        while (true) {
            int pc = frame.getNextPC();
            thread.setPC(pc);

            reader.reset(byteCode, pc);
            byte opcode = reader.readByte();
            Instruction inst = Factory.create(opcode);
            if (null == inst) {
                System.out.format("Unsupported opcode：0x%x\n", opcode);
                break;
            }
            inst.fetchOperands(reader);
            frame.setNextPC(reader.getPC());
            System.out.format("寄存器(指令)：0x%x -> %s => 局部变量表：%s 操作数栈：%s\n", opcode, inst.getClass().getSimpleName(), JSON.toJSONString(frame.getLocalVars().getSlots()), JSON.toJSONString(frame.getOperandStack().getSlots()));

            inst.execute(frame);
        }

    }

}
