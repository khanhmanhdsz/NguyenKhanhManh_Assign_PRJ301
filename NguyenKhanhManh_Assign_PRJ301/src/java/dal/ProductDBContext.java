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
import model.Product;

/**
 *
 * @author Le Hong Quan
 */
public class ProductDBContext extends DBContext{

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from product";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product where Product.cateID = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getProductsWithPagging(int page, int PAGE_SIZE) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select *  from Product order by id\n"
                    + "offset (?-1)*? row fetch next ? rows only";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getTotalProducts() {
        try {
            String sql = "select count(id)  from Product ";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Product> search(String keyword) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select *  from Product where name like ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getProductById(int productId) {
        try {
            String sql = "select *  from Product where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                return product;
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
