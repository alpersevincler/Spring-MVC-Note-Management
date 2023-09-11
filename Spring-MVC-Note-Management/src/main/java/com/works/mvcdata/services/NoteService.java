package com.works.mvcdata.services;

import com.works.mvcdata.props.Note;
import com.works.mvcdata.utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoteService {
    DB db = new DB();
    Note note = new Note();

    public List<Note> note(int p) {
        List<Note> ls = new ArrayList<>();
        try {
            p = p - 1;
            p = p * 4;
            String sql = "select * from note order by id asc limit ?,4";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, p);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                //ÇOK ÖNEMLİ KESİNLİKLE U OLMALI ÇÜNKÜ DATALAR TABLODA GÖRÜNMELİ  Note note = new Note();
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setDetail(rs.getString("detail"));
                ls.add(note);

            }

        } catch (Exception ex) {
            System.err.println("User Note ERROR: " + ex);
        } finally {
            db.close();
        }
        return ls;


    }

    public int noteSave(Note note) {
        DB db = new DB();
        int status = 0;
        try {
            String sql = "insert into note values (null,?,?) order by id asc ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, note.getTitle());
            pre.setString(2, note.getDetail());
            status = pre.executeUpdate();

        } catch (Exception ex) {
            System.err.println("Note Save ERROR : " + ex);
        } finally {
            db.close();
        }
        return status;
    }

    public int totalCount() {
        int count = 0;
        try {
            String sql = "select count(id) as count from note ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }

        } catch (Exception ex) {
            System.err.println("Pagination ERROR : " + ex);

        } finally {
            db.close();
        }
        return count;
    }

    public int deleteNote(int id) {
        int status = 0;
        try {
            String sql = "delete from note where id=? ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, id);
            status = pre.executeUpdate();

        } catch (Exception ex) {
            System.err.println("Delete ERROR : " + ex);

        } finally {
            db.close();
        }
        return status;
    }

    public int noteUpdate(Note note) {
        int status = 0;

        try {
            String sql = "update note set  title=?, detail=?  where id=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, note.getTitle());
            pre.setString(2, note.getDetail());
            pre.setInt(3, note.getId());
            status = pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("Note Update : " + ex);
        } finally {
            db.close();
        }
        return status;
    }


}
