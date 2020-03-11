#include<stdio.h>

void main() {

  printf("Enter directory name: ");

  char directoryName[20];
  scanf("%[^\n]%*c", directoryName);

  char temp [50];
  strcpy(temp,directoryName);
  int dir = mkdir(directoryName,0700);

  if(-1 == dir){
    int i = 1;
    int flag = 4;

    while( 4 == flag){

    char buffer[50];
    sprintf( buffer, "%d", i );

    strcpy(temp,directoryName);
    strcat(temp,buffer);

    int dir1 = mkdir(temp,0700);

    if(0 == dir1){
        flag = 5;
    }
    i++;
    }
  }
 else{
    printf("successful");
 }
}
