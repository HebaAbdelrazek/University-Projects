
module PCMux( m_out,A,B,select); 
output [31:0] m_out;  input [31:0] A,B;  input  select; 
 
 assign m_out = (select=== 1'bx)? 0: ((select)? B:A); 
always @(select)
begin
$display ("PCMUXSelect %b", select);
end
endmodule 