module ALUCtrl(out, fn, ALUop);
input [5:0] fn;
input [2:0] ALUop;
output reg [3:0] out;

always @(fn or ALUop)
begin
    case(ALUop)
    3'b000: out = 4'b0010;
    3'b001: out = 4'b0110;
    3'b100: out= 4'b0000;  //andi
    3'b101: out= 4'b0001;  //ori
    3'b010: 
        case(fn)
        6'b100000: out = 4'b0010; //add
        6'b100010: out = 4'b0110; //subtract
        6'b100100: out = 4'b0000; //and
        6'b100101: out = 4'b0001; //or
        6'b101010: out = 4'b0111; //slt
        6'b101011: out = 4'b0100; //sltu
        6'b000000: out = 4'b0011; //sll
        6'b000010: out = 4'b1000; //srl
	default:   out= 4'b0010;
        endcase
 
    endcase
$display ("aluCtrl %b %b %b",out, fn, ALUop );  
end
endmodule