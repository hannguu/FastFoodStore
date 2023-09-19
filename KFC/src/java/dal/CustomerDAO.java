/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import connectDB.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author Asus
 */
public class CustomerDAO extends DBContext {

    private final DBContext db;

    public CustomerDAO() throws Exception {
        db = new DBContext();
    }

    public void add(String username, String password, String email, String phoneNumber, String role) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Customer (username, password, email, phoneNumber, role) VALUES (?, ?, ?, ?, ?)";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phoneNumber);
            ps.setString(5, role);

            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<Customer> getAllCustomer() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT customer_id, username, password, email, phoneNumber, role FROM Customer";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            ArrayList<Customer> list = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setRole(rs.getString("role"));
                list.add(customer);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi ở đây nếu cần thiết, ví dụ: throw một ngoại lệ tùy chỉnh
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên trong khối finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }
    public Customer getCustomer(String username, String password) {
        
        try {
            String sql = "SELECT * FROM Customer WHERE username = ? AND password = ?;";
             Connection connection = null;
            
            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();

            while (rs.next()) {
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                
                
                // return lap tuc!
                return customer;
            }

            rs.close();
            ps.close();      
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public Customer getCustomerUserName(String username) {
        
        try {
            String sql = "SELECT * FROM Customer WHERE username = ? ;";
             Connection connection = null;
            
            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            

            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();

            while (rs.next()) {
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("username"));
              
                
                // return lap tuc!
                return customer;
            }

            rs.close();
            ps.close();      
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public Customer getCustomerEmail(String email) {
        
        try {
            String sql = "SELECT * FROM Customer WHERE email = ? ;";
             Connection connection = null;
            
            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            

            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();

            while (rs.next()) {
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("email"));
              
                
                // return lap tuc!
                return customer;
            }

            rs.close();
            ps.close();      
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
