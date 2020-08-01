grammar FDFA2;
prog: (State0)*;
State0 : ('0'+)State0 | ('1')State1;
State1 : (('1'+)State1 | ('0')State2) {System.out.print("01");};
State2 : (('0')State0 | ('1')State3) {System.out.print("10");};
State3 : ('0')State3 | ('1')State3;
