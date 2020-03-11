module testSignExtend();
wire [31:0] out;
reg [15:0] in;


signExtend sign(out,in);
initial begin
in=-1; 
#10 in= 16'h4;
end
initial begin
        $monitor (in,out);
       #100 $finish;
    end
endmodule
