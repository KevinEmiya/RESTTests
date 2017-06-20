package com.ky.tests.timelog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ky.tests.timelog.TimeLog;

@Repository
public interface TimeLogDao
{
    public static final String TABLE_LOG_INFO = "ky_log_info";
    
    @Select("select * from `" + TABLE_LOG_INFO + "`")
    List<TimeLog> queryLogInfo();
    
    @Select("select count(*) from `" + TABLE_LOG_INFO + "` where `fin_type`=#{finType}")
    long queryInterruptCnt(@Param("finType") String finType);
}
