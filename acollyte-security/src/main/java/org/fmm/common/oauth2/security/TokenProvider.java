package org.fmm.common.oauth2.security;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.nimbusds.jwt.SignedJWT;

@Service
public class TokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	
//	public String extractToken(Authentication authentication) {
//		OidcUser userPrincipal = (OidcUser) authentication.getPrincipal();
//		
//		Date now = new Date();
//		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
//		return userPrincipal.getIdToken().getTokenValue();
//	}
	
	public static SignedJWT parseToken(String authToken) throws ParseException {
		SignedJWT jwt = null;
		jwt = SignedJWT.parse(authToken);
		return jwt;
	}
	
	public static Jwt generateOauthJwtToken(String tokenValue) {
	    Jwt jwt = null;
	    final SignedJWT jwtToken;
	    
	    Consumer<Map<String, Object>> headerConsumer = null;
        Consumer<Map<String, Object>> claimsConsumer = null;
	    try {
	        jwtToken = parseToken(tokenValue);
	        
	        headerConsumer = (Map<String, Object> mapa) -> {
	            mapa.put("alg", jwtToken.getHeader().getAlgorithm());
	            mapa.put("kid", jwtToken.getHeader().getKeyID());
	            mapa.put("x5t", jwtToken.getHeader().getX509CertSHA256Thumbprint());
	        };
            claimsConsumer = (Map<String, Object> mapa) -> {
                try {
                    jwtToken.getJWTClaimsSet().getClaims().forEach((key, value) -> {
                        if (value instanceof Date)
                            mapa.put(key, ((Date)value).toInstant());
                        else
                            mapa.put(key, value);
                    });
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
            Date aux = jwtToken.getJWTClaimsSet().getNotBeforeTime();
            Instant auxInstant = aux.toInstant();
            Date aux2 = jwtToken.getJWTClaimsSet().getNotBeforeTime();
            Instant aux2Instant = aux2.toInstant();
            Date aux3 = jwtToken.getJWTClaimsSet().getNotBeforeTime();
            Instant aux3Instant = aux3.toInstant();
            jwt = Jwt.withTokenValue(tokenValue)
                .issuedAt(auxInstant)
                .expiresAt(aux2Instant)
                .notBefore(aux3Instant)
                .subject(jwtToken.getJWTClaimsSet().getSubject())
                .headers(headerConsumer)
                .claims(claimsConsumer)
                .build();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    return jwt;
	}
	
	public static void main(String[] args) {
        String tokenString = "eyJ4NXQiOiJPREkyWlRJME9EUmlZak0yTldJMk5XTTJNMkkxTkRJMU1qUTRORGszT0RBMlpEUTRZVFZrWlEiLCJraWQiOiJPREkyWlRJME9EUmlZak0yTldJMk5XTTJNMkkxTkRJMU1qUTRORGszT0RBMlpEUTRZVFZrWlEiLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiYWx3cEdSQ1Q3N0t2WjdNN3BBcTljZyIsInN1YiI6ImZlbGl4IiwiYW1yIjpbIkJhc2ljQXV0aGVudGljYXRvciJdLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJnaXZlbl9uYW1lIjoiRmVsaXggRmlzdCBOYW1lIiwibm9uY2UiOiJucFYzdU0wb0dZLWhsZEVzU2JyNkRkT2RqMU1lM1REYy00c1lzYVI2ZXVVIiwiYXVkIjoiVVA0OVpjdVhUckVxRWZqcXJVUFVsTUo1U3pRYSIsImNfaGFzaCI6ImJHOGIwTFFTLUdhTURsdVF4UVR2bXciLCJuYmYiOjE1OTExNzcwNTgsImF6cCI6IlVQNDlaY3VYVHJFcUVmanFyVVBVbE1KNVN6UWEiLCJuYW1lIjoiZmVsaXgiLCJleHAiOjE1OTExODA2NTgsImlhdCI6MTU5MTE3NzA1OCwiZmFtaWx5X25hbWUiOiJNZXJpbm8gTGFzdCBOYW1lIiwiZW1haWwiOiJmZWxpeC5tZXJpbm9Aa2V5bGFuZC5lcyJ9.R5cGFfyn5CGddn_J332xEtUSrruBYgVEbhQgO23Xuuo9QUaXwCkelxS07Dp76t6_nz9UQ8vXTqU-ZZ4RwACv3ay8-LEL_jINPrjtqqB2RuSfsGGaWzCQux7DcjZXOg-F4HWUv5oRKo7LBqA8Xeo2AuWi16bj9Jc5lE5j2SYGjhsyt1lOyi4p_zeQiPsucXnDRplGzNRPWn4ZgRc0UR43g47cvpIiYtFMGX5FzOtm9kwlxOSO_eVtybJAc89NfXNSNWLOjrrupESNAf_mgYU9FuvqoDjnkNogEui7WYa_X_JZR_WfqP7Tu-klXXtOYkxEkUWQBdnauKL8F2pHh6s2MQ";
        SignedJWT signed = null;
        Jwt jwt = null;
        
        try {
            signed = parseToken(tokenString);
            jwt = generateOauthJwtToken(tokenString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println();
    }
}
