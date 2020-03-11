
module MEMWB(output reg [1:0] WBOut, output reg [31:0] aluOut, output reg  [31:0] read_dataOut, output reg [4:0] destOut, input [1:0] WB, input [31:0] read_data, input [31:0] alu, input [4:0] dest, input clk);



always @(posedge clk)
begin
WBOut = WB;
aluOut = alu;
read_dataOut = read_data;
destOut = dest;


$display("MEMWB %b %b %b %b " ,WBOut, aluOut, destOut,read_dataOut );
end

endmodule 