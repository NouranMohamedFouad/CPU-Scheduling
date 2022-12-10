import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        Scanner string=new Scanner(System.in);
        //System.out.println("Please enter number of processes :");
        int processNum=4;
        //System.out.println("Please enter Round Robin Time Quantum :");
        int qTime=2;
        //System.out.println("Please enter Context Switching :");
        int contextSwitching=2;

        Process p[]= new Process[processNum];
        int RRprocess[]=new int[processNum];

        for(int i=0;i<processNum;i++)
        {
            p[i]=new Process();
        }
        for(int i=0;i<processNum;i++)
        {
            System.out.println("Please enterName of process["+(i+1)+"]:");
            String name=string.nextLine();
            System.out.println("Please enter Arrival time of process["+(i+1)+"]:");
            int arrivalT=s.nextInt();
            System.out.println("Please enter Burst time of process["+(i+1)+"]:");
            int burstT=s.nextInt();
            p[i].setName(name);
            p[i].setArrivalTime(arrivalT);
            p[i].setburstTime(burstT);

        }
        ///////////////////////////////////
        //SJF sjf=new SJF(processNum,contextSwitching);
        //sjf.display(p);
        ///////////////////////////////////
        RoundRobin rr=new RoundRobin();
        rr.schedulerforRR(processNum,4);
    }
}