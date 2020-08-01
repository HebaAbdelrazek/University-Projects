//Task 8

grammar T18_37_5660_Heba_Abdelrazek;

start: operationS EOF{System.out.println($operationS.val);};

///////////////////
operationS returns [double val]
: operationL'.'operationR {$val = ($operationL.val + $operationR.val);};

operationL returns[double val, int n]
: operationB operationL1=operationL {$n = $operationL1.n+1;} {$val =(($operationB.val*Math.pow(2,$n))+$operationL1.val);}
| operationB {$n=0;} {$val=$operationB.val;};

operationR returns[double val]
: operationB operationR1=operationR {$val=(($operationR1.val*0.5)+($operationB.val*0.5));}
| operationB {$val=$operationB.val*0.5;};

operationB returns[int val]
: '0' {$val=0;}
| '1' {$val=1;};

///////////////////
WS : [ \r\n\t] + -> skip;
