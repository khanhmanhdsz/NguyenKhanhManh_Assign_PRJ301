/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Admin
 */
public class AcountDBContext extends DBContext {

    public Account login(String user, String pass) {
        try {
            String sql = "SELECT * FROM Account where [user] = ? and pass = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUid(rs.getInt(1));
                a.setUser(rs.getString(2));
                a.setPass(rs.getString(3));
                a.setIsSell(rs.getInt(4));
                a.setIsAdmin(rs.getInt(5));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account checkAccountExist(String user) {
        try {
            String sql = "SELECT * FROM Account where [user] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUid(rs.getInt(1));
                a.setUser(rs.getString(2));
                a.setPass(rs.getString(3));
                a.setIsSell(rs.getInt(4));
                a.setIsAdmin(rs.getInt(5));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertAccount(String user ,String pass) {
        try {
            String sql = "INSERT INTO [dbo].[Account]\n"
                    + "           ([user]\n"
                    + "           ,[pass]\n"
                    + "           ,[isSell]\n"
                    + "           ,[isAdmin])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,0\n"
                    + "           ,0)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AcountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
