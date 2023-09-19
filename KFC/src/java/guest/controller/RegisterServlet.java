package guest.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        try {

            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("password_confirmation");
            String phoneNumber = "";
            String role = "c";
            boolean nameNull = isNull(username);
            boolean emailNull = isNull(email);
            boolean passNull = isNull(password);
            boolean pass2Null = isNull(confirmPassword);
            boolean nameValid = isValidUsername(username);
            boolean emailValid = isValidEmail(email);
            boolean passValid = isValidPassword(password);
            boolean pass2Valid = true;
            boolean nameAdmin = isAdmin(username);
            CustomerDAO customerDAO = new CustomerDAO();
            if (nameNull) {
                if (nameValid) {
                    if (nameAdmin) {
                       
                            if (customerDAO.getCustomerUserName(username) != null) {
                              
                                request.setAttribute("tbTk", "Tài khoản đã tồn tại");
                                
                            }
                       
                    } else {
                        request.setAttribute("tbTk", "Vui lòng nhập tài khoản không chứa từ đặc biệt");
                    }
                } else {
                    request.setAttribute("tbTk", "Vui lòng nhập tài khoản từ 6 đến 16 kí tự");
                }
            } else {
                request.setAttribute("tbTk", "Vui lòng nhập tài khoản");
            }
//        ------------------------------------------
            if (emailNull) {
                if (emailValid) {
                    try {
                        if (customerDAO.getCustomerEmail(email)!= null) {
                            request.setAttribute("tbEmail", "Email đã tồn tại");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    request.setAttribute("tbEmail", "Vui lòng nhập đúng định dạng Email");
                }
            } else {
                request.setAttribute("tbEmail", "Vui lòng nhập Email");
            }
//        ------------------------------------------
            if (passNull) {
                if (!passValid) {
                    request.setAttribute("tbPass", "Vui lòng nhập mật khẩu có chữ và số, có từ 8 đến 24 kí tự");
                }
            } else {
                request.setAttribute("tbPass", "Vui lòng nhập mật khẩu");
            }
//        ------------------------------------------
            if (pass2Null) {
                if (!confirmPassword.equalsIgnoreCase(password)) {
                    request.setAttribute("tbPass2", "Mật khẩu không khớp");
                    pass2Valid = false;
                } else {
                    pass2Valid = true;
                }
            } else {
                request.setAttribute("tbPass2", "Vui lòng nhập lại mật khẩu");
            }
            try {
                //        ------------------------------------------
                if (nameNull && nameValid && emailNull && emailValid && passNull && passValid && pass2Null && pass2Valid
                        && nameAdmin && customerDAO.getCustomerUserName(username) == null && customerDAO.getCustomerEmail(email)==null) {
                    try {
                        CustomerDAO customer = new CustomerDAO();
                        customer.add(username, password, email, phoneNumber, role);
                    } catch (Exception ex) {
                        Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    response.sendRedirect("home.jsp");
                } else {

                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    request.setAttribute("email", request.getParameter("email"));
                    request.setAttribute("username", request.getParameter("username"));
                    request.setAttribute("password", request.getParameter("password"));
                }
            } catch (Exception ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isNull(String text) {
        if (text.equals("")) {
            return false;
        } else {
            return true;
        }
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
//    private boolean isDoubleUsername(String username) throws Exception{
//        CustomerDAO customer = new CustomerDAO();
//        ArrayList<Customer> List = customer.getAllCustomer();
//        for(Customer c:List){
//            if(username.equalsIgnoreCase(c.getUsername())){
//                return false;
//            }
//        }
//       return true;
//    }

//    private boolean isDoubleEmail(String email) throws Exception {
//        CustomerDAO customer = new CustomerDAO();
//        ArrayList<Customer> List = customer.getAllCustomer();
//        for (Customer list : List) {
//            if (email.equalsIgnoreCase(list.getEmail())) {
//                return false;
//            }
//        }
//        return true;
//    }

    private boolean isAdmin(String username) {
        if (username.contains("admin")) {
            return false;
        } else {
            return true;
        }
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
