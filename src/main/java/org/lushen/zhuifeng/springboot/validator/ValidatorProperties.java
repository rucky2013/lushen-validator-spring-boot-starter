package org.lushen.zhuifeng.springboot.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 配置
 * 
 * @author hlm
 */
@ConfigurationProperties(prefix = ValidatorProperties.VALIDATOR_PREFIX)
public class ValidatorProperties implements InitializingBean {

	protected static final String VALIDATOR_PREFIX = "lushen.validator";	//配置前缀
	
	private static final String[] DEFAULT_VALIDATOR_FILE = new String[]{"classpath:validator"};	//默认配置文件
	
	private static final String DEFAULT_CHARSET = "UTF-8";		//默认编码格式
	
	private Log log = LogFactory.getLog(getClass());
	
	//====================================================================================
	//====================================================================================
	//====================================================================================
	
	private boolean enabled;
	
	private String encoding = DEFAULT_CHARSET;
	
	private boolean alwaysUseMessageFormat = false;
	
	private String[] basenames = DEFAULT_VALIDATOR_FILE;
	
	private long cacheMillis = -1;
	
	private int cacheSeconds = -1;
	
	private boolean concurrentRefresh = true;
	
	private boolean fallbackToSystemLocale = true;
	
	private boolean useCodeAsDefaultMessage = false;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isAlwaysUseMessageFormat() {
		return alwaysUseMessageFormat;
	}

	public void setAlwaysUseMessageFormat(boolean alwaysUseMessageFormat) {
		this.alwaysUseMessageFormat = alwaysUseMessageFormat;
	}

	public String[] getBasenames() {
		return basenames;
	}

	public void setBasenames(String[] basenames) {
		this.basenames = basenames;
	}

	public long getCacheMillis() {
		return cacheMillis;
	}

	public void setCacheMillis(long cacheMillis) {
		this.cacheMillis = cacheMillis;
	}

	public int getCacheSeconds() {
		return cacheSeconds;
	}

	public void setCacheSeconds(int cacheSeconds) {
		this.cacheSeconds = cacheSeconds;
	}

	public boolean isConcurrentRefresh() {
		return concurrentRefresh;
	}

	public void setConcurrentRefresh(boolean concurrentRefresh) {
		this.concurrentRefresh = concurrentRefresh;
	}

	public boolean isFallbackToSystemLocale() {
		return fallbackToSystemLocale;
	}

	public void setFallbackToSystemLocale(boolean fallbackToSystemLocale) {
		this.fallbackToSystemLocale = fallbackToSystemLocale;
	}

	public boolean isUseCodeAsDefaultMessage() {
		return useCodeAsDefaultMessage;
	}

	public void setUseCodeAsDefaultMessage(boolean useCodeAsDefaultMessage) {
		this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		log.info("properties :: " + new ObjectMapper().writeValueAsString(this));
	
	}

}
