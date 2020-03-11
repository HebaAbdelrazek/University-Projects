module IDEX(output reg [1:0] WBOut,output reg [4:0] MEMOut, output reg [4:0] EXOut, output reg [31:0] PCOut, output reg [31:0] read_data_1Out, output reg [31:0] read_data_2Out, output reg [31:0] sign_extendOut, output reg [4:0] rtOut, output reg [4:0] rdOut,output reg [4:0] shiftAmountOut, input [1:0] WB,input [4:0] MEM, input [4:0] EX, input [31:0] PC, input [31:0] read_data_1, input [31:0] read_data_2, input [31:0] sign_extend, input [4:0] rt, input [4:0] rd,input [4:0] shiftAmount, input clk);

always @(posedge clk)
begin
WBOut = WB;
MEMOut = MEM;
EXOut = EX;
PCOut = PC;
read_data_1Out = read_data_1;
read_data_2Out = read_data_2;

sign_extendOut = sign_extend;
rdOut = rd;
rtOut = rt;
shiftAmountOut= shiftAmount;
$display("IDEX %b %b %b %b %b %b %b %b %b" ,WBOut, MEMOut, EXOut, PCOut,read_data_1Out, read_data_2Out, sign_extendOut,rdOut,rtOut );
end

endmodule 
