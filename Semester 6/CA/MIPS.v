module MIPS(input clk);

wire [31:0]  PCIn1, PCOut, instruction, intructionIFIDOut, PCIFIDOut, read_data_1, read_data_2, write_data, signExtendOut, muxPCOut;

wire [31:0] PCIDEXOut, read_data_1IDEX, read_data_2IDEX, signExtendIDEXOut, sllOut, EXAdderOut, EXMux1Out, ALUOut, AdderEXMEMOut, ALUEXMEMOut, read_data_2EXMEM, dataMemOut, ALUMEMWBOut, dataMemMEMWBOut;

wire PCsrc,  Zero, ZeroEXMEMOut;

wire [4:0]  rtIDEXOut, rdIDEXOut, EXMux2Out, destEXMEMOut, destMEMWBOut,shiftAmountIDEX;

wire [1:0] WBControlOut, WBIDEXOut,WBEXMEMOut,WBMEMWBOut;
wire [4:0] MEMControlOut, MEMIDEXOut, MEMEXMEMOut;
wire [3:0]  ALUCtrlOut;

wire [4:0] EXIDEXOut, EXControlOut;

PCMux pcMux(muxPCOut, PCIn1,AdderEXMEMOut,PCsrc);
PC pc (PCOut,muxPCOut, clk);


adder PCAdder(PCIn1, 4, PCOut);

IFID ifid (intructionIFIDOut,PCIFIDOut ,instruction, PCIn1,clk);
 
instructionMemory instructionMem(instruction , PCOut);

rf registerFile(read_data_1, read_data_2, intructionIFIDOut[25:21], intructionIFIDOut [20:16], destEXMEMOut,write_data, WBMEMWBOut[1]);

controller controlUnit(WBControlOut, MEMControlOut, EXControlOut, intructionIFIDOut[31:26]);

signExtend signExtender (signExtendOut, intructionIFIDOut[15:0]);

IDEX idex(WBIDEXOut, MEMIDEXOut, EXIDEXOut, PCIDEXOut, read_data_1IDEX, read_data_2IDEX, signExtendIDEXOut,rtIDEXOut, rdIDEXOut,shiftAmountIDEX ,WBControlOut,MEMControlOut,EXControlOut, PCIFIDOut , read_data_1, read_data_2, signExtendOut, intructionIFIDOut[20:16], intructionIFIDOut[15:11],intructionIFIDOut[10:6], clk );

shiftLeft2 sll (sllOut,signExtendIDEXOut);

adder EXAdder ( EXAdderOut , PCIDEXOut, sllOut);

mux EXMux1(EXMux1Out,read_data_2IDEX, signExtendIDEXOut, EXIDEXOut[0]);

DestMux EXMux2(EXMux2Out, rtIDEXOut, rdIDEXOut,  EXIDEXOut[4],clk);

ALUCtrl aluCtrl(ALUCtrlOut, signExtendIDEXOut[5:0],EXIDEXOut[3:1]);

alu ALU(ALUOut , Zero, read_data_1IDEX , EXMux1Out ,shiftAmountIDEX,ALUCtrlOut);

EXMEM exmem (WBEXMEMOut, MEMEXMEMOut, AdderEXMEMOut, ZeroEXMEMOut, ALUEXMEMOut, read_data_2EXMEM, destEXMEMOut, WBIDEXOut, MEMIDEXOut,EXAdderOut, Zero, ALUOut, read_data_2IDEX, EXMux2Out, clk );

and (PCsrc, MEMEXMEMOut[2], ZeroEXMEMOut);

dataMemory dataMem( dataMemOut, MEMEXMEMOut[1], MEMEXMEMOut[0],ALUEXMEMOut, read_data_2EXMEM,MEMEXMEMOut[4],MEMEXMEMOut[3] );

MEMWB memwb(WBMEMWBOut, ALUMEMWBOut, dataMemMEMWBOut, destMEMWBOut,WBEXMEMOut, dataMemOut, ALUEXMEMOut,destEXMEMOut, clk);

mux WBMux(write_data,dataMemMEMWBOut,ALUMEMWBOut,WBMEMWBOut[0]);

endmodule
