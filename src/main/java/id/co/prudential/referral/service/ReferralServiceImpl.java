package id.co.prudential.referral.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import id.co.prudential.referral.dao.ReferralDao;
import id.co.prudential.referral.dto.ReferralTO;

@Service
public class ReferralServiceImpl implements ReferralService {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;
	
	private static String logMessage = "";

	private static final Logger log = LoggerFactory.getLogger(ReferralServiceImpl.class);
	
	private final String insertReferralQuery = "INSERT INTO pd_activity_management.referral_master "
			+ "(referral_code, referral_name, channel, rm_psid, branch_office_code, created_date, created_by, active_flag, updated_by, updated_date) "
			+ "VALUES(:referralCode, :referralName, :channel, :rmPsid, '', now(), 'system', true, 'system', now());";
	
	@Autowired
	private ReferralDao referralDao;

	@Override
	public List<ReferralTO> getReferralByChannel(String channel) throws Exception {
		List<ReferralTO> result = new ArrayList<ReferralTO>();
		try {
			if (channel.equals("BUOI")) {
				channel = "UOB";
			}
			result = referralDao.getByChannel(channel);
		} catch (Exception e) {
			log.error("gagal masuk service referral", e.getMessage());
			throw e;
		}
		return result;
	}

	@Transactional
	@Override
	public void upload(String channel, MultipartFile file) throws Exception {
		byte[] fileContent = null;
		List<ReferralTO> newReferralData = new ArrayList<ReferralTO>();
		SqlParameterSource[] batchSql = null;
		try {
			fileContent = file.getBytes();
			List<List<String>> batchData = readFile(byteToFile(fileContent));
			int lineLength = batchData.size();
			
			// backup referral master
			referralDao.moveMasterToHistory(channel);
			
			// delete current referral master
			referralDao.deleteByChannel(channel);
			
			for (int i = 0; i < lineLength; i++) {
				ReferralTO referral = new ReferralTO();
				referral.setReferralCode(batchData.get(i).get(0));
				referral.setReferralName(batchData.get(i).get(1));
				referral.setChannel(batchData.get(i).get(2));
				if (channel.equals("SCB")) {
					referral.setRmPsid(batchData.get(i).get(3));
				} else {
					referral.setRmPsid("");
				}
				newReferralData.add(referral);
			}
			
			batchSql = SqlParameterSourceUtils.createBatch(newReferralData.toArray());
			jdbcTemplate.batchUpdate(insertReferralQuery, batchSql);
			
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private File byteToFile(byte[] fileContent) {
		File tmpFile = null;
		FileOutputStream fileToImport = null;
		try {
			tmpFile = File.createTempFile("tempFile", ".tmp");
			fileToImport = new FileOutputStream(tmpFile, true);
			// write byte to file
			fileToImport.write(fileContent);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(fileToImport != null) {
				try {
					fileToImport.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return tmpFile;
	}
	
	public List<List<String>> readFile(File file) {
		FileReader fileReader = null;
		BufferedReader br = null;
		List<List<String>> records = new ArrayList<>();
		
		try {
			try {
				fileReader = new FileReader(file);
			} catch (Exception e) {
				logMessage += "Gagal membuka file.\r\n";
			}
			
			//read file
			br = new BufferedReader(fileReader);
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				records.add(Arrays.asList(values));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return records;
	}

}
