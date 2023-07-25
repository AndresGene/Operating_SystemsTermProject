/* CSCI 330M04 Operating Systems
 *
 * Created by Edgar Mendez and Andres Gene 
 * 
 * CPU Scheduling Programming Project
*/

package OS;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.Scanner;
import java.util.Scanner.*;

public class RoundRobin_definition {
int burstTime[];
int waitingTime[];
int turnaroundTime[];
int array[];
// Method to perform round robin scheduling
void rrScheduling(int numberOfProcess, Scanner sc){
int quantum, sum = 0;
float averageWaitingtime = 0;
float averageTurnaroundtime = 0;
Random rand = new Random();
for(int counter = 0; counter < numberOfProcess; counter++)
burstTime[counter] = rand.nextInt(35);

System.out.println("\n Number of Time quantums: ");
quantum = sc.nextInt();

for(int counter = 0; counter < numberOfProcess; counter++)
array[counter] = burstTime[counter];

for(int counter = 0; counter < numberOfProcess; counter++)
waitingTime[counter] = 0;

do {
for(int counter = 0; counter < numberOfProcess; counter++) {
if (burstTime[counter] > quantum) {
burstTime[counter] -= quantum;
for(int index = 0; index < numberOfProcess; index++) {
if ((index != counter) && (burstTime[index] != 0))
waitingTime[index] += quantum;
} 
}
else {
 for (int index = 0; index < numberOfProcess; index++) {
if ((index != counter) && (burstTime[index] != 0))
waitingTime[counter] += burstTime[counter]; 
 }
burstTime[counter] = 0;
 }
}
sum = 0;

for(int c = 0; c < numberOfProcess; c++)
// Adds the previous sum with each process burst time
sum += burstTime[c];
}while(sum != 0); 
for(int counter = 0; counter < numberOfProcess; counter++)
// Calculates the turn around time by adding waiting time of each process
// by burst time of each process
turnaroundTime[counter] = waitingTime[counter] + array[counter];

System.out.println("\nprocess\t\tBurstTime\tWaitingTime\tTurnAroundTime");
for(int counter = 0; counter < numberOfProcess; counter++)
System.out.println("process" + (counter + 1) + "\t" + array[counter] +
"\t\t" + waitingTime[counter] + "\t\t" + turnaroundTime[counter]);
for(int counter = 0; counter < numberOfProcess; counter++)
// total waiting time for each process
averageWaitingtime += waitingTime[counter];
for(int counter = 0; counter < numberOfProcess; counter++)
// total turn around time for each process
averageTurnaroundtime += turnaroundTime[counter];
int requiredTime[] = new int[numberOfProcess];
// total time required
int totalRequiredTime = 0;
// total time quantum
int totalResponseTime = -quantum;
for(int counter = 0; counter < numberOfProcess; counter++){
 requiredTime[counter] = turnaroundTime[counter] - waitingTime[counter];
 // total required time
 totalRequiredTime += requiredTime[counter];
 // total response time
 totalResponseTime += quantum;
 }

System.out.println("\n Average Waiting Time = " +
(averageWaitingtime / numberOfProcess));
System.out.println("\n Average Turn Around Time = " +
(averageTurnaroundtime / numberOfProcess));
System.out.println("\n Average Response Time = " +
(totalResponseTime / numberOfProcess));
System.out.println("\n Average Throughoutput = " +
(totalRequiredTime / numberOfProcess));
}


public static void main(String s[]){
RoundRobin_definition rr = new RoundRobin_definition();
Scanner sc = new Scanner(System.in);

System.out.println("\n************************************************");
System.out.println("\n******** Round Robin CPU Scheduling ********");
System.out.println("\n************************************************");
System.out.println("Enter number of process: ");

int numberOfProcess = sc.nextInt();
rr.burstTime = new int[numberOfProcess];
rr.waitingTime = new int[numberOfProcess];
rr.turnaroundTime = new int[numberOfProcess];
rr.array = new int[numberOfProcess];

rr.rrScheduling(numberOfProcess, sc);
rr.rrScheduling(numberOfProcess, sc);
rr.rrScheduling(numberOfProcess, sc);
}
}
