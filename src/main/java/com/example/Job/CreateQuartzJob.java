//import package  
package com.example.Job;

//import classes  
//import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.logging.Logger;

//create CreateQuartzJob class that implements Job  
public class CreateQuartzJob implements Job{

    //Create instance of logger  
    //private Logger log = Logger.getLogger(CreateQuartzJob.class);

    //execute Job by using execute() method of the Job interface  
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {//handle JobExecutionException  

        //debug message   
        System.out.println("CreateQuartzJob is running......");
    }
}  