package org.fmm.common.oauth2.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;

public class UserPrincipal implements OidcUser, UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 371560060945584930L;
	private String id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;
	private Jwt jwt;
	
	public UserPrincipal() {
		super();
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	public static UserPrincipal create(Jwt jwt, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = new UserPrincipal();
		userPrincipal.setAttributes(attributes);
		userPrincipal.jwt = jwt;
		
		//process jwt
		userPrincipal.id = jwt.getSubject();
		userPrincipal.email = jwt.getClaim("email");
		Collection<SimpleGrantedAuthority> authorities = getAuthorities(jwt);
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		userPrincipal.authorities = authorities;
		return userPrincipal;
	}
	
	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	private void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return getUsername();
	}

	@Override
	public Map<String, Object> getClaims() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OidcIdToken getIdToken() {
		// TODO Auto-generated method stub
		return null;
	}

    @SuppressWarnings("unchecked")
    private static Collection<SimpleGrantedAuthority> getAuthorities(Jwt jwt) {
	        String claimName = "groups";

	        Object authorities = jwt.getClaim(claimName);
//	        if (authorities instanceof String) {
//	            if (StringUtils.hasText((String) authorities)) {
//	                return Arrays.asList(((String) authorities).split(" "));
//	            } else {
//	                return Collections.emptyList();
//	            }
//	        } else if (authorities instanceof Collection) {
	            return ((Collection<String>) authorities)
	                    .stream()
	                    .map((SimpleGrantedAuthority::new))
	                    .collect(Collectors.toList());
//	        }
//
//	        return Collections.emptyList();
	    }

    public Jwt getJwt() {
        return jwt;
    }

}
