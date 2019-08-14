package com.ravish.mypoll;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ravish.mypoll.security.CustomUserDetails;

@Configuration
@EnableJpaAuditing
public class MyPollSpringAuditConfiguration {
	
	@Bean
	public AuditorAware<Long> getAuditorProvider() {
		return new SpringAuditorAwareImpl();
	}

}

class SpringAuditorAwareImpl implements AuditorAware<Long>{

	@Override
	public Optional<Long> getCurrentAuditor() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || !authentication.isAuthenticated()
				|| authentication instanceof AnonymousAuthenticationToken) {
			return Optional.empty();
		}
		
		CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
		return Optional.of(principal.getId());
	}
	
}
