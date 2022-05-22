package com.bycw.jobs;


import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;




/**
 * 每日５点定时job
 */
@DisallowConcurrentExecution
public class DailyResetJob implements Job {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(DailyResetJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("每日５点定时任务开始");
	}

}
