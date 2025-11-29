package murach.email;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// Nếu vẫn lỗi thư viện thì dùng: import jakarta.servlet... (cho Tomcat 10)

@WebServlet(urlPatterns = {"/emailList"})
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.jsp";
        
        // Lấy dữ liệu từ form
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // hành động mặc định
        }

        if (action.equals("add")) {
            // Lấy thông tin người dùng nhập
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            // Ở đây thường sẽ lưu vào Database hoặc tạo class User (Model)
            // Để đơn giản, ta chỉ log ra console
            System.out.println("Email: " + email);
            System.out.println("Name: " + firstName + " " + lastName);
            
            // Chuyển hướng sang trang cảm ơn (bạn cần tạo thêm file thanks.jsp)
            // Hoặc gửi dữ liệu lại chính trang index để test
            request.setAttribute("userEmail", email);
            url = "/thanks.jsp"; 
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}