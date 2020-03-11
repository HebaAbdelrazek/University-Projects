module EXMEM(output reg [1:0] WBOut,output reg  [4:0] MEMOut, output reg [31:0] ADDEROut, output reg  ZeroOut, output reg [31:0] aluOut, output reg  [31:0] read_data_2Out, output reg [4:0] destOut, input [1:0] WB,input [4:0] MEM, input [31:0] ADDER, input Zero, input [31:0] alu, input [31:0] read_data_2, input [4:0] dest, input clk);

always @(posedge clk)
begin
WBOut = WB;
MEMOut = MEM;
ADDEROut = ADDER;
ZeroOut = Zero;
aluOut = alu;
destOut = dest;
read_data_2Out = read_data_2;
$display("EXMEM %b %b %b %b %b %b %b" ,WBOut, MEMOut, ADDEROut, aluOut,ZeroOut,destOut, read_data_2Out );
end


endmodule 
