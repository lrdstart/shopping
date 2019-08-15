package com.shopping.service.impl;

import com.shopping.mapper.UsersMapper;
import com.shopping.model.Orders;
import com.shopping.model.Users;
import com.shopping.service.UserService;
import com.shopping.util.EncryptUtil;
import com.shopping.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServieImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users findOne(String userName, String password) {
        Users user = usersMapper.findOneByName(userName);
        if (user == null) {
            return null;
        }
        try {
            if (!user.getPassword().equals(EncryptUtil.getMD5Str(password))) {//和加密后的密码比对
                return null;
            }
        } catch (Exception e) {
            return null;//加密不了就请回吧
        }
        return user;
    }

    @Override
    public Users findByName(String uname) {
        Users user = null;
        try {
            user = usersMapper.findOneByName(uname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int registerUser(String uname, String password, String email) {
        int i = 0;
        if (usersMapper.findOneByName(uname) != null) {
            i = 3;
        } else {
            try {
                Users user = new Users();
                user.setUserName(uname);
                user.setPassword(EncryptUtil.getMD5Str(password));//密码加密
                user.setEmail(email);
                user.setGrade((byte) 2);
                user.setStatus((byte) 0);
                i = usersMapper.registerUser(user);
                //激活邮件发送
                String content = "<a href='http://localhost/user/active?username=" + uname + "&password=" + user.getPassword() + "'>点击激活【米柚商城账户】</a>";
                MailUtils.sendMail(user.getEmail(), content, "激活账户");
            } catch (Exception e) {
                i = 2;
            }
        }
        return i;
    }

    @Override
    public Orders findOrderOne(String openid, String orderId) {
        return null;
    }

    @Override
    public Orders cancelOrder(String openid, String orderId) {
        return null;
    }

    @Override
    public boolean active(String username, String password) {
        Users user = usersMapper.findOneByName(username);
        if (user.getPassword().equals(password)) {
            try {
                user.setStatus((byte) 1);
                usersMapper.updateByPrimaryKeySelective(user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void getBackPassword(String username, String email) {
        Users user = usersMapper.findOneByName(username);
        String password = null;
        try {
            password = "123456";
            user.setPassword(EncryptUtil.getMD5Str(password));
            usersMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String content = "尊敬的用户,您的密码为" + password;
        MailUtils.sendMail(user.getEmail(), content, "找回密码");
    }

    @Override
    public int changePassword(String username, String password, String newPassword) {
        Users user = usersMapper.findOneByName(username);
        int result=0;
        try {
            if(user.getPassword().equals(EncryptUtil.getMD5Str(password))){
                user.setPassword(EncryptUtil.getMD5Str(newPassword));
                //更改密码
                usersMapper.updateByPrimaryKeySelective(user);
                result=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
