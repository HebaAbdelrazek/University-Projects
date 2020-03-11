// //gcc -o sampleOut Lab_6_Sol.cpp -framework GLUT -framework OpenGL -Wno-deprecated && ./sampleOut

#include <math.h>
#include <iostream>
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
#include <string.h>
#include <stdio.h> 
#include <math.h>




#include <stdlib.h>




#define GLUT_KEY_ESCAPE 27
#define DEG2RAD(a) (a * 0.0174532925)



// float eyeXNew = 3.0f;
// float eyeYNew = 1.0f;
// float eyeZNew = 2.0f;
float eyeXNew = 1.5f;
float eyeYNew = 1.0f;
float eyeZNew = 1.5f;
float centerXnew = 0.0f; 
float centerYnew = 0.0f;
float centerZnew = 0.0f;


float clockx = 0.0; 
float clocky = 0.0;
float clockz = 0.0;
float clockcolorx = 0.5f; 
float clockcolory = 0.8f;
float clockcolorz = 1.0f;


float jackx = 0.0;
float jacky = 0.0;
float jackz = 0.0;
float jackcolorx = 0.0f;
float jackcolory = 0.2f;
float jackcolorz = 0.4f;


float teapotx = 0.0;
float teapoty = 0.0;
float teapotz = 0.0;
float teapotcolorx = 0.7f;
float teapotcolory = 0.8f;
float teapotcolorz = 0.6f;


float tablex = 0.0;
float tabley = 0.0;
float tablez = 0.0;
float tablecolorx = 0.4f;
float tablecolory = 0.2f;
float tablecolorz = 0.0f;

float chair1x = 0.0f;
float chair1y = 0.0f;
float chair1z = 0.0f;
float chair1colorx = 0.4f;
float chair1colory = 0.3f;
float chair1colorz = 0.1f;

float chair2x = 0.0f;
float chair2y = 0.0f;
float chair2z = 0.0f;
float chair2colorx = 0.4f;
float chair2colory = 0.2f;
float chair2colorz = 0.5f;


float cubes1x = 0.0;
float cubes1y = 0.0;
float cubes1z = 0.0;
float cubes1colorx = 0.3f;
float cubes1colory = 0.8f;
float cubes1colorz = 0.0f;


float cupboardx = 0.0;
float cupboardy = 0.0;
float cupboardz = 0.0;
float cupboardcolorx = 0.7f;
float cupboardcolory = 0.7f;
float cupboardcolorz = 0.7f;


float walll1x = 0.0;
float walll1y = 0.0;
float walll1z = 0.0;
float walll1colorx = 0.3f;
float walll1colory = 0.4f;
float walll1colorz = 0.4f;


float walll2x = 0.0;
float walll2y = 0.0;
float walll2z = 0.0;
float walll2colorx = 0.4f;
float walll2colory = 0.9f;
float walll2colorz = 0.8f;


float walll3x = 0.0;
float walll3y = 0.0;
float walll3z = 0.0;
float walll3colorx = 0.3f;
float walll3colory = 0.7f;
float walll3colorz = 0.6f;
/////////////////////////////////////////////////////////////////////////////////////
//bedroom

float wallb1x = 0.0;
float wallb1y = 0.0;
float wallb1z = 0.0;
float wallb1colorx = 0.7f;
float wallb1colory = 0.3f;
float wallb1colorz = 0.5f;

float wallb2x = 0.0;
float wallb2y = 0.0;
float wallb2z = 0.0;
float wallb2colorx = 0.9f;
float wallb2colory = 0.4f;
float wallb2colorz = 0.8f;

float wallb3x = 0.0;
float wallb3y = 0.0;
float wallb3z = 0.0;
float wallb3colorx = 0.9f;
float wallb3colory = 0.6f;
float wallb3colorz = 1.0f;


float bedx=0.0;
float bedy=0.0;
float bedz=0.0;

float bedcolorx=0.9f;
float bedcolory=0.8f;
float bedcolorz=0.5f;

float sheetx=0.0;
float sheety=0.0;
float sheetz=0.0;
float sheetcolorx=1.0f;
float sheetcolory=0.0f;
float sheetcolorz=0.6f;

float cushioncolorx = 1.0f;
float cushioncolory = 1.0f;
float cushioncolorz = 1.0f;

float cubes2x = 0.0;
float cubes2y = 0.0;
float cubes2z = 0.0;
float cubes2colorx = 0.8f;
float cubes2colory = 0.4f;
float cubes2colorz = 1.0f;

float dolabx=0.0;
float dolaby=0.0;
float dolabz=0.0;
float dolabcolorx = 0.4f;
float dolabcolory = 0.0f;
float dolabcolorz = 0.2f;

float maktabx=0.0;
float maktaby=0.0;
float maktabz=0.0;
float maktabcolorx=0.5f;
float maktabcolory=0.3f;
float maktabcolorz=0.0f;


float chair3x = 0.0f;
float chair3y = 0.0f;
float chair3z = 0.0f;
float chair3colorx = 0.5f;
float chair3colory = 0.2f;
float chair3colorz = 0.5f;

GLboolean animate1 = false;
GLboolean animate2 = false; 
GLboolean colorrand= false;

int redD1=1;
int greenD1=1;
int blueD1=1;

int redD2=1;
int greenD2=1;
int blueD2=1;

int redD3=1;
int greenD3=1;
int blueD3=1;

int redD4=1;
int greenD4=1;
int blueD4=1;

int redD5=1;
int greenD5=1;
int blueD5=1;

int redD6=1;
int greenD6=1;
int blueD6=1;

int redD7=1;
int greenD7=1;
int blueD7=1;

int redD8=1;
int greenD8=1;
int blueD8=1;

int redD9=1;
int greenD9=1;
int blueD9=1;

int redD10=1;
int greenD10=1;
int blueD10=1;

int redD11=1;
int greenD11=1;
int blueD11=1;

int redD12=1;
int greenD12=1;
int blueD12=1;

int redD13=1;
int greenD13=1;
int blueD13=1;

int redD14=1;
int greenD14=1;
int blueD14=1;

int redD15=1;
int greenD15=1;
int blueD15=1;

int redD16=1;
int greenD16=1;
int blueD16=1;

int redD17=1;
int greenD17=1;
int blueD17=1;

int redD18=1;
int greenD18=1;
int blueD18=1;

int redD19=1;
int greenD19=1;
int blueD19=1;

int redD20=1;
int greenD20=1;
int blueD20=1;

float ghostx = 0.0;
float ghosty = 0.0;
float ghostz = 0.0;

GLboolean ghost = false;

float appearx = 2.0;
float appeary = 2.0;
float appearz = 2.0;

float scale1x = 1.0;
float scale1y = 1.0;
float scale1z = 1.0;

///////////////////////////////////
class Vector3f {
public:
	float x, y, z;

	Vector3f(float _x = 0.0f, float _y = 0.0f, float _z = 0.0f) {
		x = _x;
		y = _y;
		z = _z;
	}

	Vector3f operator+(Vector3f v) {
		return Vector3f(x + v.x, y + v.y, z + v.z);
	}

	Vector3f operator-(Vector3f &v) {
		return Vector3f(x - v.x, y - v.y, z - v.z);
	}

	Vector3f operator*(float n) {
		return Vector3f(x * n, y * n, z * n);
	}

	Vector3f operator/(float n) {
		return Vector3f(x / n, y / n, z / n);
	}

	Vector3f unit() {
		return *this / sqrt(x * x + y * y + z * z);
	}

	Vector3f cross(Vector3f v) {
		return Vector3f(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
};

class Camera {
public:
	Vector3f eye, center, up;

	Camera(float eyeX = eyeXNew, float eyeY = eyeYNew, float eyeZ = eyeZNew, float centerX = centerXnew, float centerY = centerYnew, float centerZ = centerZnew, float upX = 0.0f, float upY = 1.0f, float upZ = 0.0f) {
		eye = Vector3f(eyeX, eyeY, eyeZ);
		center = Vector3f(centerX, centerY, centerZ);
		up = Vector3f(upX, upY, upZ);
	}

	void moveX(float d) {
		Vector3f right = up.cross(center - eye).unit();
		eye = eye + right * d;
		center = center + right * d;
	}

	void moveY(float d) {
		eye = eye + up.unit() * d;
		center = center + up.unit() * d;
	}

	void moveZ(float d) {
		Vector3f view = (center - eye).unit();
		eye = eye + view * d;
		center = center + view * d;
	}

	void rotateX(float a) {
		Vector3f view = (center - eye).unit();
		Vector3f right = up.cross(view).unit();
		view = view * cos(DEG2RAD(a)) + up * sin(DEG2RAD(a));
		up = view.cross(right);
		center = eye + view;
	}

	void rotateY(float a) {
		Vector3f view = (center - eye).unit();
		Vector3f right = up.cross(view).unit();
		view = view * cos(DEG2RAD(a)) + right * sin(DEG2RAD(a));
		right = view.cross(up);
		center = eye + view;
	}

	void look() {
		gluLookAt(
			eyeXNew, eyeYNew, eyeZNew,
			centerXnew, centerYnew, centerZnew,
			up.x, up.y, up.z
		);
	}
};

Camera camera;

void drawWall(double thickness) {
	glPushMatrix();
	glTranslated(0.5, 0.5 * thickness, 0.5);
	glScaled(1.0, thickness, 1.0);
	glutSolidCube(1);
	glPopMatrix();
}
void drawTableLeg(double thick, double len) {
	glPushMatrix();
	glTranslated(0, len / 2, 0);
	glScaled(thick, len, thick);
	glutSolidCube(1.0);
	glPopMatrix();
}
void drawJackPart() {
	glPushMatrix();
	glScaled(0.2, 0.2, 1.0);
	glutSolidSphere(1, 15, 15);
	glPopMatrix();
	glPushMatrix();
	glTranslated(0, 0, 1.2);
	glutSolidSphere(0.2, 15, 15);
	glTranslated(0, 0, -2.4);
	glutSolidSphere(0.2, 15, 15);
	glPopMatrix();
}
void drawJack() {
	glPushMatrix();
	drawJackPart();
	glRotated(90.0, 0, 1, 0);
	drawJackPart();
	glRotated(90.0, 1, 0, 0);
	drawJackPart();
	glPopMatrix();
}
void drawTable(double topWid, double topThick, double legThick, double legLen){
	glPushMatrix();
	glTranslated(0, legLen, 0);
	glScaled(topWid, topThick, topWid);
	glutSolidCube(1.0);
	glPopMatrix();

	double dist = 0.95*topWid / 2.0 - legThick / 2.0;
	glPushMatrix();
	glTranslated(dist, 0, dist);
	drawTableLeg(legThick, legLen);
	glTranslated(0, 0, -2 * dist);
	drawTableLeg(legThick, legLen);
	glTranslated(-2 * dist, 0, 2 * dist);
	drawTableLeg(legThick, legLen);
	glTranslated(0, 0, -2 * dist);
	drawTableLeg(legThick, legLen);
	glPopMatrix();
}

void setupLights() {
	GLfloat ambient[] = { 0.7f, 0.7f, 0.7, 1.0f };
	GLfloat diffuse[] = { 0.6f, 0.6f, 0.6, 1.0f };
	GLfloat specular[] = { 1.0f, 1.0f, 1.0, 1.0f };
	GLfloat shininess[] = { 50 };
	glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, ambient);
	glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuse);
	glMaterialfv(GL_FRONT, GL_SPECULAR, specular);
	glMaterialfv(GL_FRONT, GL_SHININESS, shininess);

	GLfloat lightIntensity[] = { 0.7f, 0.7f, 1, 1.0f };
	GLfloat lightPosition[] = { -7.0f, 6.0f, 3.0f, 0.0f };
	glLightfv(GL_LIGHT0, GL_POSITION, lightIntensity);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, lightIntensity);
}
void setupCamera() {
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(60, 640 / 480, 0.001, 100);

	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	camera.look();
}

void Display() {
	setupCamera();
	setupLights();

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


	/////////character
	glPushMatrix();
		glTranslated(appearx,appeary,appearz);
		glScaled(scale1x,scale1y,scale1z);
		glTranslated(ghostx,ghosty,ghostz);
		glPushMatrix(); //dress
			glColor3f(0.0f,0.0f,0.0f);
			glTranslated(0.5,0.1,0.85);
			glRotated(-90,1,0,0);
			glutSolidCone(0.1, 0.4, 30, 30);
		glPopMatrix();
		glPushMatrix(); //leg 1
			glTranslated(0.45,0.0,0.85);
			glColor3f(0.9f,0.2f,0.1f);
			drawTableLeg(0.02,0.2);
		glPopMatrix();
		glPushMatrix(); //leg 2
			glTranslated(0.55,0.0,0.85);
			glColor3f(0.9f,0.2f,0.1f);
			drawTableLeg(0.02,0.2);
		glPopMatrix();
		glPushMatrix(); /////////////////eyes
			glPushMatrix(); //eye 1
				glColor3f(1.0f,1.0f,1.0f);
				glTranslated(0.46, 0.2, 0.9);
				glScaled(0.25, 0.25, 0.25);
				glutSolidSphere(0.1, 15, 15);
			glPopMatrix();
			glPushMatrix(); //eye 2
				glColor3f(1.0f,1.0f,1.0f);
				glTranslated(0.54, 0.2, 0.9);
				glScaled(0.25, 0.25, 0.25);
				glutSolidSphere(0.1, 15, 15);
			glPopMatrix();
			glPushMatrix(); //eye 1b
				glColor3f(0.9f,0.2f,0.1f);
				glTranslated(0.46, 0.2, 0.92);
				glScaled(0.25, 0.25, 0.25);
				glutSolidSphere(0.05, 15, 15);
			glPopMatrix();
			glPushMatrix(); //eye 2
				glColor3f(0.9f,0.2f,0.1f);
				glTranslated(0.54, 0.2, 0.92);
				glScaled(0.25, 0.25, 0.25);
				glutSolidSphere(0.05, 15, 15);
			glPopMatrix();
			glPushMatrix(); //eye 1c
				glColor3f(0.0f,0.0f,0.0f);
				glTranslated(0.46, 0.2, 0.93);
				glScaled(0.25, 0.25, 0.25);
				glutSolidSphere(0.02, 15, 15);
			glPopMatrix();
			glPushMatrix(); //eye 2c
				glColor3f(0.0f,0.0f,0.0f);
				glTranslated(0.54, 0.2, 0.93);
				glScaled(0.25, 0.25, 0.25);
				glutSolidSphere(0.02, 15, 15);
			glPopMatrix();
		glPopMatrix();
		glPushMatrix(); //mouth
				glColor3f(1.0f,0.9f,0.3f);
				glTranslated(0.5, 0.15, 0.93);
				glScaled(0.25, 0.25, 0.25);
				glutSolidSphere(0.09, 15, 15);
		glPopMatrix();
	glPopMatrix();




	///////////////////////////////////////living room 
	glPushMatrix(); //jack
		glColor3f(jackcolorx,jackcolory,jackcolorz);
		glTranslated(jackx,jacky,jackz);
		glTranslated(0.4, 0.38, 0.3);
		glRotated(45, 0, 0, 1);
		glScaled(0.08, 0.08, 0.08);
		drawJack();
	glPopMatrix();

	glPushMatrix(); //teapot
		glColor3f(teapotcolorx,teapotcolory,teapotcolorz);
		glTranslated(teapotx,teapoty,teapotz);
		glTranslated(0.6, 0.37, 0.5);
		glRotated(30, 0, 1, 0);
		glutSolidTeapot(0.08);
	glPopMatrix();


	glPushMatrix(); //table
		glColor3f(tablecolorx,tablecolory,tablecolorz);
		glTranslated(tablex,tabley,tablez);
		glTranslated(0.4, 0.0, 0.4);
		drawTable(0.6, 0.02, 0.02, 0.3);
	glPopMatrix();


	glPushMatrix(); //chair model 1
		glColor3f(chair1colorx,chair1colory,chair1colorz);
		glTranslated(chair1x,chair1y,chair1z);

		glPushMatrix(); //chair
			glTranslated(0.3, 0.0, 0.8);
			drawTable(0.1, 0.02, 0.01, 0.2);
		glPopMatrix();
		glPushMatrix(); //chair
			glTranslated(0.5, 0.0, 0.8);
			drawTable(0.1, 0.02, 0.01, 0.2);
		glPopMatrix();
	glPopMatrix();

	glPushMatrix(); //chair model 2
		glColor3f(chair2colorx,chair2colory,chair2colorz);
		glTranslated(chair2x,chair2y,chair2z);

		glPushMatrix(); //chair2
			glTranslated(0.8, 0.0, 0.3);
			drawTable(0.1, 0.02, 0.01, 0.2);
		glPopMatrix();
		glPushMatrix(); 
			glTranslated(0.8, 0.26 ,0.3);
			glRotated(90,1,0,0);
			// cylinder at (0,0,1)  
			GLUquadricObj * qobj;
			qobj = gluNewQuadric();  
			gluQuadricDrawStyle(qobj,GLU_FILL); 
			gluCylinder(qobj, 0.08, 0.08, 0.07, 10, 8);
			gluDisk(qobj,0,0.08,30,30);  
		glPopMatrix();
		glPushMatrix(); //chair2
			glTranslated(0.8, 0.0, 0.55);
			drawTable(0.1, 0.02, 0.01, 0.2);
		glPopMatrix();
		glPushMatrix(); 
			glTranslated(0.8, 0.26 ,0.55);
			glRotated(90,1,0,0);
			// cylinder at (0,0,1)  
			GLUquadricObj * qobj1;
			qobj1 = gluNewQuadric();  
			gluQuadricDrawStyle(qobj1,GLU_FILL);  
			gluCylinder(qobj1, 0.08, 0.08, 0.07, 10, 8);
			gluDisk(qobj1,0,0.08,30,30);  
		glPopMatrix();
	glPopMatrix();


	glPushMatrix(); //////////////////////////   	Cupboard
		glPushMatrix(); //Cupboard
			glColor3f(cupboardcolorx,cupboardcolory, cupboardcolorz);
			glTranslated(cupboardx,cupboardy,cupboardz);
			glScaled(0.7, 0.2, 0.2);
			glTranslated(0.7, 3.65, 0.5);
			glutSolidCube(0.9);
		glPopMatrix();

		glPushMatrix(); //Cubes	
			glColor3f(cubes1colorx,cubes1colory,cubes1colorz);
			glTranslated(cubes1x,cubes1y,cubes1z);
			glPushMatrix(); //cubes
				glScaled(0.08, 0.08, 0.08);
				glTranslated(4.0, 8.91, 2.1);
				glutSolidCube(1.5);
			glPopMatrix();
			glPushMatrix(); //cubes
				glScaled(0.08, 0.08, 0.08);
				glTranslated(6.1, 8.91, 2.1);
				glutSolidCube(1.5);
			glPopMatrix();
			glPushMatrix(); //cubes
				glScaled(0.08, 0.08, 0.08);
				glTranslated(8.1, 8.91, 2.1);
				glutSolidCube(1.5);
			glPopMatrix();
		glPopMatrix();
	glPopMatrix();

	///////////////////////////////////////// walls
	glPushMatrix();
		glTranslated(walll1x, walll1y,walll1z);
		glColor3f(walll1colorx,walll1colory,walll1colorz);
		drawWall(0.02);	
	glPopMatrix();
	glPushMatrix();
		glTranslated(walll2x, walll2y,walll2z);
		glColor3f(walll2colorx,walll2colory,walll2colorz);
		glRotated(90, 0, 0, 1.0);
		drawWall(0.02);
	glPopMatrix();
	glPushMatrix();
		glTranslated(walll3x, walll3y,walll3z);
		glColor3f(walll3colorx,walll3colory,walll3colorz);
		glRotated(-90, 1.0, 0.0, 0.0);
		drawWall(0.02);
	glPopMatrix();





	///////////////////////////////////////////////////////////////////////////          bedroom

	//walls
	glPushMatrix();
		glTranslated(wallb1x,wallb1y,wallb1z);
		glColor3f(wallb1colorx,wallb1colory,wallb1colorz);
		glTranslated(1.0, 0.0, 0.0);
		drawWall(0.02);	 
	glPopMatrix();
	glPushMatrix();
		glTranslated(wallb2x,wallb2y,wallb2z);
		glColor3f(wallb2colorx,wallb2colory,wallb2colorz);
		glTranslated(1.0, 0.0, 0.0);
		glRotated(90, 0, 0, 1.0);
		drawWall(0.02);
	glPopMatrix();
	glPushMatrix();
		glTranslated(wallb3x,wallb3y,wallb3z);
		glColor3f(wallb3colorx,wallb3colory,wallb3colorz);
		glTranslated(1.0, 0.0, 0.0);
		glRotated(-90, 1.0, 0.0, 0.0);
		drawWall(0.02);
	glPopMatrix();


	glPushMatrix(); //Bed
		glTranslated(bedx,bedy,bedz);
		glPushMatrix(); //bed
			glColor3f(bedcolorx,bedcolory,bedcolorz);
			glTranslated(1.3, 0.03, 0.4);
			drawTable(0.55, 0.2, 0.02, 0.01);
		glPopMatrix();
		glPushMatrix(); //bed accessoried
			glColor3f(bedcolorx,bedcolory,bedcolorz);
			glTranslated(1.05, 0.1, 0.65);
			drawTableLeg(0.05, 0.22);
		glPopMatrix();
		glPushMatrix(); //bed accessoried
			glColor3f(bedcolorx,bedcolory,bedcolorz);
			glTranslated(1.05, 0.1, 0.15);
			drawTableLeg(0.05, 0.22);
		glPopMatrix();

		glPushMatrix(); //bed sheet
			glTranslated(sheetx,sheety,sheetz);
			glColor3f(sheetcolorx,sheetcolory,sheetcolorz);
			glScaled(0.5, 0.055, 0.55);
			glTranslated(2.65, 2.9,0.73);
			glutSolidCube(0.93);
		glPopMatrix();

		glPushMatrix(); //bed cushion
			glColor3f(cushioncolorx,cushioncolory,cushioncolorz);
			//glTranslated(bedx,bedy,bedz);
			glTranslated(1.1, 0.25 ,0.2);
			// cylinder at (0,0,1)  
			GLUquadricObj * qobj2;
			qobj2 = gluNewQuadric();  
			gluQuadricDrawStyle(qobj2,GLU_FILL);  
			gluCylinder(qobj2, 0.06, 0.06, 0.4, 30, 30);  
		glPopMatrix();
	glPopMatrix(); 


	glPushMatrix(); //dolab
		glPushMatrix(); //dolab
			glColor3f(dolabcolorx,dolabcolory,dolabcolorz);
			glTranslated(dolabx,dolaby,dolabz);
			glTranslated(1.1, 0.03, 0.85);
			drawTableLeg(0.2, 0.6);
		glPopMatrix();
		glPushMatrix(); //cubes2
			glColor3f(cubes2colorx,cubes2colory,cubes2colorz);
			glTranslated(cubes2x,cubes2y,cubes2z);
			glPushMatrix(); //cubes
				glScaled(0.08, 0.08, 0.08);
				glTranslated(14.5, 7.0, 11.0);
				glutSolidCube(1.4);
			glPopMatrix();
			glPushMatrix(); //cubes
				glScaled(0.08, 0.08, 0.08);
				glTranslated(14.5, 4.5, 11.0);
				glutSolidCube(1.4);
			glPopMatrix();
			glPushMatrix(); //cubes
				glScaled(0.08, 0.08, 0.08);
				glTranslated(14.5, 2.0, 11.0);
				glutSolidCube(1.4);
			glPopMatrix();
		glPopMatrix();
	glPopMatrix();


	glPushMatrix(); //maktab
		glTranslated(maktabx,maktaby,maktabz);
		glPushMatrix(); //maktab
			glColor3f(maktabcolorx,maktabcolory,maktabcolorz);
			glTranslated(2.0, 0.2, 0.13);
			glRotated(90,0,0,1);
			drawTableLeg(0.16, 0.4);
		glPopMatrix();
		glPushMatrix(); //maktab accessoried
			glColor3f(maktabcolorx,maktabcolory,maktabcolorz);
			glTranslated(1.65, 0.0, 0.15);
			drawTableLeg(0.09, 0.19);
		glPopMatrix();
		glPushMatrix(); //maktab accessoried
			glColor3f(maktabcolorx,maktabcolory,maktabcolorz);
			glTranslated(1.95, 0.0, 0.11);
			drawTableLeg(0.09, 0.19);
		glPopMatrix();
		glPushMatrix(); //maktab accessoried
			glColor3f(maktabcolorx,maktabcolory,maktabcolorz);
			glTranslated(1.95, 0.0, 0.16);
			drawTableLeg(0.09, 0.19);
		glPopMatrix();
		glPushMatrix(); //paper
			glColor3f(1.0f,1.0f,1.0f);
			glTranslated(1.9, 0.19, 0.125);
			drawTableLeg(0.08, 0.1);
		glPopMatrix();
	glPopMatrix(); 


	glPushMatrix(); //chair model 3
		glTranslated(chair3x,chair3y,chair3z);
		glColor3f(chair3colorx,chair3colory,chair3colorz);
		glPushMatrix(); //chair nafso
			glTranslated(1.85, 0.0, 0.35);
			drawTable(0.1, 0.02, 0.01, 0.19);
		glPopMatrix();
		glPushMatrix(); //chair fo2
			glTranslated(1.8, 0.19, 0.4);
			drawTableLeg(0.01, 0.18);
		glPopMatrix();
		glPushMatrix(); //chair fo2
			glTranslated(1.9, 0.19, 0.4);
			drawTableLeg(0.01, 0.18);
		glPopMatrix();
		glPushMatrix(); //chair fo2
			glTranslated(1.87, 0.31, 0.35);
			glRotated(90,0,0,1);
			drawTableLeg(0.01, 0.11);
		glPopMatrix();
		glPushMatrix(); //chair fo2
			glTranslated(1.87, 0.24, 0.35);
			glRotated(90,0,0,1);
			drawTableLeg(0.01, 0.11);
		glPopMatrix();
	glPopMatrix();	


	glPushMatrix(); //clock
		glTranslated(clockx, clocky, clockz);
		glColor3f(clockcolorx,clockcolory,clockcolorz);
		glPushMatrix();
			glTranslated(1.5, 0.6, 0.02);
			glScaled(0.25, 0.25, 0.25);
			glutSolidSphere(0.1, 15, 15);
		glPopMatrix();
		glPushMatrix();
			glTranslated(1.5, 0.9, 0.02);
			glScaled(0.25, 0.25, 0.25);
			glutSolidSphere(0.1, 15, 15);
		glPopMatrix();
		glPushMatrix();
			glTranslated(1.65, 0.75, 0.02);
			glScaled(0.25, 0.25, 0.25);
			glutSolidSphere(0.1, 15, 15);
		glPopMatrix();
		glPushMatrix();
			glTranslated(1.35, 0.75, 0.02);
			glScaled(0.25, 0.25, 0.25);
			glutSolidSphere(0.1, 15, 15);
		glPopMatrix();
		glPushMatrix(); //chair fo2
			glTranslated(1.5, 0.74, 0.02);
			glRotated(90,0,1,1);
			drawTableLeg(0.01, 0.11);
		glPopMatrix();
	glPopMatrix();

	glFlush();
}


void Keyboard(unsigned char key, int x, int y) {
	float d = 0.01;

	switch (key) {
	case 'a': //start animation dinning
		animate1 = true;
		break;
	case 'b': //start animation bedroom
		animate2 = true;
		break;
	case 'c':
		colorrand = true;	
		break;
	case 'g': //ghost appear and so stuff
		ghost = true;
		appearx = 0.0;
		appeary = 0.0;
		appearz = 0.0;	
		break;
	case 'h':  //ghost disappear
		ghost = false;
		appearx = 2.0;
		appeary = 2.0;
		appearz = 2.0;	
		break;
	case 't': //top view
		eyeXNew = 0.6f;
		eyeYNew = 4.0f;
		eyeZNew = 0.5f;
		centerXnew = 0.8f; 
		centerYnew = -2.8f;
		centerZnew = 0.6f;
		break;
	case 'r': //right view
		eyeXNew = 4.0f;
		eyeYNew = 0.3f;
		eyeZNew = 1.0f;
		centerZnew = 0.4f; 
		break;
	case 'l': //left view
		eyeXNew = 0.2f;
		eyeYNew = 0.3f;
		eyeZNew = 2.0f;
		centerXnew = 0.6f; 
		centerYnew = 0.2f;
		break;
	case 'd': //default view and stop animation and go back to normal colors
		eyeXNew = 3.0f;
		eyeYNew = 1.0f;
		eyeZNew = 2.0f;
		centerXnew = 0.0f; 
		centerYnew = 0.0f;
		centerZnew = 0.0f;
		animate1 = false;
		animate2 = false;
		colorrand = false;
		break;

	case GLUT_KEY_ESCAPE:
		exit(EXIT_SUCCESS);
	}

	glutPostRedisplay();
}
void Special(int key, int x, int y) {
	float a = 1.0;

	switch (key) {
	case GLUT_KEY_UP:
	//	camera.rotateX(a);
		break;
	case GLUT_KEY_DOWN:
	//	camera.rotateX(-a);
		break;
	case GLUT_KEY_LEFT:
	//	camera.rotateY(a);
		break;
	case GLUT_KEY_RIGHT:
	//	camera.rotateY(-a);
		break;
	}

	glutPostRedisplay();
}


void anim (){

	if(animate1){      //animate room 1
		if(jackx<1.0)
			jackx+=0.0002;
		else 
			jackx=0.0;
		if(teapotx<1.0)
			teapotx+=0.0003;
		else
			teapotx=0.0;
		if(tablex<1.0)
			tablex+=0.0004;
		else
			tablex=0.0;
		if(chair1y<0.7)
			chair1y+=0.0001;
		else
			chair1y=0.0;
		if(chair2z<0.7)
			chair2z+=0.0004;
		else
			chair2z=0.0;
		if(cupboardy<0.2)
			cupboardy+=0.0004;
		else
			cupboardy=0.0;
		if(cubes1z<1.0)
			cubes1z+=0.0003;
		else
			cubes1z=0.0;
		if(walll1x<1.0)
			walll1x+=0.0002;
		else
			walll1x=0.0;
		if(walll2x<1.0)
			walll2x+=0.0003;
		else
			walll2x=0.0;
		if(walll3z<1.0)
			walll3z+=0.0004;
		else
			walll3z=0.0;

	}
	else if(!animate1){
		jackx=0.0;
		teapotx=0.0;
		tablex=0.0;
		chair1y=0.0;
		chair2z=0.0;
		cupboardy=0.0;
		cubes1z=0.0;
		walll1x=0.0;
		walll2x=0.0;
		walll3z=0.0;
	}
	
	if(animate2){
		if(cubes2x<1.0)
			cubes2x+=0.0003;
		else
			cubes2x=0.0;
		if(wallb1x<1.0)
			wallb1x+=0.0002;
		else
			wallb1x=0.0;
		if(wallb2z<1.0)
			wallb2z+=0.0002;
		else
			wallb2z=0.0;
		if(wallb3x<1.0)
			wallb3x+=0.0001;
		else
			wallb3x=0.0;
		if(bedx<1.0)
			bedx+=0.0003;
		else
			bedx=0.0;
		if(dolaby<0.6)
			dolaby+=0.0001;
		else
			dolaby=0.0;
		if(maktabz<1.0)
			maktabz+=0.0003;
		else
			maktabz=0.0;
		if(clockz<1.0)
			clockz+=0.0001;
		else
			clockz=0.0;
		if(chair3y<0.6)
			chair3y+=0.001;
		else
			chair3y=0.0;
	}
	else if(!animate2){
			cubes2x=0.0;
			wallb1x=0.0;
			wallb2z=0.0;
			wallb3x=0.0;
			bedx=0.0;
			dolaby=0.0;
			maktabz=0.0;
			clockz=0.0;
			chair3y=0.0;
	}

	if(colorrand){
		//clock
		clockcolorx+=0.01*redD1;						
		if(clockcolorx<0||clockcolorx>1)					
			redD1*=-1;								
													
		clockcolory+=0.01*greenD1;						
		if(clockcolory<0||clockcolory>0.5)						
			greenD1*=-1;								
															
		clockcolorz+=0.01*blueD1;						
		if(clockcolorz<0.5||clockcolorz>0.75)				
			blueD1*=-1;
	
		//sheet
		sheetcolorx+=0.001*redD2;						
		if(sheetcolorx<0||sheetcolorx>1)					
			redD2*=-1;								
													
		sheetcolory+=0.001*greenD2;						
		if(sheetcolory<0||sheetcolory>0.5)						
			greenD2*=-1;								
															
		sheetcolorz+=0.001*blueD2;						
		if(sheetcolorz<0.5||sheetcolorz>0.75)				
			blueD2*=-1;

		//bed
		bedcolorx+=0.001*redD3;						
		if(bedcolorx<0||bedcolorx>1)					
			redD3*=-1;								
													
		bedcolory+=0.001*greenD3;						
		if(bedcolory<0||bedcolory>0.5)						
			greenD3*=-1;								
															
		bedcolorz+=0.001*blueD3;						
		if(bedcolorz<0.5||bedcolorz>0.75)				
			blueD3*=-1;


		//cubes2
		cubes2colorx+=0.001*redD4;						
		if(cubes2colorx<0||cubes2colorx>1)					
			redD4*=-1;								
													
		cubes2colory+=0.001*greenD4;						
		if(cubes2colory<0||cubes2colory>0.5)						
			greenD4*=-1;								
															
		cubes2colorz+=0.001*blueD4;						
		if(cubes2colorz<0.5||cubes2colorz>0.75)				
			blueD4*=-1;

		//maktab
		maktabcolorx+=0.001*redD5;						
		if(maktabcolorx<0||maktabcolorx>1)					
			redD5*=-1;								
													
		maktabcolory+=0.001*greenD5;						
		if(maktabcolory<0||maktabcolory>0.5)						
			greenD5*=-1;								
															
		maktabcolorz+=0.001*blueD5;						
		if(maktabcolorz<0.5||maktabcolorz>0.75)				
			blueD5*=-1;

		//chair3
		chair3colorx+=0.001*redD6;						
		if(chair3colorx<0||chair3colorx>1)					
			redD6*=-1;								
													
		chair3colory+=0.001*greenD6;						
		if(chair3colory<0||chair3colory>0.5)						
			greenD6*=-1;								
															
		chair3colorz+=0.001*blueD6;						
		if(chair3colorz<0.5||chair3colorz>0.75)				
			blueD6*=-1;

		//dolab
		dolabcolorx+=0.001*redD7;						
		if(dolabcolorx<0||dolabcolorx>1)					
			redD7*=-1;								
													
		dolabcolory+=0.001*greenD7;						
		if(dolabcolory<0||dolabcolory>0.5)						
			greenD7*=-1;								
															
		dolabcolorz+=0.001*blueD7;						
		if(dolabcolorz<0.5||dolabcolorz>0.75)				
			blueD7*=-1;

		
		//wallb1
		wallb1colorx+=0.001*redD8;						
		if(wallb1colorx<0||wallb1colorx>1)					
			redD8*=-1;								
													
		wallb1colory+=0.001*greenD8;						
		if(wallb1colory<0||wallb1colory>0.5)						
			greenD8*=-1;								
															
		wallb1colorz+=0.001*blueD8;						
		if(wallb1colorz<0.5||wallb1colorz>0.75)				
			blueD8*=-1;

		//wallb2
		wallb2colorx+=0.001*redD9;						
		if(wallb2colorx<0||wallb2colorx>1)					
			redD9*=-1;								
													
		wallb2colory+=0.001*greenD9;						
		if(wallb2colory<0||wallb2colory>0.5)						
			greenD9*=-1;								
															
		wallb2colorz+=0.001*blueD9;						
		if(wallb2colorz<0.5||wallb2colorz>0.75)				
			blueD9*=-1;

		//wallb3
		wallb3colorx+=0.001*redD10;						
		if(wallb3colorx<0||wallb3colorx>1)					
			redD10*=-1;								
													
		wallb3colory+=0.001*greenD10;						
		if(wallb3colory<0||wallb3colory>0.5)						
			greenD10*=-1;								
															
		wallb3colorz+=0.001*blueD10;						
		if(wallb3colorz<0.5||wallb3colorz>0.75)				
			blueD10*=-1;

		//dining room
		//cupboard
		cupboardcolorx+=0.001*redD11;						
		if(cupboardcolorx<0||cupboardcolorx>1)					
			redD11*=-1;								
													
		cupboardcolory+=0.001*greenD11;						
		if(cupboardcolory<0||cupboardcolory>0.5)						
			greenD11*=-1;								
															
		cupboardcolorz+=0.001*blueD11;						
		if(cupboardcolorz<0.5||cupboardcolorz>0.75)				
			blueD11*=-1;

		//walll1
		walll1colorx+=0.001*redD12;						
		if(walll1colorx<0||walll1colorx>1)					
			redD12*=-1;								
													
		walll1colory+=0.001*greenD12;						
		if(walll1colory<0||walll1colory>0.5)						
			greenD12*=-1;								
															
		walll1colorz+=0.001*blueD12;						
		if(walll1colorz<0.5||walll1colorz>0.75)				
			blueD12*=-1;

		//wallb2
		walll2colorx+=0.001*redD13;						
		if(walll2colorx<0||walll2colorx>1)					
			redD13*=-1;								
													
		walll2colory+=0.001*greenD13;						
		if(walll2colory<0||walll2colory>0.5)						
			greenD13*=-1;								
															
		walll2colorz+=0.001*blueD13;						
		if(walll2colorz<0.5||walll2colorz>0.75)				
			blueD13*=-1;

		//wallb3
		walll3colorx+=0.001*redD14;						
		if(walll3colorx<0||walll3colorx>1)					
			redD14*=-1;								
													
		walll3colory+=0.001*greenD14;						
		if(walll3colory<0||walll3colory>0.5)						
			greenD14*=-1;								
															
		walll3colorz+=0.001*blueD14;						
		if(walll3colorz<0.5||walll3colorz>0.75)				
			blueD14*=-1;

		//table
		tablecolorx+=0.001*redD15;						
		if(tablecolorx<0||tablecolorx>1)					
			redD15*=-1;								
													
		tablecolory+=0.001*greenD15;						
		if(tablecolory<0||tablecolory>0.5)						
			greenD15*=-1;								
															
		tablecolorz+=0.001*blueD15;						
		if(tablecolorz<0.5||tablecolorz>0.75)				
			blueD15*=-1;

		//chair2
		chair2colorx+=0.001*redD16;						
		if(chair2colorx<0||chair2colorx>1)					
			redD16*=-1;								
													
		chair2colory+=0.001*greenD16;						
		if(chair2colory<0||chair2colory>0.5)						
			greenD16*=-1;								
															
		chair2colorz+=0.001*blueD16;						
		if(chair2colorz<0.5||chair2colorz>0.75)				
			blueD16*=-1;

		//chair1
		chair1colorx+=0.001*redD17;						
		if(chair1colorx<0||chair1colorx>1)					
			redD17*=-1;								
													
		chair1colory+=0.001*greenD17;						
		if(chair1colory<0||chair1colory>0.5)						
			greenD17*=-1;								
															
		chair1colorz+=0.001*blueD17;						
		if(chair1colorz<0.5||chair1colorz>0.75)				
			blueD17*=-1;

		//teapot
		teapotcolorx+=0.001*redD18;						
		if(teapotcolorx<0||teapotcolorx>1)					
			redD18*=-1;								
													
		teapotcolory+=0.001*greenD18;						
		if(teapotcolory<0||teapotcolory>0.5)						
			greenD18*=-1;								
															
		teapotcolorz+=0.001*blueD18;						
		if(teapotcolorz<0.5||teapotcolorz>0.75)				
			blueD18*=-1;

		//jack
		jackcolorx+=0.001*redD19;						
		if(jackcolorx<0||jackcolorx>1)					
			redD19*=-1;								
													
		jackcolory+=0.001*greenD19;						
		if(jackcolory<0||jackcolory>0.5)						
			greenD19*=-1;								
															
		jackcolorz+=0.001*blueD19;						
		if(jackcolorz<0.5||jackcolorz>0.75)				
			blueD19*=-1;

		//cubes1
		cubes1colorx+=0.001*redD20;						
		if(cubes1colorx<0||cubes1colorx>1)					
			redD20*=-1;								
													
		cubes1colory+=0.001*greenD20;						
		if(cubes1colory<0||cubes1colory>0.5)						
			greenD20*=-1;								
															
		cubes1colorz+=0.001*blueD20;						
		if(cubes1colorz<0.5||cubes1colorz>0.75)				
			blueD20*=-1;



	}
	else if(!colorrand){
		clockcolorx = 0.5f; 
		clockcolory = 0.8f;
		clockcolorz = 1.0f;
		sheetcolorx=1.0f;
		sheetcolory=0.0f;
		sheetcolorz=0.6f;
		wallb1colorx = 0.7f;
		wallb1colory = 0.3f;
		wallb1colorz = 0.5f;
		wallb2colorx = 0.9f;
		wallb2colory = 0.4f;
		wallb2colorz = 0.8f;
		wallb3colorx = 0.9f;
		wallb3colory = 0.6f;
		wallb3colorz = 1.0f;
		bedcolorx=0.9f;
		bedcolory=0.8f;
		bedcolorz=0.5f;
		cubes2colorx = 0.8f;
		cubes2colory = 0.4f;
		cubes2colorz = 1.0f;
		dolabcolorx = 0.4f;
		dolabcolory = 0.0f;
		dolabcolorz = 0.2f;
		maktabcolorx=0.5f;
		maktabcolory=0.3f;
		maktabcolorz=0.0f;
		chair3colorx = 0.5f;
		chair3colory = 0.2f;
		chair3colorz = 0.5f;
		walll3colorx = 0.3f;
		walll3colory = 0.7f;
		walll3colorz = 0.6f;
		walll2colorx = 0.4f;
		walll2colory = 0.9f;
		walll2colorz = 0.8f;
		walll1colorx = 0.3f;
		walll1colory = 0.4f;
		walll1colorz = 0.4f;
		cupboardcolorx = 0.7f;
		cupboardcolory = 0.7f;
		cupboardcolorz = 0.7f;
		cubes1colorx = 0.3f;
		cubes1colory = 0.8f;
		cubes1colorz = 0.0f;
		chair2colorx = 0.4f;
		chair2colory = 0.2f;
		chair2colorz = 0.5f;
		chair1colorx = 0.4f;
		chair1colory = 0.3f;
		chair1colorz = 0.1f;
		tablecolorx = 0.4f;
		tablecolory = 0.2f;
		tablecolorz = 0.0f;
		teapotcolorx = 0.7f;
		teapotcolory = 0.8f;
		teapotcolorz = 0.6f;
		jackcolorx = 0.0f;
		jackcolory = 0.2f;
		jackcolorz = 0.4f;


	}

	if(ghost){
		if(ghostx<1.2)
			ghostx+=0.001;
		else
			ghostx=-0.5;
		ghostz=-0.4;
		
		if(scale1x<1.5)
			scale1x+=0.001;
		else
			scale1x=1.0;
		if(scale1y<2.5)
			scale1y+=0.002;
		else
			scale1y=1.0;
		if(scale1z<1.5)
			scale1z+=0.002;
		else
			scale1z=1.0;
	}
	else if(!ghost){
		ghostx=0.0;
		scale1x=1.0;
		scale1y=1.0;
		scale1z=1.0;	
	}
	


	glutPostRedisplay();
}


int main(int argc, char** argv) {
	glutInit(&argc, argv);

	glutInitWindowSize(640, 480);
	glutInitWindowPosition(50, 50);

	glutCreateWindow("Assignment 2");
	glutDisplayFunc(Display);
	glutIdleFunc(anim);
	glutKeyboardFunc(Keyboard);
	glutSpecialFunc(Special);

	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

	glEnable(GL_DEPTH_TEST);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glEnable(GL_NORMALIZE);
	glEnable(GL_COLOR_MATERIAL);

	glShadeModel(GL_SMOOTH);

	glutMainLoop();
	return 0;
}
