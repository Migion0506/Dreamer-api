package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.UserEntity;
import org.beru.dreammer.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService{
private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity userEntity = this.userRepository.findByUsernameOrEmail(username, username)
                    .orElseThrow(() -> new RuntimeException(
                            String.format("Username: %s not found", username)));
            return User
                    .builder()
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .accountLocked(false)
                    .disabled(false)
                    .build();
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getLocalizedMessage());
        }
    }
}
