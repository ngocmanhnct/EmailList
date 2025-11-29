package murach.data;

import java.sql.*;
import murach.business.User;

public class UserDB {
    
    // Giữ nguyên thông tin kết nối DB của bạn (Render hoặc Local)
    private static final String DB_URL = "jdbc:postgresql://......?sslmode=require";
    private static final String USER = "......";
    private static final String PASS = "......";

    public static int insert(User user) {
        Connection conn = null;
        PreparedStatement ps = null;

        // --- SỬA QUAN TRỌNG TẠI ĐÂY ---
        // 1. Tên bảng: UserTest
        // 2. Tên cột: EmailAddress (thay vì Email)
        // 3. Thứ tự: FirstName, LastName, EmailAddress (cho giống SQL bạn gửi)
        String query = "INSERT INTO UserTest (FirstName, LastName, EmailAddress) " +
                       "VALUES (?, ?, ?)";
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            ps = conn.prepareStatement(query);
            
            // --- CẬP NHẬT THỨ TỰ DATA CHO KHỚP VỚI CÂU QUERY ---
            // Dấu ? thứ 1 là FirstName
            ps.setString(1, user.getFirstName());
            
            // Dấu ? thứ 2 là LastName
            ps.setString(2, user.getLastName());
            
            // Dấu ? thứ 3 là EmailAddress
            ps.setString(3, user.getEmail());
            
            return ps.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi Insert: " + e.getMessage());
            e.printStackTrace(); // In lỗi đầy đủ để dễ sửa
            return 0;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}