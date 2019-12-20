package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Others.Connect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

@WebServlet("/BrochureDownload")
public class BrochureDownload extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int BUFFER_SIZE = 4096;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null; // connection to the database
        int id = Integer.parseInt(request.getParameter("id"));

        System.out.println(id);
        try {

            // connects to the database
        	String sql = "select * from WORKSHOP where workshop_id=?";
    		conn = Connect.getConnection();
    		PreparedStatement statement;
    		
    		statement = conn.prepareStatement(sql);
    		statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                // gets file name and file blob data
                String fileName = result.getString("title");
                Blob blob = result.getBlob("pdf");
                InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();

                System.out.println("fileLength = " + fileLength);

                ServletContext context = getServletContext();

                // sets MIME type for the file download
                String mimeType = context.getMimeType(fileName);
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                // set content properties and header attributes for the response
                response.setContentType(mimeType);
                response.setContentLength(fileLength);
                response.setContentType("application/pdf");
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
                response.setHeader(headerKey, headerValue);

                // writes the file to the client
                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outStream.close();
            } else {
                // no file found
                response.getWriter().print("File not found for the id: " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.getWriter().print("SQL Error: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            response.getWriter().print("IO Error: " + ex.getMessage());
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }}



