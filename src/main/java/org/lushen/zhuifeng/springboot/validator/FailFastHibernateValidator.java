package org.lushen.zhuifeng.springboot.validator;

import javax.validation.Configuration;
import javax.validation.ValidatorFactory;
import javax.validation.spi.BootstrapState;
import javax.validation.spi.ConfigurationState;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.internal.engine.ConfigurationImpl;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;

public class FailFastHibernateValidator extends HibernateValidator {

	public HibernateValidatorConfiguration createSpecializedConfiguration(BootstrapState state) {
		ConfigurationImpl configurationImpl = new ConfigurationImpl( this );
		configurationImpl.failFast(true);
		return HibernateValidatorConfiguration.class.cast(configurationImpl);
	}

	public Configuration<?> createGenericConfiguration(BootstrapState state) {
		ConfigurationImpl configurationImpl = new ConfigurationImpl( state );
		configurationImpl.failFast(true);
		return configurationImpl;
	}

	public ValidatorFactory buildValidatorFactory(ConfigurationState configurationState) {
		return new ValidatorFactoryImpl( configurationState );
	}
}