package com.ky.tests.timelog.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ky.tests.main.SvcMain;
import com.ky.tests.timelog.TimeLog;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SvcMain.class)
@TestPropertySource(locations = "classpath:conf/test.properties")
public class TimeLogDaoTest
{
    @Resource
    TimeLogDao dao;

    @Test
    public void queryTest()
    {
        List<TimeLog> timeLogs = dao.queryLogInfo();
        for (TimeLog timeLog : timeLogs)
        {
            System.out.println(timeLog);
        }
        int totalCnt = timeLogs.size();
        long interruptCnt = dao.queryInterruptCnt("Interrupt");
        System.out.println("Ratio = " + interruptCnt / (totalCnt * 1.0) + " (" + interruptCnt + "/" + totalCnt + ")");
    }
}
