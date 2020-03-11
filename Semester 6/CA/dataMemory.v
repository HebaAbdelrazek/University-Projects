module dataMemory(output reg [31:0] readData, input memRead, input memWrite, input [31:0] address, input [31:0] writeData, input lhu, input lh);

 reg [7:0] memory [31:0];
integer index;

always @(memRead or memWrite or address or writeData or lh or lhu)
begin

if(memWrite)
begin
memory[address] = writeData[31:24];
memory[address+1] = writeData[23:16];
memory[address+2] = writeData[15:8];
memory[address+3] = writeData[7:0];
end

if(memRead )
begin
memory[0]=1;
memory[1]=0;
memory[2]=0;
memory[3]=0;

memory[4]=1;
memory[5]=1;
memory[6]=1;
memory[7]=1;
if(lhu)
readData = {memory[address], memory[address+1]};
else
if(lh)
readData = $signed({memory[address], memory[address+1]});
else
readData = {memory[address], memory[address+1], memory[address+2], memory[address+3]};
end

for(index = 0; index < 32; index = index + 4) begin
$display("%b %b %b %b", memory[index], memory[index+1], memory[index+2], memory[index+3] );
end

end

//always @(posedge clk)
//begin
//memory[0] = 0;
//memory[1] =0;
//memory [2]=1;
//memory [3]=1;
//for(index = 0; index < 32; index = index + 4) begin
//$display("%b %b %b %b", memory[index], memory[index+1], memory[index+2], memory[index+3]);
//end
//end


endmodule 