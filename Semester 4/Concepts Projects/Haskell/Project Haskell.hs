
-- Ex Student : Midterm, Quizzes, Name, Pass/Fail label
data Ex= Ex Float Float String String deriving Show
-- New Student: Midterm, Quizzes,Name
data NewSt= NewSt Float Float String  deriving Show
--Distance: Distance New Student Old Student
data Dist = Dist Float NewSt Ex       deriving Show

--this function gives the squared value of a number x.
sqr x = x*x

--this function calculates the euclidean distance between a given new student and a given ex student.
euclidean:: NewSt -> Ex -> Dist
euclidean (NewSt mid1 q1 n1 ) (Ex mid2 q2 n2 p) = Dist (sqrt( sqr( mid1-mid2) + sqr(q1-q2))) (NewSt mid1 q1 n1) (Ex mid2 q2 n2 p)

--this function calculates the manhattan distance between a given new student and a given ex student.
manhattan:: NewSt -> Ex -> Dist
manhattan (NewSt mid1 q1 n1) (Ex mid2 q2 n2 p)= Dist ( abs(mid1-mid2) + abs(q1-q2)) (NewSt mid1 q1 n1) (Ex mid2 q2 n2 p)

--this function takes as an input a generic distance function (euclidean/manhattan) and two points (students), and returns a Dist holding the distance function applied on the two points.
dist :: (a -> b -> c) -> a -> b -> c
dist f x y = f x y

--this function given a distance function, a NewSt, and a list of Ex Students, returns a list of all the distances from the NewSt to elements in the list.
all_dists:: (a -> b -> c) -> a -> [b] -> [c]
all_dists f x [] = []
all_dists f x (y:ys)= (f x y): all_dists f x ys

--this is a helper function used to insert a Dist in a list of Dist in a sorted order. It is used for insertion sort for the next function.
insertDist x [] = [x]
insertDist (Dist x1 n1 e1) ((Dist x2 n2 e2):ys) | x1<x2 = (Dist x1 n1 e1):(Dist x2 n2 e2):ys
												| otherwise = (Dist x2 n2 e2) : insertDist (Dist x1 n1 e1) ys												

--this is a helper function that sorts a given list of Dist using insetion sort (utilizes the insertDist helper function).
sortDist [] = []
sortDist (x:xs)= insertDist x(sortDist xs)

--this function given a number n and a list l, returns the first n elements from that list.						
takeN ::Num a=> a ->[b]->[b]
takeN 0 _ = []
takeN _ [] = []
takeN n (x:xs) = x: (takeN (n-1) xs)

--this function given a distance function, a number n, a list of Ext and a St (New student) to be classified, returns a list of Dist objects that are the closest n objects to the St object using sortDist helper function to sort the list of Distances computed using all_dists function.
closest:: Num a =>(b->c->Dist) -> a -> [c] -> b -> [Dist]
closest f n x y = takeN n (sortDist(all_dists f y x)) 

--this is a helper function given a Dist object returns whether the Ex student in that Dist has a pass Label or not.
pass  (Dist _ _ (Ex _ _ _ l1)) = if(l1 == "pass") then True
										else False

--this is a helper function given a list of Dist objects, returns a list of Dist objects of the students who has a pass label in the given list using pass helper function.										
passL []= []										
passL (x:xs) = if (pass x) then x: passL xs else passL xs

--this is a helper function given a list of Dist objects, returns a list of Dist objects of the students who has a fail label in the given list using pass helper function.	
failL [] = []
failL (x:xs) = if( pass x) then failL xs else x:failL xs

--this is a helper function given a list of Distances groups the students with pass label in one list and the students with fail label in one list and returns a list of lists with these 2 lists. It uses the helper passL and failL functions.
myGroup x =   [(passL x),(failL x)]		

--this function given a distance function, a number n, a list of Ext and a St object, returns a list of lists of Dist objects containing 2 lists, the first for “pass” and second for “fail”, utilizes the function closest. It also uses the helper function myGroup to group the the lists together.			
grouped_dists:: Num a => (b -> c -> Dist) -> a -> [c] -> b -> [[Dist]]
grouped_dists f n x y = myGroup(closest f n x y)

--this is a helper function given a list of lists returns the list has the greater length between them.
myMax [x,y] = if (length x > length y) then x else y
myMax (x:y:ys) = if(length x > length y) then (myMax (x:ys)) else (myMax(y:ys)) 

--this function given a distance function, a number n, a list of Ext and a St object, returns a list of Dist objects contain the Dist objects of only the most frequent label; this is done by using the helper myMax function that returns the most frequent list label of the grouped_dists function.
mode:: Num a => (b -> c -> Dist) -> a -> [c] -> b -> [Dist]
mode f n x y= myMax(grouped_dists f n x y)

--this function given a list of Dist objects, extracts a tuple from the first element of the list containing the name of the NewSt object and the label of the Ex object.
label_of:: [Dist] -> ([Char],[Char])
label_of (Dist _ (NewSt _ _ s1) (Ex _ _ _ l1):_)= (s1,l1)

--this function given a distance function, a number n, a list of Ex and a NewSt object, returns a tuple containing the name of the NewSt object and the predicted label (most repeated class in the k-neighborhood) using label_of and mode functions.
classify:: Num a => (b -> c -> Dist) -> a -> [c] -> b -> ([Char],[Char])
classify f n x y = label_of(mode f n x y)

--this function given a distance function, a number k, a list old of Ex objects, and a list new of NewSt objects, returns the predictions for every element in new using the k-neighborhood from the list old. The result is a list of tuples, containing the name and label of each element, it uses the function classify.
classify_all :: (a -> b -> Dist) -> Int -> [b] -> [a] -> [([Char],[Char])]
classify_all _ _ _ [] =[]
classify_all f k x (y:ys) = (classify f k x y):(classify_all f k x ys)