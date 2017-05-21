package services;

import models.UserAuth;

import java.util.List;

public interface SecurityService {

    List<UserAuth> findAll();

}
