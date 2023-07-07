package com.fintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fintech.script.FileWatcherService;
import com.jcraft.jsch.SftpException;

@SpringBootApplication
@EnableScheduling
public class GlimpseDashboardApplication {

	public static void main(String[] args) throws SftpException {
		SpringApplication.run(GlimpseDashboardApplication.class, args);
		System.out.println("welcome to boot project bro"); 
	}
}
