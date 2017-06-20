package com.ky.tests.timelog;

import java.util.Date;

public class TimeLog
{
    long id;
    String task_desc;
    Date start_time;
    Date end_time;
    String fin_type;
    String interrupt_reason;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getTask_desc()
    {
        return task_desc;
    }

    public void setTask_desc(String task_desc)
    {
        this.task_desc = task_desc;
    }

    public Date getStart_time()
    {
        return start_time;
    }

    public void setStart_time(Date start_time)
    {
        this.start_time = start_time;
    }

    public Date getEnd_time()
    {
        return end_time;
    }

    public void setEnd_time(Date end_time)
    {
        this.end_time = end_time;
    }

    public String getFin_type()
    {
        return fin_type;
    }

    public void setFin_type(String fin_type)
    {
        this.fin_type = fin_type;
    }

    public String getInterrupt_reason()
    {
        return interrupt_reason;
    }

    public void setInterrupt_reason(String interrupt_reason)
    {
        this.interrupt_reason = interrupt_reason;
    }

    @Override
    public String toString()
    {
        return "{\"id\":\"" + id + "\",\"task_desc\":\"" + task_desc + "\",\"start_time\":\"" + start_time
                        + "\",\"end_time\":\"" + end_time + "\",\"fin_type\":\"" + fin_type
                        + "\",\"interrupt_reason\":\"" + interrupt_reason + "\"}  ";
    }
}
