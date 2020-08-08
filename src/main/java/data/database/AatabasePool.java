package data.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import data.config.Config;
import data.config.ConfigPath;

public class AatabasePool {
	
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean Start() {
		Object DriverClass;
		Object InitialPoolSize;
		Object MaxPoolSize;
		Object MinPoolSize;
		Object AcquireIncrement;
        
		if((DriverClass=Config.getConfigData(new ConfigPath("data","database","pool","driverClass")))==null&
				(InitialPoolSize=Config.getConfigData(new ConfigPath("data","database","pool","initialPoolSize")))==null&
				(MaxPoolSize=Config.getConfigData(new ConfigPath("data","database","pool","maxPoolSize")))==null&
				(MinPoolSize=Config.getConfigData(new ConfigPath("data","database","pool","minPoolSize")))==null&
				(AcquireIncrement=Config.getConfigData(new ConfigPath("data","database","pool","acquireIncrement")))==null
				) {
				return false;
			}
		try {
            dataSource.setDriverClass((String) DriverClass);
            dataSource.setJdbcUrl("jdbc:derby:"+System.getProperty("user.dir")+"/user");
            dataSource.setInitialPoolSize((int)InitialPoolSize);
            dataSource.setMaxPoolSize((int) MaxPoolSize);
            dataSource.setMinPoolSize((int) MinPoolSize);
            dataSource.setAcquireIncrement((int) AcquireIncrement);
        } catch (PropertyVetoException e) {
        	logger.error(" ˝æ›ø‚≥ÿ≈‰÷√”–ŒÛ",e.toString());
            return false;
        }
		return true;
	}
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	public void close() {
		dataSource.close();
	}
}
