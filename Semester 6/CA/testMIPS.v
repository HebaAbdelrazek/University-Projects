module testMIPS();
//wire [31:0] muxPCOut;
reg clk;

//assign muxPCOut=0;
initial begin
clk=0;

forever begin
#5 clk=~clk; //wait 5 units of time and print/change
end
end

MIPS mips(clk);



initial begin
#100 $finish;
end

endmodule
