module DestMux(m_out,A,B,select, clk);  
output reg [4:0] m_out;  input [4:0] A,B;  input select,clk; 
 
always @(posedge clk)
begin
 m_out = (select)? B:A; 
end

endmodule 
