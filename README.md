# lushen-spring-boot-starter

Springboot JSR349定制

### 关于本项目

        本项目基于springboot-web的JSR349验证，进行定制化输出json格式验证错误信息，去除需要写BindResult到Controller方法参数的繁琐
        
### 如何使用

        1，导入项目到eclipse，打包为本地maven jar
        
        2，导入maven配置：
                
		<dependency>
			<groupId>org.lushen.zhuifeng</groupId>
			<artifactId>lushen-validator-spring-boot-starter</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
                
        3，classpath下新增validator.properties，如：
 
		user.login.username.null=用户名不能为空
		user.login.password.null=密码不能为空
        
        4，基于SpringBoot接口参数：
        
		public class VoLoginParameter implements VoParameter {

			@NotBlank(message="{user.login.username.null}")
			private String username;

			@NotBlank(message="{user.login.password.null}")
			private String password;

		}
                
        5，基于SpringBoot接口：
        
                @RequestMapping(value="/login")
                public String login(@Validated @ModelAttribute VoLoginParameter parameter) {
                        
                }
                
        6，验证不通过的json串例如：
        
                {"errcode":400,"msg":"请求参数错误","details":[{"msg":"密码不能为空","field":"password"}]}
                
                {"errcode":400,"msg":"请求参数错误","details":[{"msg":"用户名不能为空","field":"username"},
                                                              {"msg":"密码不能为空","field":"password"}]}
                                                              
### 可配置项

	lushen.validator.enabled                     //是否开启，默认true
	lushen.validator.errcode                     //验证不通过错误码，默认400
	lushen.validator.msg                         //验证不通过错误信息，默认‘请求参数错误’
	lushen.validator.encoding                    //ReloadableResourceBundleMessageSource配置
	lushen.validator.alwaysUseMessageFormat      //ReloadableResourceBundleMessageSource配置
	lushen.validator.basenames                   //ReloadableResourceBundleMessageSource配置
	lushen.validator.cacheMillis                 //ReloadableResourceBundleMessageSource配置
	lushen.validator.cacheSeconds                //ReloadableResourceBundleMessageSource配置
	lushen.validator.concurrentRefresh           //ReloadableResourceBundleMessageSource配置
	lushen.validator.fallbackToSystemLocale      //ReloadableResourceBundleMessageSource配置
	lushen.validator.useCodeAsDefaultMessage     //ReloadableResourceBundleMessageSource配置

### 关于验证信息

        除了使用properties文件以外，还可以实现ValidatorMessageLoader接口，并注册为Spring Bean，启动的时候会从中加载验证配置信息
        
