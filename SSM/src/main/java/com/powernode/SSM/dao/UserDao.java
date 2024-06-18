package com.powernode.SSM.dao;

import com.powernode.SSM.bean.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from user where id=#{id}")
    User selectById(Integer id);
}
