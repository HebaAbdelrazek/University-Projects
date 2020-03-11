module testAdder();
wire [31:0] out;
reg [31:0] in1,in2;

adder addtest(out,in1,in2);

initial begin
in1=1; in2=2;
#10 in1=2; in2=3;
#10 in1=3; in2=2;
end

initial begin
        $monitor (in1, in2, out);
       #100 $finish;
    end
endmodule
