/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import jdk.internal.net.http.websocket.Transport;

/**
 *
 * @author HAU
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("password_confirmation");
        boolean nameNull = isNull(username);
        boolean emailNull = isNull(email);
        boolean passNull = isNull(password);
        boolean pass2Null = isNull(confirmPassword);
        boolean nameValid = isValidUsername(username);
        boolean emailValid = isValidEmail(email);
        boolean passValid = isValidPassword(password);
        boolean pass2Valid = true;
        if (nameNull){
            if (!nameValid){
                request.setAttribute("tbTk", "Vui lòng nhập tài khoản từ 6 đến 16 kí tự");
            }
        } else {
            request.setAttribute("tbTk", "Vui lòng nhập tài khoản");
        }
//        ------------------------------------------
        if (emailNull){
            if (!emailValid){
               request.setAttribute("tbEmail", "Vui lòng nhập đúng định dạng Email"); 
            }
        }else {
            request.setAttribute("tbEmail", "Vui lòng nhập Email");
        }
//        ------------------------------------------
        if (passNull){
            if (!passValid){
               request.setAttribute("tbPass", "Vui lòng nhập mật khẩu có chữ và số, có từ 8 đến 24 kí tự"); 
            }
        }else {
            request.setAttribute("tbPass", "Vui lòng nhập mật khẩu");
        }
//        ------------------------------------------
        if (pass2Null){
            if (!confirmPassword.equalsIgnoreCase(password)){
               request.setAttribute("tbPass2", "Mật khẩu không khớp"); 
               pass2Valid = false;
            } else {
                pass2Valid = true;
            }
        }else {
            request.setAttribute("tbPass2", "Vui lòng nhập lại mật khẩu");
        }
//        ------------------------------------------
        if (nameNull && nameValid && emailNull && emailValid && passNull && passValid && pass2Null && pass2Valid){
            response.sendRedirect("home.jsp");
        } else {
           
            request.getRequestDispatcher("register.jsp").forward(request, response);
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("username", request.getParameter("username"));
            request.setAttribute("password", request.getParameter("password"));
        }
        
    }
    private boolean isNull(String text){
        if (text.equals("")){
        return false;
    } else
        return true;
    }
    // Hàm kiểm tra email hợp lệ
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches() && email.endsWith("gmail.com");
    }

    // Hàm kiểm tra tài khoản hợp lệ
    private boolean isValidUsername(String username) {
        return username.length() >= 6 && username.length() <= 16;
    }

    // Hàm kiểm tra mật khẩu hợp lệ
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{8,24}$";
        return password.matches(regex);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
