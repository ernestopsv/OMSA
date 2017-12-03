package com.proyectoFinal.OMSA.Jobs;

import com.proyectoFinal.OMSA.Services.AutobusServices;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anyderre on 03/12/17.
 */
public class AutobusJob implements Job {
    @Autowired
    private AutobusServices autobusServices;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        autobusServices.modificarAutobus();
    }
}
