package com.fintech.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import com.fintech.beans.ConsolidatedOPFile;
import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;
import com.fintech.beans.Users;
import com.fintech.dao.ConsolidatedDAO;
import com.fintech.dao.FileDAO;
import com.fintech.dao.OutputFileDAO;
import com.fintech.dao.UserDao;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileService.class);
	private static final String SFTP_HOST = "35.178.89.183";
	private static final int SFTP_PORT = 22;
	private static final String SFTP_USERNAME = "ubuntu";
	private static final String PRIVATE_KEY_PATH = "D:\\ADMIN\\git\\summer-internship\\Glimpse-dashboard\\src\\main\\java\\com\\fintech\\thekey";

	@Autowired
	private UserDao userDao;
	@Autowired
	private FileDAO fileDao;

	@Autowired
	private OutputFileDAO outputDao; // for all output type files

	@Autowired
	private ConsolidatedDAO cnDao;

	public Stream<String> load(LocalDate from_date, LocalDate to_date, Object guide, String size_type, int is_test) {
		List<OutputFile> listOfFile = outputDao.fetchOPFile(from_date, to_date, guide, size_type, is_test);

		if (listOfFile.size() == 0)
			return null;
		else {
			// ByteArrayInputStream in = ExcelHelper.OPFileToCSV(listOfFile);
			Stream<String> csvData = ExcelHelper.OPFileToCSV(listOfFile);
			return csvData;
			// return in;
		}

	}

	@Override
	public List<OutputFile> getDailyOPFile(LocalDate from_date, LocalDate to_date, Object guide, String size_type,
			int is_test) {
		// TODO Auto-generated method stub
		return outputDao.fetchOPFile(from_date, to_date, guide, size_type, is_test);
	}

	@Override
	public List<OutputFile> getMonthlyOPFile(LocalDate from_date, LocalDate to_date, Object guide, String size_type,
			int is_test) {
		// TODO Auto-generated method stub

		return outputDao.fetchOPFile(from_date, to_date, guide, size_type, is_test);
	}

	@Override
	public List<ConsolidatedOPFile> getConsolidatedOP(String from_date, String to_date, String type) {
		// TODO Auto-generated method stub
		return cnDao.fetchConsolidatedOP(from_date, to_date, type);

		// ByteArrayInputStream in = ExcelHelper.consolidatedOP(listOfFile);
		// return in;
	}

	@Override
	/*
	 * public List<DailyClientProcessingFile> getDailyClientFile(String event, int
	 * status, LocalDateTime from_dateTime, LocalDateTime to_dateTime) {
	 * 
	 * List<DailyClientProcessingFile> mergedList = new ArrayList<>();
	 * List<DailyClientProcessingFile> dataUsers; Set<Users> allUsers;
	 * Comparator<DailyClientProcessingFile> comparator =
	 * Comparator.comparing(DailyClientProcessingFile::getUpdated_date,
	 * Comparator.nullsLast(Comparator.naturalOrder()));
	 * 
	 * allUsers=userDao.getAllUsers(); dataUsers=fileDao.fetchDailyClientFile(event,
	 * status, from_dateTime, to_dateTime); List<DailyClientProcessingFile> userList
	 * = new ArrayList<>(); for (Users user : allUsers) { String username =
	 * user.getUserName();
	 * 
	 * // Check if the username exists in the dataUsers list boolean usernameExists
	 * = dataUsers.stream().anyMatch(dataUser ->
	 * dataUser.getUser_name().equals(username)); if (!usernameExists) { // Create a
	 * new DailyClientProcessingFile object and set the username
	 * DailyClientProcessingFile dailyClientProcessingFile = new
	 * DailyClientProcessingFile();
	 * dailyClientProcessingFile.setUser_name(username);
	 * userList.add(dailyClientProcessingFile); } }
	 * 
	 * // Add the elements from the list to the merged list
	 * mergedList.addAll(userList); mergedList.addAll(dataUsers);
	 * mergedList.sort(comparator); return mergedList; }
	 */
	public List<DailyClientProcessingFile> getDailyClientFile(String event, int status, LocalDateTime from_dateTime, LocalDateTime to_dateTime) {
	    List<DailyClientProcessingFile> mergedList = new ArrayList<>();
	    List<DailyClientProcessingFile> dataUsers;
	    Set<String> existingUsernames = new HashSet<>();
	    Comparator<DailyClientProcessingFile> comparator = Comparator.comparing(DailyClientProcessingFile::getUpdated_date, Comparator.nullsLast(Comparator.naturalOrder()));

	    Set<Users> allUsers = userDao.getAllUsers();
	    dataUsers = fileDao.fetchDailyClientFile(event, status, from_dateTime, to_dateTime);

	    // Add the existing usernames to the set
	    for (DailyClientProcessingFile dataUser : dataUsers) {
	        existingUsernames.add(dataUser.getUser_name());
	    }

	    // Add the dataUsers to the mergedList
	    mergedList.addAll(dataUsers);

	    // Check for missing usernames and add them to the mergedList
	    for (Users user : allUsers) {
	        String username = user.getUserName();
	        if (!existingUsernames.contains(username)) {
	            DailyClientProcessingFile dailyClientProcessingFile = new DailyClientProcessingFile();
	            dailyClientProcessingFile.setUser_name(username);
	            mergedList.add(dailyClientProcessingFile);
	        }
	    }

	    // Sort the mergedList
	    mergedList.sort(comparator);
	    return mergedList;
	}



	@Override
	public List<DailyClientProcessingFile> getDailyMonthlyOPFilesStat(String event_type, int test,
			LocalDateTime from_dateTime, LocalDateTime to_dateTime) {
		// TODO Auto-generated method stub

		return fileDao.fetchOPFileList(event_type, test, from_dateTime, to_dateTime);
	}

	
	public byte[] downloadFile(String clientName, String filePath) {
        try {
            JSch jsch = new JSch();

            if (PRIVATE_KEY_PATH != null && !PRIVATE_KEY_PATH.isEmpty()) {
                try {
                    jsch.addIdentity(PRIVATE_KEY_PATH);
                } catch (JSchException e) {
                    System.out.println("Failed to load private key: " + e.getMessage());
                }
            }

            Session session = jsch.getSession(SFTP_USERNAME, SFTP_HOST, SFTP_PORT);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            if (!session.isConnected()) {
                logger.error("Failed to establish SFTP session.");
                return null;
            }

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            if (!channelSftp.isConnected()) {
                logger.error("Failed to establish SFTP channel.");
                session.disconnect();
                return null;
            }

            // Build the full file path with the dynamic client name
            String fullFilePath = "/home/ubuntu/compressed_uploads/" + clientName + "/" + filePath;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            channelSftp.get(fullFilePath, outputStream);

            channelSftp.disconnect();
            session.disconnect();

            return outputStream.toByteArray();
        } catch (JSchException | SftpException e) {
            logger.error("SFTP operation failed: {}", e.getMessage());
        }

        return null;
    }
	/*
	 * public byte[] downloadFile(String clientName, String filePath) { try { JSch
	 * jsch = new JSch();
	 * 
	 * if (PRIVATE_KEY_PATH != null && !PRIVATE_KEY_PATH.isEmpty()) {
	 * jsch.addIdentity(PRIVATE_KEY_PATH); }
	 * 
	 * Session session = jsch.getSession(SFTP_USERNAME, SFTP_HOST, SFTP_PORT);
	 * session.setConfig("StrictHostKeyChecking", "no"); session.connect();
	 * 
	 * ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
	 * channelSftp.connect();
	 * 
	 * // Build the full file path with the dynamic client name String fullFilePath
	 * = "/home/ubuntu/compressed_uploads/" + clientName + "/" + filePath;
	 * 
	 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	 * channelSftp.get(fullFilePath, outputStream);
	 * 
	 * channelSftp.disconnect(); session.disconnect();
	 * 
	 * return outputStream.toByteArray(); } catch (JSchException | SftpException e)
	 * { // Handle exceptions }
	 * 
	 * return null; }
	 */
}
