//gcc -o sampleOut OpenGL2DTemplatecopy.cpp -framework GLUT -framework OpenGL -Wno-deprecated && ./sampleOut
#ifdef __APPLE__
#include <OpenGL/gl.h>
#else
#include <GL/gl.h>
#endif
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif
using namespace std;
#include <string.h>
#include <math.h>
#include <iostream>
#include <string>
#include <stdlib.h>
#include <vector>
#include <algorithm> 
#include <stdio.h>
float t=0;
float tdef=0;                 //randombezier
int speX=0;                   //mainX 
int speY=0;
int speC=0;
int tal2aX=0;
int tal2aY=100;               //bullet y
GLboolean tal2afired = false;
int enemyX = 0;               //beznewx
int enemyY =0;	              //beznewx
// int enemyXOld = 0;
// int enemyYOld = 0;
int p0[2];
int p1[2];
int p2[2];
int p3[2];

int defX = 0;                //beznewxdef
int defY =0;	             //beznewxdef
int p0def[2];
int p1def[2];
int p2def[2];
int p3def[2];

int enemyXOld=0;
int anglePlayer=0;
int angleEnemy=-5;
int obstacleX=0;
int obstacleY=0;
int originX=0;
int originY=0;

int obstacleDefX=0;
int obstacleDefY=0;
int originDefX=0;
int originDefY=0;

int healthX=0;
int healthY=4000;
int scoreX=0;
int scoreY=6000;

//GLboolean obstaclefired=false;

int score=0;
int incHealth=3;
int health=incHealth;
int rect1Y=1000;
int rect11Y=1000;
int rect2Y=1000;
int rect22Y=1000;
int rect222Y=1000;
int rect2222Y=1000;
int rect3Y=1000;
int rect33Y=1000;
int triY=1000;
GLboolean lose=false;
GLboolean enemyDead=false;
GLboolean night=false;
// GLboolean enemyRight=false;
// GLboolean enemyLeft=false;
GLboolean defAppeared=false;


void drawCircle(int specX, int tal2aY, float r); 
void fire(int value);
int* bezier(float t, int* p0,int* p1,int* p2,int* p3);
void print(int x, int y, char *string);
void Anim();
void drawRect(int x, int y, int w, int h);
void powerups(int value);
void healthRandom(int value);
void scoreRandom(int value);
void obstacleFire(int value);
void enemyBulletMotion(int value);
void obstacleFireDef(int value);
void defBulletMotion(int value);
void street(int value);
void enemyLive(int value);
void defAppear(int value);


void Display() {
	glClear(GL_COLOR_BUFFER_BIT);


	if(!night)
	{
		glClearColor(0.80f, 0.80f, 0.80f, 0.0f);
	}
	if(night)
	{
		glClearColor(0.329412f, 0.329412f, 0.329412f, 0.0f);
	}

	if(lose){
	
		print(200,200,"GAME IS OVER");
		print(300,300,"GAME IS OVER");
		print(400,400,"GAME IS OVER");
		print(500,500,"GAME IS OVER");
		print(600,600,"GAME IS OVER");
		
		
	}
/////////////////////////////////////////////////////////////////////
/////background objects

	glPushMatrix();                      //LEFT SIDE
	//	glTranslated(0,rect1Y,0);
		glColor3f(1.0f, 1.0f, 1.0f);
			drawRect(20,rect1Y+100,20,80);
			drawRect(20,rect1Y+220,20,120);
			drawRect(20,rect1Y+440,20,80);
			drawRect(20,rect1Y+560,20,120);
			drawRect(20,rect1Y+900,20,80);

			drawRect(20,rect11Y+1020,20,80);
			drawRect(20,rect11Y+1240,20,120);
			drawRect(20,rect11Y+1360,20,80);
			drawRect(20,rect11Y+1700,20,120);
			drawRect(20,rect11Y+1840,20,80);
			
			glPushMatrix();
				glTranslated(0,triY,0);
				glBegin(GL_TRIANGLES);
				glColor3f(1.0f, 1.0f, 0.0f);
				glVertex3f(15.0f, 100.0f, 0.0f);
				glColor3f(1.0f, 1.0f, 0.0f);
				glVertex3f(40.0f, 150.0f, 0.0f);
				glColor3f(1.0f, 1.0f, 0.0f);
				glVertex3f(65.0f, 100.0f, 0.0f);
				glEnd();
				glColor3f(0.0f, 0.0f, 0.0f);
				print(35,105,"!");
			glPopMatrix();


	glPopMatrix();


	glPushMatrix();                   //Middle
		//glTranslated(0,rect2Y,0);
		glColor3f(1.0f, 1.0f, 1.0f);
			drawRect(420,rect2Y+600,20,190);
			drawRect(420,rect22Y+200,20,190);

			drawRect(420,rect222Y+1000,20,190);
			drawRect(420,rect2222Y+1600,20,190);
	glPopMatrix();


	glPushMatrix();                   //RIGHT SIDE
		//glTranslated(0,rect3Y,0);
		glColor3f(1.0f, 1.0f, 1.0f);
			drawRect(820,rect3Y+100,20,80);
			drawRect(820,rect3Y+220,20,120);
			drawRect(820,rect3Y+440,20,80);
			drawRect(820,rect3Y+560,20,120);
			drawRect(820,rect3Y+900,20,80);

			drawRect(20,rect33Y+1020,20,80);
			drawRect(20,rect33Y+1240,20,120);
			drawRect(20,rect33Y+1360,20,80);
			drawRect(20,rect33Y+1700,20,120);
			drawRect(20,rect33Y+1840,20,80);
	glPopMatrix();





//////////////////////////////////////////////////////////////////////

	//////////////////////////////PRINT SCORE 
	glPushMatrix();
		glColor3f(0.7f, 0.08f, 0.44f);
		print(855,800,"Your Score:");
		char* scoreT[20];
		sprintf((char *)scoreT,"%d",score);
		print(855,700,(char*)scoreT);
	glPopMatrix();
	

/////////////////////////////////////////////////////////////////////////////////
////////////////////Main player bullets
	glPushMatrix();
		glColor3f(1.0f, 0.0f, 0.0f); 
		glTranslated(tal2aX,tal2aY,0);              
		drawCircle(200, 120 , 20.0);
		glColor3f(0.0f, 1.0f, 0.0f);
		drawCircle(210, 100 , 20.0);
		glColor3f(0.0f, 0.0f, 1.0f);
		drawCircle(190, 100 , 20.0);
	glPopMatrix();

////////////////////////////////////////////////////////////////////////////////

///////////////////Main character
	glPushMatrix();
		glTranslated(speX,speY,0);
		glRotated(anglePlayer,0,0,1); 

		glBegin(GL_TRIANGLES);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(200.0f, 200.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(150.0f, 100.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(250.0f, 100.0f, 0.0f);
		glEnd();

		glBegin(GL_POLYGON);
			glColor3f(1.0f, 0.0f, 1.0f);
			glVertex3f(50.0f, 100.0f, 0.0f);
			glColor3f(1.0f, 0.0f, 1.0f);
			glVertex3f(350.0f, 100.0f, 0.0f);
			glColor3f(1.0f, 0.0f, 1.0f);
			glVertex3f(350.0f, 50.0f, 0.0f);
			glColor3f(1.0f, 0.0f, 1.0f);
			glVertex3f(50.0f, 50.0f, 0.0f);
		glEnd();

		glPushMatrix();
			glBegin(GL_TRIANGLES);
				glColor3f(2.0f, 2.0f, 2.0f);
				glVertex3f(200.0f, 100.0f, 0.0f);
				glColor3f(2.0f, 2.0f, 2.0f);
				glVertex3f(150.0f, 50.0f, 0.0f);
				glColor3f(2.0f, 2.0f, 2.0f);
				glVertex3f(250.0f, 50.0f, 0.0f);
			glEnd();
			glColor3f(0.0f, 0.0f, 0.0f);
			print(110,60,"Hebz to the Webz");
		glPopMatrix();

		glBegin(GL_LINES);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(50.0f, 100.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(50.0f, 200.0f, 0.0f);
		glEnd();

		glBegin(GL_LINES);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(350.0f, 100.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(350.0f, 200.0f, 0.0f);
		glEnd();
		glPushMatrix(); 				//wheels
			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(80,60,30.0);
			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(320,60,30.0);

			glColor3f(1.0f, 1.0f, 1.0f);
			drawCircle(80,60,25.0);
			glColor3f(1.0f, 1.0f, 1.0f);
			drawCircle(320,60,25.0);

			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(80,60,20.0);
			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(320,60,20.0);

			glColor3f(1.0f, 1.0f, 1.0f);
			drawCircle(80,60,15.0);
			glColor3f(1.0f, 1.0f, 1.0f);
			drawCircle(320,60,15.0);
		
			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(80,60,10.0);
			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(320,60,10.0);
		glPopMatrix();
	glPopMatrix();


////////////////////////////////////////////////////////////////////////
////////////////////Enemy
	glPushMatrix();

		glTranslated(enemyX,enemyY,0);
		glTranslated(enemyX,enemyY,0);
		glRotated(angleEnemy,0,0,1);
		glTranslated(-enemyX,-enemyY,0);


		glBegin(GL_TRIANGLES);                	    //hair
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(378.0f, 600.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(405.0f, 605.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(395.0f, 635.0f, 0.0f);
		glEnd();
		glBegin(GL_TRIANGLES);                	    //hair
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(395.0f, 600.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(415.0f, 600.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(405.0f, 620.0f, 0.0f);
		glEnd();
		glBegin(GL_TRIANGLES);                	    //hair
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(405.0f, 595.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(425.0f, 595.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 1.0f);
			glVertex3f(415.0f, 615.0f, 0.0f);
		glEnd();
		
		glColor3f(1.0f, 1.0f, 1.0f);
		drawCircle(400,580,30.0);      				 //head
		
		glBegin(GL_QUADS);	
			glColor3f(1.0f, 1.0f, 0.0f);		   	//legs
			glVertex3f(370.0f, 500.0f, 0.0f); 
			glVertex3f(380.0f, 500.0f, 0.0f);
			glVertex3f(380.0f, 400.0f, 0.0f);
			glVertex3f(370.0f, 400.0f, 0.0f);

			glVertex3f(420.0f, 500.0f, 0.0f);
			glVertex3f(430.0f, 500.0f, 0.0f);
			glVertex3f(430.0f, 400.0f, 0.0f);
			glVertex3f(420.0f, 400.0f, 0.0f);

			
		glEnd();
		
		glColor3f(1.0f, 1.0f, 1.0f);
		drawCircle(400,500,50.0);            	    //body
		
		glBegin(GL_TRIANGLES);                	    //mouth
			glColor3f(1.0f, 0.0f, 0.0f);
			glVertex3f(390.0f, 580.0f, 0.0f);
			glColor3f(1.0f, 0.0f, 0.0f);
			glVertex3f(410.0f, 580.0f, 0.0f);
			glColor3f(1.0f, 0.0f, 0.0f);
			glVertex3f(400.0f, 570.0f, 0.0f);
		glEnd();

		glPushMatrix();
			glColor3f(0.0f, 0.0f, 0.0f);     //eyes
			drawCircle(385, 590, 6.0);

			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(415, 590, 6.0);

			glColor3f(1.0f, 1.0f, 1.0f);
			drawCircle(385, 590, 5.0);

			glColor3f(1.0f, 1.0f, 1.0f);
			drawCircle(415, 590, 5.0);


			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(385, 590, 2.0);

			glColor3f(0.0f, 0.0f, 0.0f);
			drawCircle(415, 590, 2.0);	
		glPopMatrix();

		glPushMatrix();
			glBegin(GL_TRIANGLES);                	    //wingleft
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(370.0f, 500.0f, 0.0f);
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(330.0f, 500.0f, 0.0f);
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(350.0f, 470.0f, 0.0f);
			glEnd();

			glBegin(GL_TRIANGLES);                	    //wingleft
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(370.0f, 500.0f, 0.0f);
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(330.0f, 500.0f, 0.0f);
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(360.0f, 530.0f, 0.0f);
			glEnd();
		glPopMatrix();

		glPushMatrix();
			glBegin(GL_TRIANGLES);                	    //wingright
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(470.0f, 500.0f, 0.0f);
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(430.0f, 500.0f, 0.0f);
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(450.0f, 470.0f, 0.0f);
			glEnd();

			glBegin(GL_TRIANGLES);                	    //wingright
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(470.0f, 500.0f, 0.0f);
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(430.0f, 500.0f, 0.0f);
				glColor3f(0.0f, 0.0f, 1.0f);
				glVertex3f(440.0f, 530.0f, 0.0f);
			glEnd();
		glPopMatrix();
	glPopMatrix();

/////////////////////////////////////////////////////////////////////
	glPushMatrix();                       				  //obstacle
		glTranslated(0,obstacleY,0);
		glColor3f(0.5f, 0.35f, 0.10f);      
		drawCircle(originX, originY+10, 20.0);
		glColor3f(0.5f, 0.40f, 0.05f);
		drawCircle(originX+10, originY, 20.0);
		glColor3f(0.6f, 0.35f, 0.05f);
		drawCircle(originX-10, originY, 20.0);
	glPopMatrix();



//////////////////////////////////////////////////////////////////////////
///////////////////////////HEALTH BAR and PRINT HEALTH and hint
	glPushMatrix();
		glColor3f(1.0f, 0.1f, 0.01f);
		print(845,600,"Enemy Health:");
		drawRect(850, 500, health * 10 , 50);
	glPopMatrix();

	glPushMatrix();
		glColor3f(0.439216f, 0.576471f, 0.858824f);
		print(845,400,"Hint:    ");
		print(845,350, "Don't let");
		print(845,300, "the enemy");
		print(845,250, "hit your");
		print(845,200, "cone ;)");
	glPopMatrix();





/////////////////////////////////////////////////////////////////

////POWERUPS: 
//ENEMY HEALTH DECREASE BY 1
	glPushMatrix();
		glTranslated(0,healthY,0);
		glColor3f(1.0f, 0.1f, 0.01f);
		drawCircle(healthX, 10, 30.0);
	glPopMatrix();

//PLAYER SCORE INCREASE BY 1 
	glPushMatrix();
		glTranslated(0,scoreY,0);
		glColor3f(0.7f, 0.08f, 0.44f);
		drawRect(scoreX, 20, 50, 50);
	glPopMatrix();

/////////////////////////////////////////////////////////////////////

/////////////////enemy defendor
	glPushMatrix();
		glTranslated(defX,defY,0);
		
		glBegin(GL_TRIANGLES);                	    //hair
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(385.0f, 375.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(405.0f, 375.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(395.0f, 400.0f, 0.0f);
		glEnd();
		glBegin(GL_TRIANGLES);                	    //hair
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(395.0f, 370.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(415.0f, 370.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(405.0f, 390.0f, 0.0f);
		glEnd();
		glBegin(GL_TRIANGLES);                	    //hair
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(405.0f, 365.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(425.0f, 365.0f, 0.0f);
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(415.0f, 380.0f, 0.0f);
		glEnd();

		glColor3f(1.0f, 1.0f, 0.1f); 				//body
		drawCircle(400, 350, 30.0);
		

		glColor3f(0.0f, 0.0f, 0.0f);
		drawCircle(390, 350, 6.0);

		glColor3f(0.0f, 0.0f, 0.0f);
		drawCircle(410, 350, 6.0);

		glColor3f(1.0f, 1.0f, 1.0f); //eyes
		drawCircle(390, 350, 5.0);

		glColor3f(1.0f, 1.0f, 1.0f);
		drawCircle(410, 350, 5.0);


		glColor3f(0.0f, 0.0f, 0.0f);
		drawCircle(390, 350, 2.0);

		glColor3f(0.0f, 0.0f, 0.0f);
		drawCircle(410, 350, 2.0);
		
		glBegin(GL_TRIANGLES);                	    //mouth
			glColor3f(1.0f, 0.0f, 0.0f);
			glVertex3f(390.0f, 340.0f, 0.0f);
			glColor3f(1.0f, 0.0f, 0.0f);
			glVertex3f(410.0f, 340.0f, 0.0f);
			glColor3f(1.0f, 0.0f, 0.0f);
			glVertex3f(400.0f, 330.0f, 0.0f);
		glEnd();

	glPopMatrix();


	////////////////////////bullet enemy defendor

	glPushMatrix();
		glTranslated(0,obstacleDefY,0);
		glColor3f(0.5f, 0.35f, 0.10f);               
		drawCircle(originDefX, originDefY-30, 10.0);
	glPopMatrix();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	glFlush();
}


void drawCircle(int x, int y, float r) {
  glPushMatrix();
  glTranslatef(x, y, 0);
  GLUquadric *quadObj = gluNewQuadric();
  gluDisk(quadObj, 0, r, 50, 50);
  glPopMatrix();
}


int* bezier(float t, int* p0,int* p1,int* p2,int* p3)
{
	int res[2];
	res[0]=pow((1-t),3)*p0[0]+3*t*pow((1-t),2)*p1[0]+3*pow(t,2)*(1-t)*p2[0]+pow(t,3)*p3[0];
	res[1]=pow((1-t),3)*p0[1]+3*t*pow((1-t),2)*p1[1]+3*pow(t,2)*(1-t)*p2[1]+pow(t,3)*p3[1];
	return res;
}

void spe(int k, int x,int y){// keyboard special key function takes 3 parameters
							// int k: is the special key pressed such as the keyboard arrows the f1,2,3 and so on

	if(k==GLUT_KEY_RIGHT)//if the right arrow is pressed, then the object will be translated in the x axis by 10. (moving right)
	{	
		if(!lose)
		{
		speX+=120;
		anglePlayer= -3;
		if(!tal2afired){
			tal2aX+=120;
		}
		}
	}
	if(k==GLUT_KEY_LEFT)//if the left arrow is pressed, then the object will be translated in the x axis by -10. (moving left)
	{	
		if(!lose)
		{
		speX-=120;
		anglePlayer= 3;
		if(!tal2afired){
			tal2aX-=120;
		}
		}
	}
	if(k==GLUT_KEY_UP){//if the up arrow is pressed, then the object will be translated in the y axis by 10. (moving upwords)
		if(!lose){
		tal2afired=true;
		fire(0);
		}
	}	
	// if(k==GLUT_KEY_DOWN)//if the down arrow is pressed, then the object will be translated in the y axis by -10. (moving downwords)
	// 	speY-=10;
	if(k==GLUT_KEY_F1)//if the F1 key is pressed, then the object color will be changed
		speC=1;
	glutPostRedisplay();//redisplay to update the screen with the changes
}

void speUp(int k, int x,int y){// keyboard special key function is called whenever the special key is released.

	if(k==GLUT_KEY_F1)//if the F1 key is released, then the object will return back to it's original color.
		speC=0;
	else{
		if(k==GLUT_KEY_RIGHT)
			anglePlayer= 0;
		if(k==GLUT_KEY_LEFT)
			anglePlayer= 0;
	}
	glutPostRedisplay();//redisplay to update the screen with the changes
}

void drawRect(int x, int y, int w, int h) {
  glBegin(GL_POLYGON);
  glVertex2f(x, y);
  glVertex2f(x + w, y);
  glVertex2f(x + w, y + h);
  glVertex2f(x, y + h);
  glEnd();
}


void print(int x, int y, char *string)
{
	int len, i;
	//set the position of the text in the window using the x and y coordinates
	glRasterPos2f(x, y);
	//get the length of the string to display
	len = (int) strlen(string);
	//loop to display character by character
	for (i = 0; i < len; i++)
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24,string[i]);
	}
}


//FIRE PLAYER BULLETS ON ENEMY
void fire(int value)
{
	if((tal2aY<1000) && (tal2afired == true)){
		tal2aY+= 100;
			
		if(tal2aY>(defY+250)) //bullet is in range of defendor
		{
			if(tal2aX>(defX+150)  && (tal2aX<(defX+250) ))
			{
				tal2aY=100;
				tal2aX=speX;
				tal2afired=false;
			}
		}
		if(tal2aY>(enemyY+300)){  //bullet is not in rangle of defendor and in range of enemy
			if(tal2aX>(enemyX+110)  && (tal2aX<(enemyX+270) ))
			{
				if(health>0)
				{
					health--;
				}
					if(health==0)
					{
						enemyDead=true;
						night = ! night;
						health=incHealth+1;
						incHealth++;
					}
				score++;
				tal2aY=100;
				tal2aX=speX;
				tal2afired=false;
			}
		}	
		glutPostRedisplay();
		glutTimerFunc(100,fire,0);
	}
	else{
		tal2aY=100;
		tal2aX=speX;
		tal2afired=false;
	}
}

void enemyBulletMotion(int value){
    obstacleY-=100;
	if((obstacleY<(speY+100)) && obstacleY>0)
	{
		if((originX<(speX+250)) && (originX>(speX+90)))
		{
		lose=true;
		}
	}


    glutPostRedisplay();
    glutTimerFunc(700,enemyBulletMotion,700);

}

void obstacleFire(int value){
    
    originX=enemyX+400;
    originY=enemyY+200;
    obstacleY=enemyY;
    glutPostRedisplay();

    glutTimerFunc(700,enemyBulletMotion,700);
    glutTimerFunc(5000,obstacleFire,5000);
   
}	


void defBulletMotion(int value){
    obstacleDefY-=100;
	if((obstacleDefY<(speY+100)) && obstacleDefY>0)
	{
		if((originDefX<(speX+250)) && (originDefX>(speX+90)))
		{
		lose=true;
		}
	}

    glutPostRedisplay();
    glutTimerFunc(700,defBulletMotion,700);

}

void obstacleFireDef(int value){
    
    originDefX=defX+400;
    originDefY=defY+200;
    obstacleDefY=defY;
    glutPostRedisplay();

    glutTimerFunc(700,defBulletMotion,700);
    glutTimerFunc(6000,obstacleFireDef,6000);
   
}	

void powerups(int value){

	//Health
	if(healthY>0){
		healthY-=50;
	}
	else
	{
		healthY=(rand()%1000)+6000;
	}
	if(healthY<=(speY+200))
	{
		if((healthX>=(speX+20))  && (healthX<=(speX+360)))
		{
			if(health>0)
			{
				health--;
			}
			else 
			{
				if(health==0)
				{
					health=incHealth+1;
					incHealth++;
				}
			}
			healthY=(rand()%1000)+6000;

		}
	}
	//Score
	if(scoreY>0){
		scoreY-=50;
	}
	else
	{
		scoreY=(rand()%1000)+4000;;
	}
	if(scoreY<=(speY+200))
		{
			if((scoreX>=(speX+20))  && (scoreX<=(speX+360)))
			{
				score++;
				scoreY=(rand()%1000)+4000;;

			}
		}
	glutPostRedisplay();
	glutTimerFunc(300,powerups,0);
}

void healthRandom(int value){
	healthX = (rand()% 700);
	glutPostRedisplay();
	glutTimerFunc(25000,healthRandom,0);
}

void scoreRandom(int value){
	scoreX = (rand()% 700);
	glutPostRedisplay();
	glutTimerFunc(25000,scoreRandom,0);
}


void street(int value){
	
	if((triY+100)>-200){
		triY-=10;
	}
	else{
		if((triY+100)==-200){	
			triY=1000;
		}
	}
	if((rect1Y+100)>-800){
		rect1Y-=10;
	}
	else{
		if((rect1Y+100)==-800){	
			rect1Y=1000;
		}
	}
	if((rect11Y+100)>-1600){
		rect11Y-=10;
	}
	else{
		if((rect11Y+100)==-1600){	
			rect11Y=1000;
		}
	}
    /////////////////////////////////

	if((rect2Y+200)>-400){
		rect2Y-=10;
	}
	else{
		if((rect2Y+200)==-400){	
			rect2Y=1000;
		}
	}
	if((rect22Y+200)>-800){
		rect22Y-=10;
	}
	else{
		if((rect22Y+200)==-800){	
			rect22Y=1000;
		}
	}

	if((rect222Y+200)>-16000){
		rect222Y-=10;
	}
	else{
		if((rect222Y+200)==-16000){	
			rect222Y=1000;
		}
	}
		if((rect2222Y+200)>-18000){
		rect2222Y-=10;
	}
	else{
		if((rect2222Y+200)==-18000){	
			rect2222Y=1000;
		}
	}
	/////////////////////////////////////
	if((rect3Y+100)>-800){
		rect3Y-=10;
	}
	else{
		if((rect3Y+100)==-800){	
			rect3Y=1000;
		}
	}

	if((rect33Y+100)>-1600){
		rect33Y-=10;
	}
	else{
		if((rect33Y+100)==-1600){	
			rect33Y=1000;
		}
	}
	glutPostRedisplay();
	glutTimerFunc(100,street,0);
}

void enemyLive(int value){
	enemyDead=false;	
}


void defAppear(int value){
	defAppeared=true;
}

void Anim()
{
	//bezier for the enemy
	if(t<1){
	 t+=0.001;

	 int* p = bezier(t,p0,p1,p2,p3);
	 if(!enemyDead){
		enemyXOld=enemyX;
	 	enemyX= p[0];
	 	enemyY= p[1];
	 }
	 else{
		enemyX=2000;
		enemyY=2000;
	    glutTimerFunc(4000,enemyLive,0);
	 }
	}
	else{
		t=0;
		p0[0]=p3[0];
		p0[1]=p3[1];
		p1[0]=(rand() % 600) - 300;
		p1[1]=(rand() % 300) + 100;
		p2[0]=(rand() % 600) - 300;
		p2[1]=(rand() % 300) + 100;
		p3[0]=(rand() % 600) - 300;
		p3[1]=(rand() % 300) + 100;
	}

	//bezier for the defendor
	if(tdef<1){
	 tdef+=0.001;
	 int* pdef = bezier(tdef,p0def,p1def,p2def,p3def);
	 	if(defAppeared)
		 {
			defX= pdef[0];
			defY= pdef[1];
		}
		else
		{
			defX=-1000;
			defY=-1000;
		}
	}
	else{
		tdef=0;
		angleEnemy = - angleEnemy;
		p0def[0]=p3def[0];
		p0def[1]=p3def[1];
		p1def[0]=(rand() % 600) - 300;
		p1def[1]=(rand() % 200) + 100;
		p2def[0]=(rand() % 600) - 300;
		p2def[1]=(rand() % 200) + 100;
		p3def[0]=(rand() % 600) - 300;
		p3def[1]=(rand() % 200) + 100;
	}


  glutPostRedisplay();
}

int main(int argc, char** argr) {
	glutInit(&argc, argr);

	glutInitWindowSize(1000, 1000);
	glutInitWindowPosition(150, 150);
	p0[0]=100;
	p0[1]=100;
	p1[0]=100;
	p1[1]=500;
	p2[0]=350;
	p2[1]=500;
	p3[0]=350;
	p3[1]=100;

	p0def[0]=100;
	p0def[1]=50;
	p1def[0]=100;
	p1def[1]=400;
	p2def[0]=350;
	p2def[1]=400;
	p3def[0]=350;
	p3def[1]=50;

	glutCreateWindow("Assignment1");
	glutDisplayFunc(Display);
	glutSpecialFunc(spe);							//call the keyboard special keys function
	
	glutIdleFunc(Anim);			
	glutSpecialUpFunc(speUp); 						//call the keyboard special keys up function
	glutTimerFunc(0, fire, 0);
	glutTimerFunc(0, powerups, 0);	
	glutTimerFunc(0,street,0);
	glutTimerFunc(0, healthRandom, 0);
	glutTimerFunc(0, scoreRandom, 0);	
	glutTimerFunc(5000,obstacleFire,5000);
	glutTimerFunc(6000,obstacleFireDef,6000);
	glutTimerFunc(25000,enemyLive,0);
	glutTimerFunc(25000,defAppear,0);

	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glPointSize(9.0);
	
	street(0);
	obstacleFire(0);
	obstacleFireDef(0);
	gluOrtho2D(0.0, 1000, 0.0, 1000);
	glutMainLoop();
	return 0;
}
