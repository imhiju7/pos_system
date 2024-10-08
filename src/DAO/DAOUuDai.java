/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Hieu PC
 */
public class DAOUuDai {
    
    public DTOUuDai getud(DTOUuDai i) throws SQLException, ParseException{
        Connection con = Connect.connection();
        String sql = "SELECT * FROM uudai WHERE maUuDai = ?";
        PreparedStatement pst =  con.prepareStatement(sql);
        pst.setInt(1, i.getMaUuDai());
        ResultSet rs = pst.executeQuery();
        DTOUuDai ud = new DTOUuDai();
        while(rs.next()){
            ud.setMaUuDai(rs.getInt("maUuDai"));
            ud.setMocUuDai(rs.getInt("mocUuDai"));
            ud.setTiLeGiam(rs.getInt("tiLeGiam"));
            ud.setIsHidden(rs.getInt("isDelete"));
        }
        con.close();
        return ud;
    }
    public ArrayList<DTOUuDai> getlist() throws SQLException, ParseException{
        Connection con = Connect.connection();
        String sql = "SELECT * FROM uudai WHERE isDelete = 0";
        PreparedStatement pst =  con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        ArrayList<DTOUuDai> list = new ArrayList<>();
        while(rs.next()){
            DTOUuDai ud = new DTOUuDai();
            ud.setMaUuDai(rs.getInt("maUuDai"));
            ud.setMocUuDai(rs.getInt("mocUuDai"));
            ud.setTiLeGiam(rs.getInt("tiLeGiam"));
            ud.setIsHidden(rs.getInt("isDelete"));
            list.add(ud);
        }
        con.close();
        Collections.sort(list, new Comparator<DTOUuDai>() {
            @Override
            public int compare(DTOUuDai person1, DTOUuDai person2) {
                return Integer.compare(person1.getMocUuDai(), person2.getMocUuDai());
            }
        });
        return list;
    }
    public int adduudai(DTOUuDai ud) throws SQLException{
        Connection con = Connect.connection();
        String sql = "INSERT INTO uudai(mocUuDai,tiLeGiam,isDelete) VALUES(?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1,ud.getMocUuDai() );
        pst.setInt(2, ud.getTiLeGiam());
        pst.setInt(3, ud.getIsHidden());
        int rowaffect = pst.executeUpdate();
        con.close();
        return rowaffect;
    }
    public int updateuudai(DTOUuDai ud) throws SQLException{
        Connection con = Connect.connection();
        String sql = "UPDATE uudai set mocUuDai = ?,tiLeGiam = ?,isDelete = ? WHERE maUuDai= ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ud.getMocUuDai());
        pst.setInt(2, ud.getTiLeGiam());
        pst.setInt(3, ud.getIsHidden());
        pst.setInt(4, ud.getMaUuDai());
        int rowaffect = pst.executeUpdate();
        con.close();
        return rowaffect;
    }
}
