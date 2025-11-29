package murach.email;

import java.io.IOException;
// Import các class Model và Database
import murach.business.User;
import murach.data.UserDB;

// Import thư viện Servlet (Jakarta EE 10)
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Định nghĩa URL cho Servlet
@WebServlet(urlPatterns = {"/emailList"})
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.jsp";
        
        // 1. Lấy hành động từ form (cái thẻ hidden input)
        String action = request.getParameter("action");
        
        // Debug: In ra xem action nhận được là gì
        System.out.println("--- SERVLET DEBUG ---");
        System.out.println("Action nhận được: " + action);

        if (action == null) {
            action = "join";  // mặc định
        }

        // 2. Nếu hành động là "add" thì mới lưu dữ liệu
        if (action.equals("add")) {
            // Lấy dữ liệu người dùng nhập
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            // Tạo đối tượng User
            User user = new User(firstName, lastName, email);
            
            // 3. GỌI DATABASE ĐỂ LƯU
            System.out.println("Đang gọi UserDB.insert()...");
            try {
                int ketQua = UserDB.insert(user);
                System.out.println("Kết quả Insert: " + ketQua + " (1 là thành công)");
            } catch (Exception e) {
                System.out.println("LỖI GỌI DB: " + e.getMessage());
                e.printStackTrace();
            }

            // Gửi user sang trang cảm ơn
            request.setAttribute("user", user);
            url = "/thanks.jsp"; 
        }
        
        System.out.println("---------------------");

        // Chuyển hướng trang
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}