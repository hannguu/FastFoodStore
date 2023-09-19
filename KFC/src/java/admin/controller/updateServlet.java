/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller;

import dal.DishDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Dish;

/**
 *
 * @author Asus
 */@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,  // 1 MB
    maxFileSize = 1024 * 1024 * 10,  // 10 MB
    maxRequestSize = 1024 * 1024 * 50  // 50 MB
)
public class updateServlet extends HttpServlet {

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
            out.println("<title>Servlet updateServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateServlet at " + request.getContextPath() + "</h1>");
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

        try {
            int dish_id = Integer.parseInt(request.getParameter("dish_id"));
            DishDAO dishDAO = new DishDAO();
            Dish d = dishDAO.getDish(dish_id);
            
            request.setAttribute("dish", d);
            
            
            request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(updateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        int dish_id = Integer.parseInt(request.getParameter("dish_id"));
        String name = request.getParameter("name");
        String infor = request.getParameter("infor");
        int price = Integer.parseInt(request.getParameter("price"));
       Part part=request.getPart("image");
            String realPath = request.getServletContext().getRealPath("/images");
            String image = Paths.get(part.getSubmittedFileName()).getFileName().toString();
           part.write(realPath + "/" + image);
        String comment = request.getParameter("comment");
        String typeOfDish = request.getParameter("typeOfDish");

       

        DishDAO dishDAO = new DishDAO();
        dishDAO.update(dish_id, name, infor, price, image, comment, typeOfDish);

       

        response.sendRedirect("ListController");
    } catch (NumberFormatException ex) {
        // Xử lý lỗi số học (không thể chuyển đổi thành số).
        response.sendRedirect("error.jsp");
    } catch (SQLException ex) {
        // Xử lý lỗi truy vấn cơ sở dữ liệu.
        response.sendRedirect("error.jsp");
        Logger.getLogger(updateServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        // Xử lý lỗi khác (nếu có).
        response.sendRedirect("error.jsp");
        Logger.getLogger(updateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>

}
