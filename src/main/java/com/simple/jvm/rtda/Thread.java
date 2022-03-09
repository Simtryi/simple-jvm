package com.simple.jvm.rtda;

/**
 * 线程
 */
public class Thread {

    private int pc;         //  PC寄存器
    private JVMStack stack; //  Java虚拟机栈

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

}
