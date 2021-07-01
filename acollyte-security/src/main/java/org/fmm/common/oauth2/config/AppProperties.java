package org.fmm.common.oauth2.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppProperties.class)
@ConfigurationProperties(prefix = "app")
public class AppProperties {
	private final Auth auth = new Auth();
	private final OAuth2 oauth2 = new OAuth2();
	private final JWTProperties jwtProperties = new JWTProperties();
	
	private Map<String,JWTP> providerPropertiesByKid = null;
//	private Map<String,JWTP> providerPropertiesByPub = null;
	@PostConstruct
	public void processProperties() {
		JWTP aux = null;
		if (jwtProperties != null) {
			providerPropertiesByKid = new HashMap<>(jwtProperties.providers.size());
//			providerPropertiesByPub = new HashMap<>(jwtProperties.providers.size());
			for (String key:jwtProperties.providers.keySet()) {
				aux = jwtProperties.providers.get(key);
				providerPropertiesByKid.put(aux.getKid(), aux);
//				providerPropertiesByPub.put(aux.getAlias(), aux);
//				System.out.println(aux.getKid() + "->" + aux.getAlias());
//				System.out.println(aux.getAlias() + "->" + aux.getKid());
			}
		}
	}
	
	public static class Auth {
		private String tokenSecret;
		private long tokenExpirationMsec;
		
		public long getTokenExpirationMsec() {
			return tokenExpirationMsec;
		}

		public void setTokenExpirationMsec(long tokenExpirationMsec) {
			this.tokenExpirationMsec = tokenExpirationMsec;
		}

		public String getTokenSecret() {
			return tokenSecret;
		}
		
		public void setTokenSecret(String tokenSecret) {
			this.tokenSecret = tokenSecret;
		}
	}
	
	public static final class OAuth2 {
		private List<String> authorizedRedirectUris = new ArrayList<>();

		public List<String> getAuthorizedRedirectUris() {
			return authorizedRedirectUris;
		}
		
		public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
			this.authorizedRedirectUris = authorizedRedirectUris;
			return this;
		}
	}

	public static class JWTProperties {
		private String file;
		private String password;
		private String alias;
		
		private Map<String, JWTP> providers;
		
		public String getFile() {
			return file;
		}

		public void setFile(String file) {
			this.file = file;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public Map<String, JWTP> getProviders() {
			return providers;
		}

		public void setProviders(Map<String, JWTP> aliasProveedores) {
			this.providers = aliasProveedores;
		}
	}
	
	public static class JWTP {
		private String alias;
		private String keystore;
		private String file;
		private String kid;
		private String password;
		
		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public String getFile() {
			return file;
		}

		public void setFile(String file) {
			this.file = file;
		}

		public String getKid() {
			return kid;
		}

		public void setKid(String kid) {
			this.kid = kid;
		}

		public String getKeystore() {
			return keystore;
		}

		public void setKeystore(String keystore) {
			this.keystore = keystore;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
	
	public Auth getAuth() {
		return auth;
	}

	public OAuth2 getOauth2() {
		return oauth2;
	}

	public JWTProperties getJwtProperties() {
		return jwtProperties;
	}

	public Map<String, JWTP> getProviderPropertiesByKid() {
		return providerPropertiesByKid;
	}

//	public Map<String, JWTP> getProviderPropertiesByPub() {
//		return providerPropertiesByPub;
//	}

}
