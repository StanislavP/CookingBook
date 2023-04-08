package com.example.CookingBook.repository;

import com.example.CookingBook.models.entity.UserActivationLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserActivationLinkRepository extends JpaRepository<UserActivationLinkEntity,Long> {

    UserActivationLinkEntity findByConfirmationToken(String activationLink);

}
