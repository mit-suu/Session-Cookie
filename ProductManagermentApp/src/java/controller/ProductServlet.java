package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Product;
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        ProductDAO prd = new ProductDAO();
        String url = "/productList.jsp";      
        if (action.equals("list")) {
            var products = prd.getAllProduct();
            request.setAttribute("products", products);
            
        } else if (action.equals("addnew")) {
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            String priceString = request.getParameter("price");

            String errorString = dataValidation(code, description, priceString);

            if (!errorString.isEmpty()) {
                url = "/addProduct.jsp";
                request.setAttribute("errorString", errorString);
            } else {
                double price = Double.parseDouble(priceString);
                Product product = new Product(code, description, price);
                prd.createProduct(product);
                var products = prd.getAllProduct();
                request.setAttribute("products", products);
            }
        }else if(action.equals("delete")){
            String id=request.getParameter("id");
            if (id != null && !id.isEmpty()) {
            prd.deleteProduct(id);
            }
            var products = prd.getAllProduct();
            request.setAttribute("products", products);
        }else if (action.equals("edit")) {
            String id = request.getParameter("id");
            Product product = prd.getProductById(id);
            url = "/editProduct.jsp";
            request.setAttribute("product", product);
}       else if(action.equals("update")){
        String id = request.getParameter("idP");
        Product productF=prd.getProductById(id);
        String code=request.getParameter("code");
        String description=request.getParameter("description");
        String priceString=request.getParameter("price");
        String errorString = dataValidation(code, description, priceString);
        
        if(!errorString.isEmpty()){
            url="/editProduct.jsp";
            request.setAttribute("product", productF);
            request.setAttribute("errorString", errorString);
        }else{
            double price =Double.parseDouble(priceString);
            Product product=new Product(code, description, price);
            product.setId(Integer.parseInt(id));
            prd.updateProduct(product);
            var products = prd.getAllProduct();
            request.setAttribute("products",products);
        }
        
}
        else {
            url = "/error.jsp"; // Xử lý khi action không hợp lệ
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    
    
    
    private String dataValidation(String code, String description, String priceString) {
        if (code.isEmpty() || description.isEmpty() || priceString.isEmpty()) {
            return "All fields are required.";
        }
        try {
            Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            return "Price must be a valid number.";
        }
        return "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}