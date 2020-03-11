module rf(read_data_1, read_data_2, read_reg_1, read_reg_2, write_reg, write_data, regWrite);


input [4:0] read_reg_1, read_reg_2, write_reg;
input [31:0] write_data;
input regWrite;
output reg [31:0] read_data_1, read_data_2;
reg [31:0] registers[31:0];
integer index; 

always @(read_reg_1 or read_reg_2 ) 
begin
registers[0] = 0;
registers[9]= 6; //t1 
registers[10]= 7;  //t2
registers[11]=8; //t3
registers[12]=6; //t4
read_data_1 = (read_reg_1== 5'b00000)? 32'd0 : registers[read_reg_1];
read_data_2 = (read_reg_2== 5'b00000)? 32'd0 : registers[read_reg_2];


for(index = 0; index < 32; index = index + 1) begin
$display("RFRead %b %b %b", registers[index],read_data_1,read_data_2 );
end


end



always @(regWrite or  write_reg or write_data) 
begin
    if(regWrite) 
        begin
         registers[0] = 0;
        registers[write_reg] =  (write_reg == 0)? 32'd0 : write_data; 
        end

$display("RFWrite %b %b %b" ,regWrite,write_reg, write_data );
for(index = 0; index < 32; index = index + 1) begin
$display("RFWrite %b " ,registers[index]);
end
end

//always @(posedge clk)
//begin
//$display("%h%h %b %b %b %b %b %b %b", "x","f" ,read_data_1, read_data_2, write_data, regWrite,read_reg_1, read_reg_2, write_reg );
//for(index = 0; index < 32; index = index + 1) begin
//$display("%h%h %b ", "r","x" ,registers[index]);
//end
//end


endmodule

