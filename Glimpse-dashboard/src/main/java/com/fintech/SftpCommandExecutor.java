package com.fintech;

import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SftpCommandExecutor {

    @Value("${sftp.host}")
    private String sftpHost;

    @Value("${sftp.port}")
    private int sftpPort;

    @Value("${sftp.username}")
    private String sftpUsername;

    @Value("${sftp.private-key-path}")
    private String privateKeyPath;

    private JSch jsch;
    private Session session;

    @PostConstruct
    public void initialize() throws JSchException {
        jsch = new JSch();
        jsch.addIdentity(privateKeyPath);
        session = jsch.getSession(sftpUsername, sftpHost, sftpPort);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }

    public void executeSftpCommand() {
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("sudo chmod -R 700 /home/ubuntu/compressed_uploads/*");
            channel.setInputStream(null);
            channel.setErrStream(System.err);
            channel.connect();
            System.out.println("SFTP command executed successfully.");
        } catch (JSchException e) {
            System.err.println("Failed to execute SFTP command: " + e.getMessage());
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    public void closeSession() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}
