module signExtend(out,in);
output [31:0] out;
input [15:0] in;

assign out = $signed(in);

endmodule 
