package sujalmandal.torncityservicesclub.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.constants.AppConstants;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.repositories.PlayerRepository;
import sujalmandal.torncityservicesclub.services.TornAPIService;

@Component
@Slf4j
public class FingerprintAuthenticationFilter extends OncePerRequestFilter {

    private List<String> publicURIs = Arrays.asList(AppConstants.PUBLIC_API_PREFIXES.toString().split(","));

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private TornAPIService tornService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {

	log.info("incoming request for {} type {}", request.getServletPath(), request.getMethod());
	if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
	    log.info("ignoring authentication for preflight request.");
	    filterChain.doFilter(request, response);
	    return;
	}

	String fingerprint = request.getHeader(AppConstants.FINGERPRINT_HEADER.toString());
	String apiKey = request.getHeader(AppConstants.API_KEY_HEADER.toString());

	Optional<Player> player = null;
	boolean isAuthenticationPassed = true;
	String errorMessage = null;
	if (StringUtils.isNotEmpty(fingerprint) && !fingerprint.equals("undefined") && !fingerprint.equals("null")) {
	    log.info("authenticated player using fingerprint verification.");
	    player = playerRepo.findByFingerprint(fingerprint);
	    if (player.isEmpty()) {
		isAuthenticationPassed = false;
		errorMessage = String.format("Authentication failed with fingerprint: {%s}!", fingerprint);
	    }
	} else if (StringUtils.isNotEmpty(apiKey) && !apiKey.equals("undefined") && !apiKey.equals("null")) {
	    log.warn("using API key to authenticate player.");
	    player = tornService.getPlayer(apiKey);
	    if (player == null) {
		isAuthenticationPassed = false;
		errorMessage = String.format("Authentication with torn failed. Please check your torn API key.");
	    }
	    log.info("authenticated player using fingerprint verification.");
	} else {
	    isAuthenticationPassed = false;
	    errorMessage = String.format("Neither fingerprint nor apiKey passed in the header.");
	}

	if (isAuthenticationPassed) {
	    // set the authentication
	    AuthenticatedUser.setPlayer(player.get());
	    filterChain.doFilter(request, response);
	} else {
	    response.sendError(HttpStatus.FORBIDDEN.value(), errorMessage);
	    log.error(errorMessage);
	    return;
	}

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	return publicURIs.stream().anyMatch(prefix -> {
	    return request.getServletPath().startsWith(prefix);
	});
    }

}