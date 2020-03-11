module PC(output reg [31:0] addressOut,input [31:0] address, input clk);

always@(posedge clk)
begin
$display ("pc %b %b", address, addressOut);
addressOut = address;
end


endmodule 
