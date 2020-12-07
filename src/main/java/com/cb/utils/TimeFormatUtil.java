package com.cb.utils;

import com.cb.config.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description: 时间转换工具
 * @author:
 * @create: 2020-11-12 17:40
 **/
@Slf4j
public class TimeFormatUtil {
    private static DateTimeFormatter dateTimeFormat=DateTimeFormatter.ofPattern(CommonConfig.timeformat);


    public static String localDateTimeNow(){
        return dateTimeFormat.format(LocalDateTime.now());
    }
    /**
     * @Description: LocalDateTime格式化
     * @param localDateTime  时间
     *
     * @return: 格式化的时间字符串
     * @Date: 2020/11/12
     */
    public static String localDateTimetoString(LocalDateTime localDateTime){
        try{
           return dateTimeFormat.format(localDateTime);
        }catch (Exception e){
            log.error(MessageFormat.format("LocalDateTime转换失败,{0}",e.getMessage()));
            return null;
        }
    }


}
