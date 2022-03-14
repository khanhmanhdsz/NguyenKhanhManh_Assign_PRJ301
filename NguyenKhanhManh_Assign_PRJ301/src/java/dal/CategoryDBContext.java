/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

public class CategoryDBContext extends DBContext{ //thao tác với bảng category

    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        try {
            String sql = "select * from Category";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Category category = new Category();
                 category.setCid(rs.getInt(1));
                 category.setCname(rs.getString(2));
                list.add(category);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
