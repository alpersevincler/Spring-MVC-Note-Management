package com.works.mvcdata.services;

import com.works.mvcdata.props.User;
import com.works.mvcdata.utils.DB;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class UserService {
    DB db = new DB();

    public User loginUser(User user) {
        User u = null;

        try {
            String sql = "select * from users where email =? and password=? ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, user.getEmail());
            pre.setString(2, user.getPassword());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setAge(rs.getInt("age"));
                u.setDate(rs.getString("date"));

            }

        } catch (Exception ex) {
            System.err.println("Login User ERROR :" + ex);

        } finally {
            db.close();
        }
        return u;

    }

    public User single(int uid) {
        User u = new User();
        try {
            String sql = "select*from users where uid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, uid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                u.setUid(rs.getInt("uid"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setAge(rs.getInt("age"));
                u.setDate(rs.getString("date"));

            }

        } catch (Exception ex) {
            System.err.println("User Info ERROR : " + ex);
        } finally {
            db.close();
        }
        return u;
    }


}
