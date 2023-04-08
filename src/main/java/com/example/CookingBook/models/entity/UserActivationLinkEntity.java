package com.example.CookingBook.models.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class UserActivationLinkEntity extends BaseEntity {

  @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private UserEntity user;

  @Column
  private String confirmationToken;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  public UserEntity getUser() {
    return user;
  }

  public UserActivationLinkEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public UserActivationLinkEntity() {}

  public UserActivationLinkEntity(UserEntity userEntity) {
    this.user = userEntity;
    createdDate = new Date();
    confirmationToken = UUID.randomUUID().toString();
  }

  public String getConfirmationToken() {
    return confirmationToken;
  }

  public UserActivationLinkEntity setConfirmationToken(String confirmationToken) {
    this.confirmationToken = confirmationToken;
    return this;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public UserActivationLinkEntity setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
    return this;
  }
}
