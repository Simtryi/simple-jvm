package com.simple.jvm;

import com.alibaba.fastjson.JSON;
import com.simple.jvm.classfile.MemberInfo;
import com.simple.jvm.classfile.attributeinfo.impl.CodeAttribute;
import com.simple.jvm.instructions.Factory;
import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.Thread;

/**
 * 解释器
 */
public class Interpreter {

    Interpreter(MemberInfo m) {
        //  获取Code属性
        CodeAttribute codeAttr = m.getCodeAttribute();
        int maxLocals = codeAttr.getMaxLocals();
        int maxStack = codeAttr.getMaxStack();
        byte[] byteCode = codeAttr.getData();

        //  创建一个Thread实例
        Thread thread = new Thread();
        //  创建一个帧并推入Java虚拟机栈
        Frame frame = thread.newFrame(maxLocals, maxStack);
        thread.pushFrame(frame);

        loop(thread, byteCode);
    }

    /**
     * 循环执行"计算pc、解码指令、执行指令"这三个步骤，直到遇到错误
     * @param thread
     * @param byteCode
     */
    private void loop(Thread thread, byte[] byteCode) {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();

        while (true) {
            //  计算pc
            int pc = frame.getNextPC();
            thread.setPC(pc);

            //  解码指令
            reader.reset(byteCode, pc);
            byte opcode = reader.readByte();
            Instruction inst = Factory.create(opcode);
            if (null == inst) {
                System.out.println("寄存器(指令)尚未实现 " + byteToHexString(new byte[]{opcode}));
                break;
            }

            //  执行指令
            inst.fetchOperands(reader);
            frame.setNextPC(reader.getPC());
            System.out.println("寄存器(指令)：" + byteToHexString(new byte[]{opcode}) +
                    " -> " + inst.getClass().getSimpleName() +
                    " => 局部变量表：" + JSON.toJSONString(frame.getLocalVars().getSlots()) +
                    " 操作数栈：" + JSON.toJSONString(frame.getOperandStack().getSlots()));
            inst.execute(frame);
        }

    }

    private static String byteToHexString(byte[] codes) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : codes) {
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }

}
