package com.property.manager.authen;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import com.property.manager.User;
import com.property.manager.mysqlmanager.MySQLManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service(value = "customAuth")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	private static MySQLManager sqlManager;
	/**
	 * Authenticate the user that logs in using the credentials from the html page and reading from the DB.
	 *
	 * @param authentication
	 * 		Spring Security authentication class
	 * @return
	 * 		A valid authentication if the user is found with the correct credentials.
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String loginName = authentication.getName();

		String password = authentication.getCredentials().toString();

		// Get loginName from the DB
		String username = MySQLManager.get().findUser(loginName);
		String userRole = MySQLManager.get().getUserRole();
		String userId = MySQLManager.get().getUserID();

		User user = new User(username, userRole, userId);

		if (user.getId() != null) {

			try {
				String generatedSecuredPasswordHash = PasswordHash.get().generateStorngPasswordHash(password);

				boolean matched = PasswordHash.get().validatePassword(password, user.getPassword());

				if (user != null) {

					if (loginName.equals(user.getUsername()) && matched) {

						// It is very important ot pass the List of Granted Authority to the
						// UsernamePasswordAuthenticationTokens so it can properly send the
						// WebSecurity authenticator the credentials and let the user in the app.
						List<GrantedAuthority> grantedAuths = new ArrayList<>();

						grantedAuths.add(new SimpleGrantedAuthority(userRole));

						LOGGER.info("Successful authentication of " + loginName);

						// It is necessary to pass in only the username, not the whole user object, because each time
						// the user is updated in couchDB, the objects revision number increments, so in order to update
						// the user object consistently, the endpoints have to read the directly from the DB and not from
						// this authenticator.
						return new UsernamePasswordAuthenticationToken(user.getUsername(), password, grantedAuths);
					}
				}

			} catch (NoSuchAlgorithmException | InvalidKeySpecException she) {

				LOGGER.error("Could not find algorithm", she);
			}
		}
		LOGGER.info("Unsuccessful authentication.");

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}