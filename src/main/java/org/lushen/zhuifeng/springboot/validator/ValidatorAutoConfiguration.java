package org.lushen.zhuifeng.springboot.validator;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;

/**
 * validator验证 的配置
 * 
 * @author hlm
 */
@Configuration
@EnableConfigurationProperties(ValidatorProperties.class)
@ConditionalOnProperty(prefix=ValidatorProperties.VALIDATOR_PREFIX, name="enabled", matchIfMissing=true)
public class ValidatorAutoConfiguration {
	
	private Log log = LogFactory.getLog(getClass());
	
	private ValidatorProperties properties;
	
	private ApplicationContext applicationContext;
	
	public ValidatorAutoConfiguration(ValidatorProperties validatorProperties, ApplicationContext applicationContext) {
		super();
		this.properties = validatorProperties;
		this.applicationContext = applicationContext;
	}

	/**
	 * ConfigurableWebBindingInitializer  注册验证器工厂
	 */
	@Bean
	@ConditionalOnMissingBean
	public ConfigurableWebBindingInitializer configurableWebBindingInitializer() throws IOException {
		
		log.info("create ConfigurableWebBindingInitializer when miss bean :: " + ConfigurableWebBindingInitializer.class.getName());
		
		ConfigurableWebBindingInitializer bindingInitializer = new ConfigurableWebBindingInitializer();
		bindingInitializer.setValidator(localValidatorFactoryBean());
		
		return bindingInitializer;
	}

	/**
	 * ValidatorFactoryBean  验证器工厂
	 */
	@Bean
	@ConditionalOnMissingBean
	public LocalValidatorFactoryBean localValidatorFactoryBean() throws IOException {
		
		log.info("create LocalValidatorFactoryBean when miss bean :: " + LocalValidatorFactoryBean.class.getName());
		
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(bundleMessageSource());
		localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
		
		return localValidatorFactoryBean;
	}
	
	/**
	 * MessageSource  加载验证配置信息
	 */
	@Bean
	@ConditionalOnMissingBean
	public ReloadableResourceBundleMessageSource bundleMessageSource() throws IOException {
		
		log.info("load validator message from bean");
		
		Collection<ValidatorMessageLoader> loaders = applicationContext.getBeansOfType(ValidatorMessageLoader.class).values();
		
		Properties properties = new Properties();
		
		for(ValidatorMessageLoader loader : loaders) {
			
			log.info("load validator message from bean :: " + loader.getClass().getName());
			
			Properties loaderProps = loader.loadMessages();
			
			if(loaderProps != null) {
				properties.putAll(loader.loadMessages());
			}
		}
		
		log.info("create ReloadableResourceBundleMessageSource and set validator message source :: " + ReloadableResourceBundleMessageSource.class.getName());
		
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setCommonMessages(properties);
		bundleMessageSource.setDefaultEncoding(this.properties.getEncoding());
		bundleMessageSource.setAlwaysUseMessageFormat(this.properties.isAlwaysUseMessageFormat());
		bundleMessageSource.setBasenames(this.properties.getBasenames());
		bundleMessageSource.setCacheMillis(this.properties.getCacheMillis());
		bundleMessageSource.setCacheSeconds(this.properties.getCacheSeconds());
		bundleMessageSource.setConcurrentRefresh(this.properties.isConcurrentRefresh());
		bundleMessageSource.setFallbackToSystemLocale(this.properties.isFallbackToSystemLocale());
		bundleMessageSource.setUseCodeAsDefaultMessage(this.properties.isUseCodeAsDefaultMessage());
		
		return bundleMessageSource;
	}

}
