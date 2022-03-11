package com.simple.jvm.instructions.extended;

import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.instructions.loads.aload.ALOAD;
import com.simple.jvm.instructions.loads.dload.DLOAD;
import com.simple.jvm.instructions.loads.fload.FLOAD;
import com.simple.jvm.instructions.loads.iload.ILOAD;
import com.simple.jvm.instructions.loads.lload.LLOAD;
import com.simple.jvm.instructions.math.iinc.IINC;
import com.simple.jvm.instructions.stores.astore.ASTORE;
import com.simple.jvm.instructions.stores.dstore.DSTORE;
import com.simple.jvm.instructions.stores.fstore.FSTORE;
import com.simple.jvm.instructions.stores.istore.ISTORE;
import com.simple.jvm.instructions.stores.lstore.LSTORE;
import com.simple.jvm.rtda.Frame;

/**
 * 加载类指令、存储类指令、ret指令和iinc指令需要按索引访问局部变量表，
 * 对于大部分方法来说，局部变量表大小都不会超过256，所以用一字节来表示索引就够了，
 * 但是如果有方法的局部变量表超过256，Java虚拟机规范通过wide指令来扩展前述指令。
 */
public class WIDE implements Instruction {

    private Instruction modifiedInstruction;    //  被改变的指令

    @Override
    public void fetchOperands(BytecodeReader reader) {
        //  读取一字节的操作码
        byte opcode = reader.readByte();
        switch (opcode){
            case 0x15:
                ILOAD inst_iload = new ILOAD();
                inst_iload.idx = reader.readShort();
                modifiedInstruction = inst_iload;

            case 0x16:
                LLOAD inst_lload = new LLOAD();
                inst_lload.idx = reader.readShort();
                modifiedInstruction = inst_lload;

            case 0x17:
                FLOAD inst_fload = new FLOAD();
                inst_fload.idx = reader.readShort();
                modifiedInstruction = inst_fload;

            case 0x18:
                DLOAD inst_dload = new DLOAD();
                inst_dload.idx = reader.readShort();
                modifiedInstruction = inst_dload;

            case 0x19:
                ALOAD inst_aload = new ALOAD();
                inst_aload.idx = reader.readShort();
                modifiedInstruction = inst_aload;

            case 0x36:
                ISTORE inst_istore = new ISTORE();
                inst_istore.idx = reader.readShort();
                modifiedInstruction = inst_istore;

            case 0x37:
                LSTORE inst_lstore = new LSTORE();
                inst_lstore.idx = reader.readShort();
                modifiedInstruction = inst_lstore;

            case 0x38:
                FSTORE inst_fstore = new FSTORE();
                inst_fstore.idx = reader.readShort();
                modifiedInstruction = inst_fstore;

            case 0x39:
                DSTORE inst_dstore = new DSTORE();
                inst_dstore.idx = reader.readShort();
                modifiedInstruction = inst_dstore;

            case 0x3a:
                ASTORE inst_astore = new ASTORE();
                inst_astore.idx = reader.readShort();
                modifiedInstruction = inst_astore;

            case (byte) 0x84:
                IINC inst_iinc = new IINC();
                inst_iinc.idx = reader.readShort();
                modifiedInstruction = inst_iinc;

            case (byte) 0xa9:   // ret指令
                throw new RuntimeException("Unsupported opcode: 0xa9!");
        }
    }

    @Override
    public void execute(Frame frame) {
        modifiedInstruction.execute(frame);
    }

}
