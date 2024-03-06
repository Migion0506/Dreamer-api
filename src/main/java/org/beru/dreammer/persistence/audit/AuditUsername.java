package org.beru.dreammer.persistence.audit;

import java.util.Optional;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditUsername implements AuditorAware<String> {

    @SuppressAjWarnings("null")
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String username = authentication.getPrincipal().toString();
        return Optional.of(username);
    }

}
