package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.*;

public interface UserRepository extends ListCrudRepository<UserEntity, String>{
    UserEntity findByEmail(String email);
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
