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
    int startingTicket;
    int endingTicket;
    int started;
    int waitingTime;
    int turnaroundTime;
};

int main(){

    int quantumLength, totalTickets;
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

    if((k-2) == 0){
        readyProcesses[k-2].startingTicket = 1;
        readyProcesses[k-2].endingTicket = readyProcesses[k-2].numberOfTickets;
    }
    else{
        readyProcesses[k-2].startingTicket = readyProcesses[k-3].endingTicket + 1;
        readyProcesses[k-2].endingTicket = readyProcesses[k-2].startingTicket + readyProcesses[k-2].numberOfTickets - 1;
    }

   }

    int numberOfProcesses = numberOfLines - 2;

    int oldNumberOfProcesses = numberOfProcesses;
    int clk = 0;

     srand(time(NULL));
     int ticket;


    while(numberOfProcesses > 0){

        ticket = rand() % totalTickets;

        for(int i = 0 ; i < numberOfProcesses ; i++){

            if(ticket >= readyProcesses[i].startingTicket && ticket <= readyProcesses[i].endingTicket){

                    printf(" Entering Quantum Time: ");
        printf("%d", clk);
            fprintf(f,"Entering Quantum Time:%d\n",clk);

                int msec = 0;
                clock_t before = clock();
                int count = 0;

            if(readyProcesses[i].started == 0){
                readyProcesses[i].started = 1;
                readyProcesses[i].startTime = clk;
            }

            if(quantumLength < readyProcesses[i].remainingCpuBurstTime){

                do {
                  clock_t difference = clock() - before;
                  msec = difference*1000;

                } while (msec < quantumLength);

                clk = clk + quantumLength;

                int newCpuBurstTime = readyProcesses[i].remainingCpuBurstTime - quantumLength;
                readyProcesses[i].remainingCpuBurstTime = newCpuBurstTime;

            }
            else{
                 do {
                  clock_t difference = clock() - before;
                  msec = difference*1000;

                } while (msec < readyProcesses[i].remainingCpuBurstTime);

                clk = clk + readyProcesses[i].remainingCpuBurstTime;

                readyProcesses[i].endTime = clk;
                readyProcesses[i].turnaroundTime = readyProcesses[i].endTime - readyProcesses[i].arrivalTime;
                readyProcesses[i].waitingTime = readyProcesses[i].turnaroundTime - readyProcesses[i].cpuBurstTime;

                doneProcesses[oldNumberOfProcesses - numberOfProcesses] = readyProcesses[i];

                for(int b = i ; b < numberOfProcesses ; b++){
                    readyProcesses[b] = readyProcesses[b+1];
                }
                numberOfProcesses--;
            }
   }


    }

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
