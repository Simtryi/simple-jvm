package com.simple.jvm.rtda;

/**
 * 线程
 */
public class Thread {

    private int pc;         //  PC寄存器，线程私有
    private JVMStack stack; //  Java虚拟机栈，线程私有

    public Thread() {
        stack = new JVMStack(1024);
    }

    public int getPC() {
        return pc;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame) {
        stack.push(frame);
    }

    public Frame popFrame() {
        return stack.pop();
    }

    public Frame currentFrame() {
        return stack.top();
    }

    public Frame newFrame(int maxLocals, int maxStack) {
        return new Frame(this, maxLocals, maxStack);
    }

}
