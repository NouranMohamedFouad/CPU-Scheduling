public class RoundRobin
{
    public  void schedulerforRR(int numOfP,int QuantumT)
    {
        int remainingBT[]=new int[numOfP];
        int remainingarrivalT[]=new int[numOfP];
        int waitingTime[]=new int[numOfP];
        int turnAroundT[]=new int[numOfP];
        int arrivalT[]=new int[numOfP];
        int burstTime[]=new int[numOfP];
        int totalTime=0;
        int TotalWaitingTime=0;
        int TotalTurnAroundTime=0;
        float AverageWaitingTime;
        float AverageTurnAroundTime;
        // String seq = new String();

        for(int i=0;i<numOfP;i++)
        {
            remainingBT[i]=burstTime[i];
            remainingarrivalT[i]=arrivalT[i];
        }

        //scheduler
        while(true)
        {
            boolean Done=true;
            for(int i=0;i<numOfP;i++)
            {
                if(remainingarrivalT[i]<=totalTime)
                {
                    if(remainingarrivalT[i]<=QuantumT )
                    {

                        if(remainingBT[i]>0)
                        {
                            Done=false;
                            if(remainingBT[i]>QuantumT)
                            {
                                totalTime=totalTime+QuantumT;
                                remainingarrivalT[i]=remainingarrivalT[i]+QuantumT;
                                remainingBT[i]=remainingBT[i]-QuantumT;
                                // seq += "->" + P[i];

                            }

                            else
                            {
                                totalTime+=(remainingBT[i]);
                                turnAroundT[i]=totalTime-arrivalT[i];
                                waitingTime[i]=turnAroundT[i]-burstTime[i];
                                remainingBT[i]=0;
                                // seq += "->" + P[i];

                            }
                        }
                    }
                    else if (remainingarrivalT[i] >QuantumT ) {
                        for (int j = 0; j < numOfP; j++) {
                            // compare
                            if (remainingarrivalT[j] < remainingarrivalT[i]) {
                                if (remainingBT[j] > 0) {
                                    Done = false;
                                    if (remainingBT[j] > numOfP) {
                                        totalTime = totalTime + numOfP;
                                        remainingBT[j] = remainingBT[j] - numOfP;
                                        remainingarrivalT[j] = remainingarrivalT[j] + numOfP;
                                        //seq += "->" + p[j];
                                    }
                                    else {
                                        totalTime = totalTime + remainingBT[j];
                                        turnAroundT[j] = totalTime - arrivalT[j];
                                        waitingTime[j] =turnAroundT[j]-burstTime[j];
                                        remainingBT[j]=0;
                                        // seq += "->" + P[j];
                                    }
                                }
                            }
                            // ith is process
                            if (remainingBT[i] > 0) {
                                Done = false;

                                // Check for greaters
                                if (remainingBT[i] > numOfP) {
                                    totalTime = totalTime + numOfP;
                                    remainingBT[i] = remainingBT[i] - numOfP;
                                    remainingarrivalT[i] = remainingarrivalT[i] + numOfP;
                                    // seq += "->" + p[i];
                                }
                                else {
                                    totalTime = totalTime + remainingBT[i];
                                    turnAroundT[i] = totalTime - arrivalT[i];
                                    waitingTime[i] = turnAroundT[i]- burstTime[i];
                                    remainingBT[i] = 0;
                                    //seq += "->" + p[i];
                                }
                            }
                        }
                    }
                }
                else if (remainingarrivalT[i] > totalTime) {
                    totalTime++;
                    i--;
                }
                if(Done)
                    break;
            }

            //calculate total waiting time & total turn around time
            for(int i=0;i<numOfP;i++)
            {
                TotalWaitingTime+= waitingTime[i];
                TotalTurnAroundTime+=turnAroundT[i];
            }
            // calculate average
            AverageWaitingTime= TotalWaitingTime/numOfP;
            AverageTurnAroundTime= TotalTurnAroundTime/numOfP;
            System.out.println("Average waiting time is " +AverageWaitingTime);
            System.out.println("Average turn around time is " +AverageTurnAroundTime);

        }
    }

        /*public void schedulerforRR(int numOfP,Process[] p,int QuantumT, int CS)
        {
            int remainingBT[]=new int[numOfP];
            int waitingTime[]=new int[numOfP];
            int turnAroundT[]=new int[numOfP];
            //int burstTime[]=new int[numOfP];
            int totalTime=0;
            int TotalWaitingTime=0;
            int TotalTurnAroundTime=0;
            float AverageWaitingTime;
            float AverageTurnAroundTime;

            for(int i=0;i<numOfP;i++)
            {
                remainingBT[i]=p[i].getburstTime();
            }

            //scheduler
            while(true)
            {
                boolean Done=true;
                for(int i=0;i<numOfP;i++)
                {
                    if(remainingBT[i]>0)
                    {
                        Done=false;
                        if(remainingBT[i]>QuantumT)
                        {
                            totalTime=totalTime+QuantumT+CS;
                            remainingBT[i]=remainingBT[i]-QuantumT;
                        }

                        else
                        {
                            totalTime+=(remainingBT[i]+CS);
                            waitingTime[i]=totalTime-p[i].getburstTime();
                            remainingBT[i]=0;
                        }
                    }
                }
                if(Done)
                    break;
            }
            //calculate Turn around time
            for(int i=0;i<numOfP;i++)
            {
                turnAroundT[i]=waitingTime[i]+p[i].getburstTime();
            }
            //calculate total waiting time & total turn around time
            for(int i=0;i<numOfP;i++)
            {
                TotalWaitingTime+= waitingTime[i];
                TotalTurnAroundTime+=turnAroundT[i];
            }
            for(int i=0;i<numOfP;i++)
            {
                System.out.println("waiting Time of p"+(i+1)+"="+waitingTime[i]);
                System.out.println("turn around Time of p"+(i+1)+"="+turnAroundT[i]);

            }
            // calculate average
            AverageWaitingTime=TotalWaitingTime/numOfP;
            System.out.println("avrg waiting time="+ AverageWaitingTime);
            AverageTurnAroundTime= TotalTurnAroundTime/numOfP;
            System.out.println("avrg turn around="+AverageTurnAroundTime);
        }

         */

}
