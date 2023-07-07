package com.fintech;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class SftpCommandExecutor {

	/*
	 * @Value("${sftp.host}") private String host;
	 * 
	 * @Value("${sftp.port}") private int port;
	 * 
	 * @Value("${sftp.username}") private String username;
	 * 
	 * @Value("${sftp.password}") private String password;
	 */
	private static final String SFTP_HOST = "35.178.89.183";
	private static final int SFTP_PORT = 22;
	private static final String SFTP_USERNAME = "ubuntu";
	private static final String PRIVATE_KEY_PATH = "D:\\ADMIN\\git\\summer-internship\\Glimpse-dashboard\\src\\main\\java\\com\\fintech\\thekey";

    @PostConstruct
    public void executeSftpCommand() {
    	JSch jSch = new JSch();
    	 try {
    		 jSch.addIdentity(PRIVATE_KEY_PATH);
             Session session = jSch.getSession(SFTP_USERNAME, SFTP_HOST, SFTP_PORT);
             session.setConfig("StrictHostKeyChecking", "no");
             session.connect();

             Channel channel = session.openChannel("exec");
             ((ChannelExec) channel).setCommand("sudo chmod -R 700 /home/ubuntu/compressed_uploads/*");
             channel.setInputStream(null);
             ((ChannelExec) channel).setErrStream(System.err);

             channel.connect();

             channel.disconnect();
             session.disconnect();

             System.out.println("SFTP command executed successfully.");
         } catch (JSchException e) {
             System.err.println("Failed to execute SFTP command: " + e.getMessage());
         }
    }
}
