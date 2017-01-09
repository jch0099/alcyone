package com.xushi.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xushi.service.UserService;
import com.xushi.service.VideoService;

@Component 
public class VipVideoTask {

	@Autowired UserService userService;
	@Autowired VideoService videoService;
	
	@Scheduled(cron="0 1 0 * * ?")//早上凌晨12点01分处理用户信息和视频信息
	public void handle(){
		userService.ScheduledHandle();
	}
}
