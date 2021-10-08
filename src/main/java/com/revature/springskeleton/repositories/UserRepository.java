package com.revature.springskeleton.repositories;

import com.revature.springskeleton.models.SiteUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long> {
    public default SiteUser findByUsername(String target) {
        SiteUser sample = new SiteUser();
        sample.setUsername(target);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("username")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

        Example<SiteUser> example = Example.of(sample, matcher);
        return (SiteUser) example;

    }
}
