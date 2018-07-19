package com.taptap.taptap.Jobs;

import com.taptap.taptap.Service.AutobusServices;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class AutobusJob implements Job {
    @Autowired
    private AutobusServices autobusServices;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("Executing job");
        try{
            autobusServices.modificarAutobus();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //  System.out.println("Hello World");

}

}
