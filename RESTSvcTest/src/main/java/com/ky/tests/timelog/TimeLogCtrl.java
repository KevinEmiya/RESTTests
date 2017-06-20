package com.ky.tests.timelog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ky.tests.timelog.mapper.TimeLogDao;

@RestController
@RequestMapping(path = "/timelog")
public class TimeLogCtrl
{
    @Resource
    protected TimeLogDao dao;
    
    @RequestMapping(path = "/all")
    public List<TimeLog> queryAllTimeLogs()
    {
        return dao.queryLogInfo();
    }
}
