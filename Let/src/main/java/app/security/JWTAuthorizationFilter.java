package app.security;

import static app.security.SecurityConstants.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import app.repository.AvionRepository;
import app.utils.UtilsMethods;

/**
 * Autorizacioja sluzi da proveri validnost JSON Web Tokena koji se nalazi u
 * poslatom requestu
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private AvionRepository avionRepo;

	@Autowired
	public JWTAuthorizationFilter(AuthenticationManager authManager, AvionRepository avionRepo) {
		super(authManager);
		this.avionRepo = avionRepo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String token = req.getHeader(HEADER_STRING);

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req, token);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {

		if (token != null) {

			DecodedJWT jwt = null;

			if (token.startsWith("Basic ")) {

				// parsiranje tokena
				jwt = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build().verify(token.replace(TOKEN_PREFIX, ""));
				System.out.println("Token je: " + jwt);
				
				String email = jwt.getSubject();
				
				RestTemplate restTmp = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.add(HttpHeaders.AUTHORIZATION, token);
				HttpEntity<String> entity = new HttpEntity<String>(null, headers);
				ResponseEntity<Boolean> response = restTmp.exchange("http://localhost:8084/whoAmI", HttpMethod.GET,
						entity, Boolean.class);
				if (response.hasBody()) {
					if (response.getBody() == true) {
						return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
					} else {
						return null;
					}
				}
				else
				{
					return null;
				}
					

			} else {
				jwt = JWT.require(Algorithm.HMAC512(ADMIN_SECRET.getBytes())).build()
						.verify(token.replace(ADMIN_TOKEN_PREFIX, ""));
			}

			// subject je email od korisnika i spakovan je u JWT
			String email = jwt.getSubject();

			if (email != null) {
				return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}
