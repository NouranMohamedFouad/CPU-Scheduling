public class SJF
{
    private int complete = 0;
    private int startT = 0;
    private int min= Integer.MAX_VALUE;
    private int shortest = 0;
    private int finish_time;
    private boolean check = false;
    private int n;
    private int wt[]=new int[n];
    private int contextS;
    private int minExecTime=Integer.MAX_VALUE;
    public SJF(int size,int conSwitching)
    {
        n=size;
        contextS=conSwitching;
    }
    /////////////////////////////////////////
     void calcTime(Process process[],int wt[],String exectutionPname[]) {
         int remainingBT[] = new int[n];
         int execOrder[] = new int[n];
         //Copy the burst time into rt[]
         for (int i = 0; i < n; i++) {
             remainingBT[i] = process[i].getburstTime();
         }
         // Process until all processes get completed
         while (complete != n) {
             // Find process with minimum
             // remaining time among the
             // processes that arrives till the
             // current time`
             for (int i = 0; i < n; i++) {
                 if ((process[i].getArrivalTime() <= startT) && (remainingBT[i] < min) && remainingBT[i] > 0) {
                     min = remainingBT[i];
                     shortest = i;
                     check = true;
                 }
             }
             if (check == false) {
                 startT++;
                 continue;
             }
             // Reduce remaining time by one of the process
             remainingBT[shortest]--;
             // Update minimum
             min = remainingBT[shortest];
             if (min == 0) {
                 min = Integer.MAX_VALUE;
             }
             // If a process gets completely executed
             if (remainingBT[shortest] == 0) {
                 // Increment complete
                 complete++;
                 check = false;
                 // Find finish time of current process
                 finish_time = startT + 1;
                 // Calculate waiting time
                 wt[shortest] = finish_time - process[shortest].getburstTime() - process[shortest].getArrivalTime() + contextS;
                 //execOrder[shortest]=process[shortest].getName();
                 if (wt[shortest] < 0) {
                     wt[shortest] = 0;
                 }

             }
             // Increment time
             startT++;
         }
         for (int i = 0; i < n; i++) {
             process[i].setTurnAroundTime(process[i].getburstTime() + wt[i]);
         }
         for (int i = 0; i < n; i++) {
             execOrder[i] = wt[i];
         }
         for (int i = 0; i < n; i++) {
             int pos = i;
             for (int j = i + 1; j < n; j++) {
                 if (execOrder[j] < execOrder[pos]) {
                     pos = j;
                 }
             }
             int temp = execOrder[i];
             execOrder[i] = execOrder[pos];
             execOrder[pos] = temp;

         }
         for (int i= 0; i < n; i++)
         {
             int x=execOrder[i];
             for (int j = 0; j < n; j++)
             {
                 if (wt[j]==x)
                 {
                      exectutionPname[i]=process[j].getName();
                 }
             }
         }
     }
    ////////////////////////////////////
    void display(Process process[])
    {
        int wt[] = new int[n];
        String exeOrder[]=new String[n];
        double  total_wt=0;
        double total_tat=0;

        // Function to find waiting time of all
        // processes
        calcTime(process,wt,exeOrder);
        System.out.println("Processes "+" Burst time "+" Waiting time "+" Turn around time"+"  Execution order");
        // Calculate total waiting time and total turnaround time for all processes
        for (int i = 0; i < n; i++)
        {
            total_wt+=wt[i];
            total_tat+=process[i].getTurnAroundTime();
            System.out.println(" "+process[i].getName()+"\t\t\t"+process[i].getburstTime()+"\t\t\t"+wt[i]+"\t\t\t\t"+process[i].getTurnAroundTime()+"\t\t\t\t"+exeOrder[i]);
        }
        System.out.println("Average waiting time= "+total_wt/n);
        System.out.println("Average turn around time= "+total_tat/n);
    }
}
