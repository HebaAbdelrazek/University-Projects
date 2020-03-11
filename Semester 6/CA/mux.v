module mux(m_out,A,B,select);  
output [31:0] m_out;  input [31:0] A,B;  input  select; 
 
 assign m_out = (select)? B:A; 


endmodule 
