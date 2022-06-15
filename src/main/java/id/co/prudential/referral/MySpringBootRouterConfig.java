package id.co.prudential.referral;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@ComponentScan
public class MySpringBootRouterConfig {

//	@Value("${spring.datasource.driver}")
//	private String datasourceDriver;
//
//	@Value("${spring.datasource.url}")
//	private String datasourceUrl;
//
//	@Value("${spring.datasource.username}")
//	private String datasourceUsername;
//
//	@Value("${spring.datasource.password}")
//	private String datasourcePassword;
//
//	@Value("${spring.datasource.maxActive}")
//	private String datasourceMaxActive;
//
//	@Value("${spring.datasource.initialSize}")
//	private String datasourceInitialSize;
//
//	@Value("${spring.datasource.className}")
//	private String datasourceClassName;
//
//	public String getDatasourceDriver() {
//		return datasourceDriver;
//	}
//
//	public void setDatasourceDriver(String datasourceDriver) {
//		this.datasourceDriver = datasourceDriver;
//	}
//
//	public String getDatasourceUrl() {
//		return datasourceUrl;
//	}
//
//	public void setDatasourceUrl(String datasourceUrl) {
//		this.datasourceUrl = datasourceUrl;
//	}
//
//	public String getDatasourceUsername() {
//		return datasourceUsername;
//	}
//
//	public void setDatasourceUsername(String datasourceUsername) {
//		this.datasourceUsername = datasourceUsername;
//	}
//
//	public String getDatasourcePassword() {
//		return datasourcePassword;
//	}
//
//	public void setDatasourcePassword(String datasourcePassword) {
//		this.datasourcePassword = datasourcePassword;
//	}
//
//	public String getDatasourceMaxActive() {
//		return datasourceMaxActive;
//	}
//
//	public void setDatasourceMaxActive(String datasourceMaxActive) {
//		this.datasourceMaxActive = datasourceMaxActive;
//	}
//
//	public String getDatasourceInitialSize() {
//		return datasourceInitialSize;
//	}
//
//	public void setDatasourceInitialSize(String datasourceInitialSize) {
//		this.datasourceInitialSize = datasourceInitialSize;
//	}

	@Value("${spring.datasource.jndiName}")
	private String jndiName;

	public static class JndiPropertyHolder {
		private String jndiName;

		public String getJndiName() {
			return jndiName;
		}

		public void setJndiName(String jndiName) {
			this.jndiName = jndiName;
		}
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public JndiPropertyHolder jndi() {
		return new JndiPropertyHolder();
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		DataSource dataSource = dataSourceLookup.getDataSource(jndi().getJndiName());
		return dataSource;
	}

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

}