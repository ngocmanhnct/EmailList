package murach.data;

import java.sql.*;
import murach.business.User;

public class UserDB {
    
    // 1. Thay đổi URL kết nối (cổng mặc định của Postgres là 5432)
    private static final String DB_URL = "jdbc:postgresql://dpg-d4k09qm3jp1c738gen50-a:5432/murach_email_list";
    
    // 2. Thay đổi User/Pass của PostgreSQL cài trên máy bạn
    private static final String USER = "ltweb_user"; // Mặc định thường là postgres
    private static final String PASS = "VZcuMQTrnePaaCtIsNMr2rzmhiksPp5h";   // Mật khẩu bạn đã đặt lúc cài PostgreSQL

    public static int insert(User user) {
        Connection conn = null;
        PreparedStatement ps = null;

        // 3. Sửa tên bảng thành "users" (khớp với Bước 1)
        String query = "INSERT INTO UserTest (FirstName, LastName, EmailAddress) " +
                       "VALUES (?, ?, ?)";
        try {
            // Nạp Driver (tùy chọn với JDBC mới, nhưng nên giữ cho chắc)
            Class.forName("org.postgresql.Driver");
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            
            return ps.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi DB: " + e.getMessage());
            e.printStackTrace(); // In chi tiết lỗi để dễ sửa
            return 0;
        } finally {
            // Đóng kết nối
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}