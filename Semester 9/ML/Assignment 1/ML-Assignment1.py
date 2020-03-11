#!/usr/bin/env python
# coding: utf-8

# In[700]:


import numpy as np
get_ipython().run_line_magic('matplotlib', 'inline')
import matplotlib.pyplot as plt


# In[701]:


#reads training data set images to matrix 
def readTrainingImages():
    
    mat = np.zeros((2400,784))
    for x in range(1,2400):
        image = plt.imread('/Users/heba/Desktop/ML/Assignment 1/Train/'+ str(x) +'.jpg')
        array = np.array(image)
        flatarray = array.flatten()
        mat[x,:] = np.array(flatarray)
    return mat


# In[702]:


Xmatrix = readTrainingImages() #This would be the X matrix each row is an image (2400x785)
np.sum(Xmatrix)


# In[703]:


#Add a colomn of 1s
ones = np.ones((2400,1))
ones


# In[704]:


XNew = np.hstack((Xmatrix,ones)) #X tilda with an extra column of ones
XNew


# In[705]:


XTranspose = Xnew.T
XTranspose.shape


# In[706]:


XMult = np.matmul(XTranspose,XNew)
XMult.shape


# In[707]:


#for pseudoinverse because matrix isn't singular so regular inv wont work
XInverse = np.linalg.pinv(XMult)
XInverse


# In[708]:


XFinal = np.matmul(XInverse, XTranspose)
XFinal


# In[709]:


T0ones = np.ones((2400,1))
T1ones = np.ones((2400,1))
T2ones = np.ones((2400,1))
T3ones = np.ones((2400,1))
T4ones = np.ones((2400,1))
T5ones = np.ones((2400,1))
T6ones = np.ones((2400,1))
T7ones = np.ones((2400,1))
T8ones = np.ones((2400,1))
T9ones = np.ones((2400,1))


# In[710]:


T0 = T0ones * -1 
T1 = T1ones * -1 
T2 = T2ones * -1 
T3 = T3ones * -1 
T4 = T4ones * -1 
T5 = T5ones * -1 
T6 = T6ones * -1 
T7 = T7ones * -1 
T8 = T8ones * -1 
T9 = T9ones * -1 


# In[711]:


LabelTxtFile = open("/Users/heba/Desktop/ML/Assignment 1/Train/Training Labels.txt", "r")
count=0
for x in LabelTxtFile:
    x = int(x)
    if(x==0):
        T0[count] = 1
    elif(x==1):
        T1[count] = 1
    elif(x==2):
        T2[count] = 1
    elif(x==3):
        T3[count] = 1
    elif(x==4):
        T4[count] = 1
    elif(x==5):
        T5[count] = 1
    elif(x==6):
        T6[count] = 1
    elif(x==7):
        T7[count] = 1
    elif(x==8):
        T8[count] = 1
    elif(x==9):    
        T9[count] = 1
    
    count+=1


# In[712]:


T0


# In[713]:


#W tilda for each of the digits
W0 = np.matmul(XFinal, T0)
W1 = np.matmul(XFinal, T1)
W2 = np.matmul(XFinal, T2)
W3 = np.matmul(XFinal, T3)
W4 = np.matmul(XFinal, T4)
W5 = np.matmul(XFinal, T5)
W6 = np.matmul(XFinal, T6)
W7 = np.matmul(XFinal, T7)
W8 = np.matmul(XFinal, T8)
W9 = np.matmul(XFinal, T9)


# In[610]:


W0.shape


# In[714]:


#Test data
def readTestImages():
    
    mat2 = np.zeros((200,784))
    for x in range(1,201):
        image2 = plt.imread('/Users/heba/Desktop/ML/Assignment 1/Test/'+ str(x) +'.jpg')
        array2 = np.array(image2)
        flatarray2 = array2.flatten()
        mat2[x-1,:] = np.array(flatarray2)
        
    return mat2


# In[715]:


XmatrixTest = readTestImages()
np.sum(XmatrixTest)


# In[716]:


ones2 = np.ones((200,1))
XNewTest = np.hstack((XmatrixTest,ones2))
XNewTest


# In[717]:


Prediction0 = np.matmul(XNewTest, W0)  
Prediction1 = np.matmul(XNewTest, W1)
Prediction2 = np.matmul(XNewTest, W2)
Prediction3 = np.matmul(XNewTest, W3)
Prediction4 = np.matmul(XNewTest, W4)
Prediction5 = np.matmul(XNewTest, W5)
Prediction6 = np.matmul(XNewTest, W6)
Prediction7 = np.matmul(XNewTest, W7)
Prediction8 = np.matmul(XNewTest, W8)
Prediction9 = np.matmul(XNewTest, W9)


# In[718]:


Prediction0.shape


# In[719]:


maximumTargetValue = 0
predictionLabels = np.ones((200,1))
for x in range(200):
    maximumTargetValue = np.argmax([Prediction0[x],Prediction1[x],Prediction2[x],Prediction3[x],Prediction4[x],Prediction5[x],Prediction6[x],Prediction7[x],Prediction8[x],Prediction9[x]])
    predictionLabels[x] = maximumTargetValue 


# In[720]:


LabelTxtFile2 = open("/Users/heba/Desktop/ML/Assignment 1/Test/Test Labels.txt", "r")
count=0
testLabels = np.ones((200,1))
for x in LabelTxtFile2:
    x = int(x)
    testLabels[count] = x
    count+=1


# In[721]:


def compute_confusion_matrix(true,pred):
    K = len(np.unique(true))
    result = np.zeros((K,K))
    
    for i in range(len(true)):
        result[int(true[i].item())][int(pred[i].item())] += 1
        
    return result


# In[722]:


confusionMatrix = compute_confusion_matrix(testLabels,predictionLabels)
confusionMatrix


# In[723]:


plt.imshow(confusionMatrix, interpolation='nearest')

plt.savefig('Confusion.jpg',bbox_inches='tight')
plt.show()

