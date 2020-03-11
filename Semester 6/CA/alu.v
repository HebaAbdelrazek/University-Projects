module alu(out,zero,in1,in2,shiftAmount,select);
output reg [31:0] out;
output reg zero;
input [31:0] in1,in2;
input [3:0] select; 

input [4:0] shiftAmount;


always @(in1 or in2 or select)
begin
if(in1==in2)
zero=1;
else
zero=0;
end


always @(in1 or in2 or shiftAmount or select)
begin
case(select)
4'b0010: //add 
out= in1 + in2;
   
4'b0110: //sub
out = in1 - in2;

4'b0000: //and, andi
out = in1 && in2;

4'b0001: //or, ori
out = in1 || in2;

4'b0100: //sltu
out = in1 < in2;

4'b0111: //slt
out = ($signed(in1) < $signed(in2));

4'b0011: //sll
out = in2 << shiftAmount;

4'b1000: //srl
out = in2 >> shiftAmount;

endcase

$display("ALU %b %b %b %b",select, in1, in2, out);
end
endmodule
