public class Process
{
    private int arrivalTime;
    private int endTime;
    private int burstTime;
    private int priority;
    private String name;

    private int turnAroundTime=0;
    private int waitingTime=0;

    private int ExecutionTime;


    public Process()
    {}
    public void setArrivalTime(int arrTime)
    {
        arrivalTime=arrTime;
    }
    public void setburstTime(int bTime)
    {
        burstTime=bTime;
    }
    public void setPriority(int p)
    {
        priority=p;
    }
    public void setName(String n)
    {
        name=n;
    }
    public void setEndTime(int endT)
    {
        endTime = endT;
    }
    public void setTurnAroundTime(int turnAroundT) { turnAroundTime = turnAroundT; }
    public void setWaitingTime(int waitingT) {
        waitingTime = waitingT;
    }
    public void setStartExecutionTime(int ExecutionT) { ExecutionTime = ExecutionT; }
    public int getStartExecutionTime() { return ExecutionTime; }
    public int getEndTime()
    {
        return endTime;
    }
    public int getWaitingTime()
    {
        return waitingTime;
    }
    public int getTurnAroundTime()
    {
        return turnAroundTime;
    }
    public int getArrivalTime()
    {
        return arrivalTime;
    }
    public int getburstTime()
    {
        return burstTime;
    }
    public int getPriority()
    {
        return priority;
    }
    public String getName()
    {
        return name;
    }
    public int compare(Process process) { return Integer.compare(this.arrivalTime, process.arrivalTime);}
}
