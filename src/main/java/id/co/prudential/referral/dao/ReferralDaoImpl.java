package id.co.prudential.referral.dao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import id.co.prudential.referral.dto.ReferralTO;

@Transactional
@Component
public class ReferralDaoImpl implements ReferralDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final Logger log = LoggerFactory.getLogger(ReferralDaoImpl.class);

	@Override
	public List<ReferralTO> getByChannel(String channel) throws Exception {
		String query = "SELECT referral_code, referral_name, channel, rm_psid, branch_office_code "
				+ "FROM pd_activity_management.referral_master "
				+ "WHERE (channel = :channel OR :channel = '' OR :channel isnull) and active_flag = true;";
		
		List<ReferralTO> result = null;
		try {
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("channel", channel);
			
			result = jdbcTemplate.query(query, queryParams, new BeanPropertyRowMapper<>(ReferralTO.class));
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public void deleteByChannel(String channel) throws Exception {
		String query = "DELETE from pd_activity_management.referral_master where channel = :channel";
		
		try {
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("channel", channel);
			
			jdbcTemplate.update(query, queryParams);
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public void moveMasterToHistory(String channel) throws Exception {
		String query = "INSERT into pd_activity_management.referral_master_history "
				+ "(SELECT * FROM pd_activity_management.referral_master where channel = :channel)";
		
		try {
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("channel", channel);
			
			jdbcTemplate.update(query, queryParams);
		} catch (Exception e) {
			throw e;
		}
		
	}

}
