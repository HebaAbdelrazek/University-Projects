
s(S) --> sentence(S).
s(s(S1,C,S2)) --> sentence(S1), conjunctionWhile(C), s(S2).
sentence(sentence(NP,VP)) --> noun_phrases(NP), verb_phrases(VP).


noun_phrase(noun_phrase(PN)) --> pronoun(PN).
noun_phrase(noun_phrase(PN,RC)) --> pronoun(PN), relative_clause(RC).
noun_phrase(noun_phrase(PN,PP)) --> pronoun(PN), preposition_phrase(PP).
noun_phrase(noun_phrase(PN,PP,RC)) --> pronoun(PN), preposition_phrase(PP), relative_clause(RC).
noun_phrase(noun_phrase(PN,RC,PP)) --> pronoun(PN), relative_clause(RC), preposition_phrase(PP).

noun_phrase(noun_phrase(N)) --> noun(N).
noun_phrase(noun_phrase(N,RC)) --> noun(N), relative_clause(RC).
noun_phrase(noun_phrase(N,PP)) --> noun(N), preposition_phrase(PP).
noun_phrase(noun_phrase(N,PP,RC)) --> noun(N), preposition_phrase(PP), relative_clause(RC).
noun_phrase(noun_phrase(N,RC,PP)) --> noun(N), relative_clause(RC), preposition_phrase(PP).

noun_phrase(noun_phrase(D,N)) --> det(D), noun(N).
noun_phrase(noun_phrase(D,N,RC)) --> det(D), noun(N), relative_clause(RC).
noun_phrase(noun_phrase(D,N,PP)) --> det(D), noun(N), preposition_phrase(PP).
noun_phrase(noun_phrase(D,N,PP,RC)) --> det(D), noun(N), preposition_phrase(PP), relative_clause(RC).
noun_phrase(noun_phrase(D,N,RC,PP)) --> det(D), noun(N), relative_clause(RC), preposition_phrase(PP).

noun_phrase(noun_phrase(D,ADP,N)) --> det(D), adjective_phrase(ADP), noun(N).
noun_phrase(noun_phrase(D,ADP,N,PP)) --> det(D), adjective_phrase(ADP), noun(N), preposition_phrase(PP).
noun_phrase(noun_phrase(D,ADP,N,RC)) --> det(D), adjective_phrase(ADP), noun(N), relative_clause(RC).
noun_phrase(noun_phrase(D,ADP,N,RC,PP)) --> det(D), adjective_phrase(ADP), noun(N), relative_clause(RC), preposition_phrase(PP).
noun_phrase(noun_phrase(D,ADP,N,PP,RC)) --> det(D), adjective_phrase(ADP), noun(N), preposition_phrase(PP), relative_clause(RC).

noun_phrase(noun_phrase(ADP,N)) --> adjective_phrase(ADP), noun(N).
noun_phrase(noun_phrase(ADP,N,PP)) --> adjective_phrase(ADP), noun(N), preposition_phrase(PP).
noun_phrase(noun_phrase(ADP,N,RC)) --> adjective_phrase(ADP), noun(N), relative_clause(RC).
noun_phrase(noun_phrase(ADP,N,RC,PP)) --> adjective_phrase(ADP), noun(N), relative_clause(RC),preposition_phrase(PP).
noun_phrase(noun_phrase(ADP,N,PP,RC)) --> adjective_phrase(ADP), noun(N), preposition_phrase(PP), relative_clause(RC).

noun_phrases(NP)--> noun_phrase(NP).
noun_phrases(noun_phrases(NP1,C,NP2))--> noun_phrase(NP1), conjunction(C), noun_phrases(NP2).

verb_phrases(VP)--> verb_phrase(VP).
verb_phrases(verb_phrases(VP1,C,VP2))--> verb_phrase(VP1), conjunction(C), verb_phrases(VP2).

verbs(V) --> verb(V).
verbs(verbs(V1,C,V2)) --> verb(V1), conjunction(C), verbs(V2).

adverbs(AV) --> adverb(AV).
adverbs(adverbs(AV,AVS)) --> adverb(AV), adverbs(AVS).

adjective_phrase(AD) --> adjective(AD).
adjective_phrase(adjective_phrase(AD,ADP)) --> adjective(AD), adjective_phrase(ADP).

verb_phrase(verb_phrase(V)) --> verbs(V).
verb_phrase(verb_phrase(V,NP,NP2)) --> verbs(V), noun_phrases(NP), noun_phrases(NP2).
verb_phrase(verb_phrase(V,NP)) --> verbs(V), noun_phrases(NP).
verb_phrase(verb_phrase(AV,V,NP)) --> adverb(AV), verbs(V), noun_phrases(NP).
verb_phrase(verb_phrase(V,P)) --> verbs(V), preposition_phrase(P).
verb_phrase(verb_phrase(AV,V,P)) --> adverb(AV), verbs(V), preposition_phrase(P).


preposition_phrase(preposition_phrase(P,NP)) --> preposition(P), noun_phrases(NP).
preposition_phrase(preposition_phrase(P,NP,PP)) -->  preposition(P), noun_phrases(NP), preposition_phrase(PP).

relative_clause(relative_clause(R,NP,VP)) --> relative_whom(R), noun_phrases(NP), verb_phrases(VP).
relative_clause(relative_clause(R,VP)) --> relative_who(R), verb_phrases(VP).

relative_who(relative_who(who)) --> [who].
relative_whom(relative_whom(whom)) --> [whom].

pronoun(pronoun(she)) --> [she].
pronoun(pronoun(he)) --> [he].
pronoun(pronoun(i)) --> [i].
pronoun(pronoun(it)) --> [it].
pronoun(pronoun(they)) --> [they].

conjunctionWhile(conjunctionWhile(while)) --> [while].
conjunction(conjunction(and)) --> [and].


noun(noun(bat)) --> [bat].
noun(noun(cat)) --> [cat].
noun(noun(boy)) --> [boy].
noun(noun(man)) --> [man].
noun(noun(woman)) --> [woman].
noun(noun(envelope)) --> [envelope].
noun(noun(shed)) --> [shed].
noun(noun(building)) --> [building].
noun(noun(students)) --> [students].
noun(noun(tree)) --> [tree].
noun(noun(box)) --> [box].
noun(noun(room)) --> [room].
noun(noun(school)) --> [school].
noun(noun(professors)) --> [professors].
noun(noun(lecturers)) --> [lecturers].
noun(noun(scientists)) --> [scientists].
noun(noun(researchers)) --> [researchers].
noun(noun(girl)) --> [girl].
noun(noun(dog)) --> [dog].
noun(noun(pen)) --> [pen].

adjective(adjective(young)) --> [young].
adjective(adjective(old)) --> [old].
adjective(adjective(poor)) --> [poor].
adjective(adjective(big)) --> [big].
adjective(adjective(large)) --> [large].
adjective(adjective(empty)) --> [empty].
adjective(adjective(white)) --> [white].
adjective(adjective(brilliant)) --> [brilliant].
adjective(adjective(bright)) --> [bright].
adjective(adjective(talented)) --> [talented].
adjective(adjective(kind)) --> [kind].
adjective(adjective(sad)) --> [sad].
adjective(adjective(happy)) --> [happy].
adjective(adjective(calm)) --> [calm].
adjective(adjective(angry)) --> [angry].
adjective(adjective(faithful)) --> [faithful].
adjective(adjective(small)) --> [small].
adjective(adjective(dark)) --> [dark].
adjective(adjective(quick)) --> [quick].
adjective(adjective(slow)) --> [slow].

verb(verb(eats)) --> [eats].
verb(verb(worked)) --> [worked].
verb(verb(pushed)) --> [pushed].
verb(verb(stored)) --> [stored].
verb(verb(gave)) --> [gave].
verb(verb(liked)) --> [liked].
verb(verb(climbed)) --> [climbed].
verb(verb(watched)) --> [watched].
verb(verb(admired)) --> [admired].
verb(verb(appreciated)) --> [appreciated].
verb(verb(lied)) --> [lied].
verb(verb(killed)) --> [killed].
verb(verb(loved)) --> [loved].
verb(verb(hated)) --> [hated].
verb(verb(drank)) --> [drank].
verb(verb(cried)) --> [cried].
verb(verb(found)) --> [found].
verb(verb(filled)) --> [filled].
verb(verb(tried)) --> [tried].
verb(verb(helped)) --> [helped].
verb(verb(was)) --> [was].

adverb(adverb(quickly)) --> [quickly].
adverb(adverb(secretly)) --> [secretly].
adverb(adverb(loudly)) --> [loudly].
adverb(adverb(lightly)) --> [lightly].
adverb(adverb(slowly)) --> [slowly].
adverb(adverb(truthfully)) --> [truthfully].
adverb(adverb(randomly)) --> [randomly].
adverb(adverb(weirdly)) --> [weirdly].
adverb(adverb(cheerfully)) --> [cheerfully].
adverb(adverb(expertly)) --> [expertly].

det(determiner(the)) --> [the].
det(determiner(a)) --> [a].
det(determiner(some)) --> [some].
det(determiner(every)) --> [every].
det(determiner(many)) --> [many].
det(determiner(much)) --> [much].
det(determiner(an)) --> [an].
det(determiner(all)) --> [all].
det(determiner(this)) --> [this].
det(determiner(that)) --> [that].


preposition(preposition(at)) --> [at].
preposition(preposition(behind)) --> [behind].
preposition(preposition(after)) --> [after].
preposition(preposition(in)) --> [in].
preposition(preposition(for)) --> [for].
preposition(preposition(on)) --> [on].
preposition(preposition(over)) --> [over].
preposition(preposition(under)) --> [under].
preposition(preposition(to)) --> [to].
preposition(preposition(into)) --> [into].
