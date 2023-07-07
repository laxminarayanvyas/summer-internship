package com.fintech;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SftpCommandScheduler {

    private final SftpCommandExecutor commandExecutor;

    public SftpCommandScheduler(SftpCommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Scheduled(fixedRate = 8000) // Executes every 5 seconds
    public void executeSftpCommand() {
        commandExecutor.executeSftpCommand();
    }
}

