package controller;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/PdfUpload")
@MultipartConfig(maxFileSize = 16177215)
public class PdfUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
   

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
        String sql="insert into FILES (file) values(?)";
        String url="jdbc:mysql://localhost:3306/community";
        String username="shrey";
        String pass="shrey";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,pass);

            PreparedStatement st=con.prepareStatement(sql);
           
            System.out.println("hi");
            InputStream inputStream = null;
             Part filePart = request.getPart("file1");
            
             if (filePart != null)
             {
                 System.out.println(filePart.getName());
                 System.out.println(filePart.getSize());
                 System.out.println(filePart.getContentType());
       
                 System.out.println("hi");
                 inputStream = filePart.getInputStream();
                 if (inputStream != null)
                 {
                     st.setBinaryStream(1, inputStream, (int) filePart.getSize());
                 }
                
                 System.out.println("hi");
                 int row = st.executeUpdate();
                 if (row > 0)
                 {
                        System.out.println("hi");
                    response.sendRedirect("pdf.jsp");
                 }
                 else
                 {

                     response.sendRedirect("pdf.jsp");
                 
                 }   
                
                
             }
           
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   
       
    }

}