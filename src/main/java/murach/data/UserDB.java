package murach.data;

import java.sql.*;
import murach.business.User;

public class UserDB {

    // 1. URL chuẩn JDBC kết nối tới Render (đã thêm sslmode=require)
    private static final String DB_URL = "jdbc:postgresql://dpg-d4k09qm3jp1c738gen50-a.oregon-postgres.render.com:5432/ltweb?sslmode=require";
    
    // 2. Tên đăng nhập
    private static final String USER = "ltweb_user";
    
    // 3. Mật khẩu
    private static final String PASS = "VZcuMQTrnePaaCtIsNMr2rzmhiksPp5h";

    public static int insert(User user) {
        Connection conn = null;
        PreparedStatement ps = null;

        // LƯU Ý: Tên bảng là "UserTest", cột là "EmailAddress" (theo như bạn cung cấp ở trên)
        // Nếu database trên Render bạn chưa tạo bảng này thì sẽ lỗi.
        // Nếu tên bảng trên Render là "users" thì sửa UserTest -> users
        String query = "INSERT INTO UserTest (FirstName, LastName, EmailAddress) VALUES (?, ?, ?)";
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            
            return ps.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi kết nối Render: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("LỖI KẾT NỐI DB CHI TIẾT: " + e.getMessage());
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