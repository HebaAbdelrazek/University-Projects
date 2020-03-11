module IFID (output reg [31:0] instructionOut,output reg [31:0] PCOut, input [31:0] instruction, input [31:0] PC, input clk);


always@(posedge clk)
begin
PCOut = PC;
instructionOut = instruction;
$display("IFID %b ", instructionOut );
end

endmodule 