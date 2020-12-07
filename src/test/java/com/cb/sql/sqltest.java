package com.cb.sql;

import com.cb.dao.RoleDao;
import com.cb.dao.UserDao;
import com.cb.model.entity.Role;
import com.cb.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.List;

/**
 * @program: CommonBackground
 * @description:
 * @author:
 * @create: 2020-11-05 13:58
 **/

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class sqltest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    public void selectUserTest() {
        User newuser=new User();
        newuser.setUsername("testuser-bysqltest");
        newuser.setPassword("123456");
        newuser.setLoginIp("1.1.1.1");

        long id= userDao.insert(newuser);
        log.info(MessageFormat.format("Insert Finish id is {0},add {1}",id,newuser.getId()));

        User findeduser=userDao.findUserByName("testuser");
        log.info((MessageFormat.format("find user {0}",findeduser)));
        Role role=roleDao.findRoleByName("superadmin");
        List<Role> roles= roleDao.getAllRoles();
        log.info((MessageFormat.format("find role {0}",role)));
        userDao.addRoletoUser(findeduser.getId(), role.getId());
    }
}
