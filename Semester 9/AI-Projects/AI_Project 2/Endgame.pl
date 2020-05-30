:- use_module(library(clpfd)).

:- include('/Users/khaledhammoud/eclipse-workspaceProject2/AI-Project2/KB1.pl').

:- set_prolog_stack(local, limit(20000000000000)).

%a predicate created for incrementing variables and saving the incremented value in a new variable
incr(X, X1):- 
    X1 is X + 1.

%the base case is when iron mans position is the same as his initial position, the stones remaining list has all 4 stones, and the state is s0
ironMan(X, Y, [stone(1,X1,Y1), stone(2,X2,Y2), stone(3,X3,Y3), stone(4,X4,Y4)], s0):-
    ironManInitial(X,Y),
    stone(1,X1,Y1),
    stone(2,X2,Y2),
    stone(3,X3,Y3),
    stone(4,X4,Y4).

%if the new state is in a cell that contained a stone, the stone collected will be added to the stones remaining list of the previous state
ironMan(X, Y, Stones, result(A, S)):-
    A = 'collect',
    Xold is X,
    Yold is Y,
    stone(_,Xold,Yold), %checks that this position contains a stone
    append(Stones, [stone(_,Xold,Yold)], StonesOld), 
    (\+thanos(X,Y)),
    ironMan(Xold, Yold, StonesOld, S).

ironMan(X, Y, Stones, result(A, S)):-
    A = 'left',
    Xold is X,
    Yold is Y+1,
    StonesOld = Stones,
    Yold > 0,
    columns(N),
    number(N),
    Yold < N,
    (\+thanos(Xold,Yold)),
    ((\+thanos(X,Y));(thanos(X,Y), StonesOld = [])),
    ironMan(Xold, Yold, StonesOld, S).

ironMan(X, Y, Stones, result(A, S)):-
    A = 'right',
    Xold is X,
    Yold is Y-1,
    StonesOld = Stones,
    columns(N),
    number(N),
    Yold < N,
    Yold > -1,
    (\+thanos(Xold,Yold)),
    ((\+thanos(X,Y));(thanos(X,Y), StonesOld = [])),
    ironMan(Xold, Yold, StonesOld, S).

ironMan(X, Y, Stones, result(A, S)):-
    A = 'up',
    Xold is X+1,
    Yold is Y,
    StonesOld = Stones,
    Xold > 0,
    rows(N),
    number(N),
    Xold < N,
    (\+thanos(Xold,Yold)),
    ((\+thanos(X,Y));(thanos(X,Y), StonesOld = [])),
    ironMan(Xold, Yold, StonesOld, S).

ironMan(X, Y, Stones, result(A, S)):-
    A = 'down',
    Xold is X-1,
    Yold is Y,
    StonesOld = Stones,
    columns(N),
    number(N),
    Xold < N,
    Xold > -1,
    (\+thanos(Xold,Yold)),
    ((\+thanos(X,Y));(thanos(X,Y), StonesOld = [])),
    ironMan(Xold, Yold, StonesOld, S).

snapped1(S):-
  thanos(X,Y), ironMan(X,Y,[],S1), S = result(snap,S1).

snapped(S):-
  testIDS(S,1).

test:-
  DepthLimit is 1000000,
  Goal = snapped(_),
  time( call_with_depth_limit(Goal,DepthLimit,R) ),
  writeln(R).

%snappedNew(S):-
%  testIDS(S,1).

testIDS(S,Depth):-
  (call_with_depth_limit(snapped1(S),Depth,R),
   R \= 'depth_limit_exceeded');
  (incr(Depth, NewDepth),
   testIDS(S, NewDepth)).

