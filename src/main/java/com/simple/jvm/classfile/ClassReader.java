package com.simple.jvm.classfile;

import java.math.BigInteger;

/**
 * 读取class数据
 * <p>
 *  构成class文件的基本数据单位是字节，可以把整个class文件当成一个字节流来处理。
 *  Java虚拟机规范定义了u1、u2和u4三种数据类型来标识1、2和4字节无符号整数。
 * </p>
 */
public class ClassReader {

    private byte[] data;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    /**
     * 读取u1类型数据
     */
    public int readU1() {
        byte[] val = readByte(1);
        String strHex = new BigInteger(1, val).toString(16);
        return Integer.parseInt(strHex, 16);
    }

    /**
     * 读取u2类型数据
     */
    public int readU2() {
        byte[] val = readByte(2);
        String strHex = new BigInteger(1, val).toString(16);
        return Integer.parseInt(strHex, 16);
    }

    /**
     * 读取u2表，表的大小由开头的u2数据指出
     */
    public int[] readU2s() {
        int n = this.readU2();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = this.readU2();
        }
        return s;
    }

    /**
     * 读取u4类型数据
     */
    public long readU4() {
        byte[] val = readByte(4);
        String strHex = new BigInteger(1, val).toString(16);
        return Long.parseLong(strHex, 16);
    }

    /**
     * 读取u4类型数据，转型成int类型
     */
    public int readU4ToInteger(){
        byte[] val = readByte(4);
        return new BigInteger(1, val).intValue();
    }

    public float readU8ToFloat() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).floatValue();
    }

    public long readU8ToLong() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).longValue();
    }

    public double readU8ToDouble() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).doubleValue();
    }

    /**
     * 读取指定数量的字节
     */
    public byte[] readBytes(int n) {
        return readByte(n);
    }

    /**
     * 读取class数据
     */
    private byte[] readByte(int length) {
        byte[] copy = new byte[length];
        System.arraycopy(data, 0, copy, 0, length);
        System.arraycopy(data, length, data, 0, data.length - length);
        return copy;
    }

}
