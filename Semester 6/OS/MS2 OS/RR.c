#include<stdio.h>
#include<string.h>
#include<time.h>
struct Processes{
    int pid;
    int arrivalTime;
    int cpuBurstTime;
    int remainingCpuBurstTime;
    int startTime;
    int endTime;
    int numberOfTickets;
    int started;
    int waitingTime;
    int turnaroundTime;
};

int main(){
    int quantumLength, totalTickets;

    static const struct Processes EmptyStruct;


    FILE *f= fopen("/Users/khaledhammoud/Desktop/output.txt","w");
    if(f==NULL){
        printf("Error\n");
        exit(1);
    }
    FILE * file;
    file = fopen("/Users/khaledhammoud/Downloads/OS_MP2/OS_MP2/Input-1.txt","r");

    char *line = (char*) malloc(1024);

    char tmp1[20][20];

    int numberOfLines = 0;

    while(fgets(line,1024,file)){
        strcpy(tmp1[numberOfLines],line);
        numberOfLines++;
    }

    struct Processes readyProcesses[numberOfLines-2];
    struct Processes doneProcesses[numberOfLines-2];

    quantumLength = atoi(tmp1[0]);
    totalTickets = atoi(tmp1[1]);

    char * tok;
    char tmp2[20][20];

    int j;
    int n = 0;
    struct Processes p;

    for(int k = 2 ; k < numberOfLines ; k++){
            tok = strtok (tmp1[k],",");
            j = 0;
        while (tok != NULL)
        {
            strcpy(tmp2[j],tok);
            tok = strtok (NULL, ",");
            j++;
        }

    readyProcesses[k-2].pid = atoi(tmp2[0]);
    readyProcesses[k-2].arrivalTime = atoi(tmp2[1]);
    readyProcesses[k-2].cpuBurstTime = atoi(tmp2[2]);
    readyProcesses[k-2].remainingCpuBurstTime = atoi(tmp2[2]);
    readyProcesses[k-2].startTime = 0;
    readyProcesses[k-2].endTime = 0;
    readyProcesses[k-2].numberOfTickets = atoi(tmp2[3]);
    readyProcesses[k-2].started = 0;
    readyProcesses[k-2].waitingTime = 0;
    readyProcesses[k-2].turnaroundTime = 0;
   }

    int numberOfProcesses = numberOfLines - 2;

    int flag = 0;
    int oldNumberOfProcesses = numberOfProcesses;
    int clk = 0;

    while(numberOfProcesses > 0){

        int msec = 0;
        clock_t before = clock();
        int count = 0;

        if(readyProcesses[0].started == 0){
            readyProcesses[0].started = 1;
            readyProcesses[0].startTime = clk;
        }

        if(quantumLength < readyProcesses[0].remainingCpuBurstTime){

            do {
              clock_t difference = clock() - before;
              msec = difference*1000;

            } while (msec < quantumLength);

            clk = clk + quantumLength;

                int newCpuBurstTime = readyProcesses[0].remainingCpuBurstTime - quantumLength;
                readyProcesses[0].remainingCpuBurstTime = newCpuBurstTime;

                struct Processes tmp = readyProcesses[0];

                int b;
                for(b = 0 ; b < numberOfProcesses ; b++){
                    readyProcesses[b] = readyProcesses[b+1];
                }
                readyProcesses[b-1] = tmp;
            }
            else{
                 do {
                  clock_t difference = clock() - before;
                  msec = difference*1000;

                } while (msec < readyProcesses[0].remainingCpuBurstTime);

                clk = clk + readyProcesses[0].remainingCpuBurstTime;

                readyProcesses[0].endTime = clk;
                readyProcesses[0].turnaroundTime = readyProcesses[0].endTime - readyProcesses[0].arrivalTime;
                readyProcesses[0].waitingTime = readyProcesses[0].turnaroundTime - readyProcesses[0].cpuBurstTime;

                doneProcesses[oldNumberOfProcesses - numberOfProcesses] = readyProcesses[0];

                for(int b = 0 ; b < numberOfProcesses ; b++){
                    readyProcesses[b] = readyProcesses[b+1];
                }
                numberOfProcesses--;
            }
//            printf("%d",clk);
//            printf(" ");
   }


   for(int i = 0 ; i < oldNumberOfProcesses ; i++){
            printf(" P");
            printf("%d", doneProcesses[i].pid);
            fprintf(f," P%d",doneProcesses[i].pid);

            printf(" Waiting Time: ");
            fprintf(f," Waiting Time:%d",doneProcesses[i].waitingTime);

           printf("%d", doneProcesses[i].waitingTime);
            printf(" Turnaround Time: ");
            printf("%d", doneProcesses[i].turnaroundTime);
            fprintf(f," Turnaround Time:%d\n",doneProcesses[i].turnaroundTime);

   }

   int sumWaitingTime = 0;
   int sumTurnAroundTime = 0;

   for(int u = 0 ; u < oldNumberOfProcesses ; u++){
        sumWaitingTime= sumWaitingTime + doneProcesses[u].waitingTime;
        sumTurnAroundTime = sumTurnAroundTime + doneProcesses[u].turnaroundTime;
   }

   int averageWaitingTime = sumWaitingTime/oldNumberOfProcesses;
   printf("Average Waiting Time: ");
   printf("%d", averageWaitingTime);
    fprintf(f,"Average Waiting Time:%d\n",averageWaitingTime);


   int averageTurnAroundTime = sumTurnAroundTime/oldNumberOfProcesses;
   printf("Average Turnaround Time: ");
   printf("%d", averageTurnAroundTime);
    fprintf(f,"Average TurnAround Time:%d\n",averageTurnAroundTime);
fclose(f);
}
