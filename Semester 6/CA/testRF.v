module testRF(); 

reg [4:0] read_reg_1, read_reg_2, write_reg; 
reg [31:0] write_data;
reg regWrite, clk;
wire [31:0] read_data_1, read_data_2;
rf r( read_data_1, read_data_2, read_reg_1, read_reg_2, write_reg, write_data, regWrite, clk);

initial begin
clk=0;
forever begin
#5 clk=~clk; //wait 5 units of time and print/change
end
end

initial
begin
$monitor("%t %b %b %b ", $time, read_data_1, read_data_2,  read_reg_1);

//#5 read_reg_1 <= 1;

#5 write_reg <= 1;
regWrite <= 1;
write_data <= 32'd55;
#15 regWrite <= 0;
#5 read_reg_2 <= 0;

#50 $finish;
end
endmodule
