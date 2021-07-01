package org.fmm.common.oauth2;

import org.fmm.common.oauth2.config.AppProperties;
import org.fmm.common.oauth2.config.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ResourceUtils;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
@PropertySource(value=ResourceUtils.CLASSPATH_URL_PREFIX
    + "lib-security.yaml", ignoreResourceNotFound=false, factory = YamlPropertySourceFactory.class)
public class OAuth2SecurityLib {

//	public static void main(String[] args) {
//		SpringApplication.run(OAuth2SecurityLib.class, args);
//	}

}
