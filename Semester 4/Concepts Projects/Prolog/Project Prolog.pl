	
:- use_module(library(clpfd)).
at(5,0,w).
at(3,5,c).
at(9,6,c).

%this predicate checks that all instances of left side of a destroyer are either followed by a right side or at the end of the list.
mycheck([]).
mycheck([l]).
mycheck([l,r|T]):-
	mycheck(T).
mycheck([H|T]):-
	\+ H = l,
	\+ H=r,
	mycheck(T).
	
	
%this predicate calls a helper predicate to turn a 1D list to a list of lists.
list_to_llists(L,W,[L]):- length(L,X), W>X.	
list_to_llists(L,W,LLists):- llhelper(L,W,W, [],LLists).
%this predicate uses a counter on width, that starts from width to 1 appending all elements between W and 1 into an accumulator list.
%Once the counter gets to zero the accumulator is encapsulated into a 2D list and gets appended to the output list. 
llhelper([],W,W,[],[]).
llhelper([],W,X,_,[]):- X>0, X=\=W.
llhelper([H|T],W,X1,Acc,L):- X1>0, X2 is X1-1, append(Acc,[H],NewAcc),llhelper(T,W,X2,NewAcc,L).
llhelper(L1,W,0,Acc,L):- llhelper(L1,W,W,[],NL),append([Acc],NL,L). 

%This predicate returns the first element of the list with a disregard to the rest of the list. If the list is empty the predicate returns false.
getZeroth([H|_],H).

%This predicate ignores the first element of a list and returns the rest of the list. If the list is empty or consisting of 1 element it returns an empty list.
rest([],[]).
rest([_],[]).
rest([_|T],T).

%This predicate takes two indices and returns the elements of the list between these two indices. The predicate decrements both indices until the lower one is equal to zero.
%Then it appends to the output list all elements starting from the instance that lower index is zero while decrementing the upper index until the upper index get to zero.
%If both indices are equal the predicate returns a single element. If the upper index is out of range of the list the predicate returns all elements from first index till end of the input.
%If the second index is smaller than the first element the predicate returns an empty list.
sublist(0,0,[H|_],[H]).
sublist(0,I2,[H|T],[H|Sub]):-length(T,X), I2=<X,I2> 0,NewI2 is I2 - 1, sublist(0, NewI2, T, Sub).
sublist(I1,I2,[_|T],Sub):- length(T,X), I2=<X,I2>=I1, I1>0 , I2> 0, NewI1 is I1 - 1, NewI2 is I2 - 1, sublist(NewI1,NewI2,T,Sub).
sublist(I1,I2,_,[]):- I1>I2.
sublist(I1,I2,L,Sub):- length(L,X), I2>=X, NewI2 is I2-1, sublist(I1,NewI2,L,Sub).


%This predicate checks all facts and calls on them a helper method and adds all facts matching the helper method to an output list.
collect_hints(H):- setof(A,check(A),H).
%This predicate takes a fact and checks if it matches the requirments of the fact by checking the first two inputs to be numbers and the third to be a valid type of cell.
check(X):- at(A,B,C), number(A), number(B), (C=l;C=r;C=c;C=w), X= at(A,B,C).

%This predicate takes an input list and a list of hints and cross checks the hints with the list with the help of a helper predicate.
ensure_hints(_,[],_,_).
ensure_hints(L,[at(A,B,C)|T],W,H):- (C =c; C=l;C=r;C=w), B>=0, B<H, A>=0,A<W, list_to_llists(L, W, LL), sublist(B,B,LL,L2), getZeroth(L2,L3), ensureHelp(L3,A,C), ensure_hints(L,T,W,H).
%This predicate takes a list an index (N) and an element(X) and checks if the Nth element of the list is X. 
ensureHelp([X|_],0,X).
ensureHelp([_|T],N,X):- N>0, NN is N-1, ensureHelp(T,NN,X).

%This predicate takes a list and checks if every element corresponds to one of to one of the valid cells(c,w,l,r).
random_assignment([]).
random_assignment([w|T]):-random_assignment(T).
random_assignment([c|T]):-random_assignment(T).
random_assignment([l|T]):-random_assignment(T).
random_assignment([r|T]):-random_assignment(T).

%This predicate takes a list representing a grid and a list of numbers and uses a helper method  to check if every (i)th row of the grid has the same amount of (l,c,r) as the ith number on the number list.
check_rows([],_,_,[]).
check_rows(L,W,_,L2):- length(L,X), X>0,countHelp(L,W,0,0,L2).
%This predicate "loops" on a certain list using the width of the list and a counter, then it compares the value of the counter to the first element of a number list and resets the test for the next row.
countHelp([c|T],W,X, Acc,E):- X < W , NAcc is Acc + 1, X1 is X + 1 ,countHelp(T,W,X1,NAcc,E).
countHelp([l|T],W,X, Acc,E):- X < W , NAcc is Acc + 1, X1 is X + 1 ,countHelp(T,W,X1,NAcc,E).
countHelp([r|T],W,X, Acc,E):- X < W , NAcc is Acc + 1, X1 is X + 1 ,countHelp(T,W,X1,NAcc,E).
countHelp([w|T],W,X,Acc,E):- X<W, X1 is X+1, countHelp(T,W,X1, Acc,E).
countHelp(L,W,W,H,[H|T]):-countHelp(L,W,0,0,T).
countHelp([],_,0,_,_).
countHelp([],W,X,_,_):- X=\=0,X<W.


%This predicate does the reverse of check rows by inverting the list and then calling check rows to check the number of vessels against the totals in acolumn.
check_columns([],_,_,_).
check_columns(L,W,H,Totals):- length(L,X),list_to_llists(L,W,LL), transpose(LL,LL1),length(L2,X), list_to_llists(L2,H,LL1), check_rows(L2,H,W,Totals).

%This predicate takes a list representing a grid and a number and checks if the total number of destroyers in the grid are equal to the input number using a helper predicate
check_destroyer([],_,_,_).
check_destroyer(L,W,_,TotalDestroyers):- destHelp(L,W,W,0,TotalDestroyers).

%This predicate takes a list representing a grid "loops" on the width checking if the last element of a row is not an l and counts the amount of destroyers.
destHelp([],_,0,X,X).
destHelp([],W,X,Ctr,T):- X>0, X<W, Ctr=<T.
destHelp([l,r|T],W,W1,X,Y):-W1>=2,W11 is W1-2, X1 is X+1 , destHelp(T,W,W11,X1,Y).
destHelp([c|T],W,W1,X,Y):-W1>0,W11 is W1-1,destHelp(T,W,W11,X,Y).
destHelp([l|T],W,W1,X,Y):-W1>0,W11 is W1-1,destHelp(T,W,W11,X,Y).
destHelp([w|T],W,W1,X,Y):-W1>0,W11 is W1-1, destHelp(T,W,W11,X,Y).
destHelp(L,W,0,X,Y):- destHelp(L,W,W,X,Y).


%This predicate takes a list representing a grid and a number and checks if the total number of submarines in the grid are equal to the input number using a helper predicate.
check_submarines([],_,_,_).
check_submarines(T,_,_,TotalSub):-  subHelp(T,0,TotalSub).
%This predicate takes a list representing a grid "loops" on the width and counts the amount of submarines.
subHelp([],X,X).
subHelp([c|T],X,Y):-  X1 is X+1 , subHelp(T,X1,Y).
subHelp([l,r|T],X,Y):- subHelp(T,X,Y).
subHelp([w|T],X,Y):- subHelp(T,X,Y).

%this predicate uses all the previous predicates in order to construct a grid satesfying all the input numbers and list of numbers.
battleship(L,W,H,TotalSub,TotalDes,TotalRows,TotalCoulmns):-
X is W*H,
length(L,X),
collect_hints(N),
ensure_hints(L,N,W,H),
check_rows(L,W,H,TotalRows),
mycheck(L),
check_submarines(L,W,H,TotalSub),
check_destroyer(L,H,W,TotalDes),
check_columns(L,W,H,TotalCoulmns),
random_assignment(L).