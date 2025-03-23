/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author mitsu
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String action = request.getParameter("action");
            switch (action) {
            case "viewProducts":
                viewProducts(request, response);
                break;
            case "add":
                addProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                response.sendRedirect("productlist.jsp");
        }
        }
private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        // Lấy dữ liệu từ form
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");

        // Kiểm tra dữ liệu đầu vào có bị rỗng không
        if (code == null || description == null || priceStr == null || 
            code.trim().isEmpty() || description.trim().isEmpty() || priceStr.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required!");
            request.getRequestDispatcher("add.jsp").forward(request, response);
            return;
        }

        // Chuyển đổi giá trị price sang double (bắt lỗi nếu nhập sai định dạng)
        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price < 0) {
                throw new NumberFormatException("Price cannot be negative.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid price format!");
            request.getRequestDispatcher("add.jsp").forward(request, response);
            return;
        }

        // Tạo product (ID sẽ được tự động tạo trong database nếu dùng AUTO_INCREMENT)
        Product product = new Product(0, code, description, price);

        // Gọi DAO để thêm vào database
        boolean success = productDAO.createProduct(product);

        if (success) {
            response.sendRedirect("ProductController?action=viewProducts");
        } else {
            request.setAttribute("error", "Failed to add product!");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "An unexpected error occurred.");
        request.getRequestDispatcher("add.jsp").forward(request, response);
    }
}
  
private void viewProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var productList = productDAO.getAllProduct();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
    }
private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("id");
    System.out.println("Editing product with ID: " + id);  // Kiểm tra ID nhận vào

    if (id == null || id.isEmpty()) {
        System.out.println("ID is null or empty!");
        response.sendRedirect("ProductController?action=viewProducts");
        return;
    }

    Product product = productDAO.getProductById(id);
    if (product != null) {
        System.out.println("Product found: " + product.getCode());  // Kiểm tra sản phẩm có tồn tại
        request.setAttribute("product", product);
        request.getRequestDispatcher("update.jsp").forward(request, response);
        System.out.println("Forwarding to update.jsp");  // Xác nhận chuyển trang
    } else {
        System.out.println("Product not found, redirecting...");  // Nếu không tìm thấy, kiểm tra log
        response.sendRedirect("ProductController?action=viewProducts");
    }
}

private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(id, code, description, price);
        if (productDAO.updateProduct(product)) {
            response.sendRedirect("ProductController?action=viewProducts");
        } else {
            request.setAttribute("error", "Failed to update product!");
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }
    }
private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        // Lấy `id` từ request và kiểm tra tính hợp lệ
        String id = request.getParameter("id");

        // Thực hiện xóa sản phẩm
        boolean success = productDAO.deleteProduct(id);

        if (success) {
            response.sendRedirect("ProductController?action=viewProducts");
        } else {
            request.setAttribute("error", "Failed to delete product! Product may not exist.");
            request.getRequestDispatcher("productlist.jsp").forward(request, response);
        }
    } catch (NumberFormatException e) {
        request.setAttribute("error", "Invalid product ID format!");
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "An unexpected error occurred.");
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
    }
}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String action = request.getParameter("action");
    System.out.println("Action received: " + action);  // Kiểm tra action

    if ("edit".equals(action)) {
        editProduct(request, response);
    } else if ("viewProducts".equals(action)) {
        viewProducts(request, response);
    }else if("delete".equals(action)){
    deleteProduct(request, response);
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}