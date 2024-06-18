package com.powernode.SSM.service.impl;

import com.powernode.SSM.bean.User;
import com.powernode.SSM.dao.UserDao;
import com.powernode.SSM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getById(Integer id) {
        return userDao.selectById(id);
    }
}
