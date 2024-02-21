package com.fursah.BankSystem.service.admin;

import com.fursah.BankSystem.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface AdminService {
  public   List<UserEntity> getAllUsers();
}
