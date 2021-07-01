package org.fmm.common.oauth2.security;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	    Jwt jwt = null;
	    OidcIdToken oidcIdToken = null;
	    OidcUser oidcUser = null;
	    OAuth2AuthenticationToken token = null;
		try {
			String jwtValue = getJwtFromRequest(request);
			
            
//            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			if (StringUtils.hasText(jwtValue)) {
			    jwt = TokenProvider.generateOauthJwtToken(jwtValue);
			    
			    //				Long userId = tokenProvider.getUserIdFromToken(jwt);
//				UserDetails userDetails = customUserDetailsService.loadUserById(userId);
				
//				String userName = tokenProvider.getUserNameFromToken(jwt);
//				UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

				UserDetails userDetails = UserPrincipal.create(jwt, null);;

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception ex) {
			logger.error("Could not set user authentication in security context", ex);
		}
		
		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		
		return null;
	}
	
//	private OidcUser generateOidcUser(String jwtValue) {
//        Jwt jwt = null;
//        OidcIdToken oidcIdToken = null;
//	    OidcUser oidcUser = null; 
//	    jwt = TokenProvider.generateOauthJwtToken(jwtValue);
//	    oidcIdToken = new OidcIdToken(jwtValue, jwt.getIssuedAt(), jwt.getExpiresAt(), jwt.getClaims());
//	    oidcUser = new DefaultOidcUser(, oidcIdToken);
//	           
//	    return oidcUser;
//	}
//	
//	private static List<GrantedAuthority> generateGrants() {
//       List<GrantedAuthority> authorities = Collections.
//                singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//       
////       Set<GrantedAuthority> authorities = new LinkedHashSet<>();
//       authorities.add(new OidcUserAuthority(userRequest.getIdToken(), userInfo));
//       OAuth2AccessToken token = userRequest.getAccessToken();
//       for (String authority : token.getScopes()) {
//           authorities.add(new SimpleGrantedAuthority("SCOPE_" + authority));
//       }
//
//       
//       
//       return authorities;
//	}
}
