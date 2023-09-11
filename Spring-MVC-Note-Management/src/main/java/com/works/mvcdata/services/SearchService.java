package com.works.mvcdata.services;

import com.works.mvcdata.props.Note;
import com.works.mvcdata.utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
    public List<Note> search(String q) {
        List<Note> ls = new ArrayList<>();

        DB db = new DB();
        try {
            String sql = "select *from  note where  title like  ? or detail like ? ";
            PreparedStatement pre = db.connect().prepareStatement(sql);

            pre.setString(1, "%" + q + '%');
            pre.setString(2, '%' + q + '%');

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Note u = new Note();

                u.setId(rs.getInt("id"));
                u.setTitle(rs.getString("title"));
                u.setDetail(rs.getString("detail"));
                ls.add(u);
            }


        } catch (Exception ex) {
            System.err.println("Note Error : " + ex);

        } finally {
            db.close();
        }
        return ls;
    }

    public int totalCount() {
        int count = 0;
        DB db = new DB();
        try {
            String sql = "select  count(id) as count from note ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count");

            }

        } catch (Exception ex) {
            System.err.println("Pagination Error : " + ex);

        } finally {
            db.close();
        }
        return count;
    }


}
