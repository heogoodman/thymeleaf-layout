package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.UserInfo;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserInfoRepository extends ListCrudRepository<UserInfo, Integer> {
    Optional<UserInfo> findByEmail(String email);
}
