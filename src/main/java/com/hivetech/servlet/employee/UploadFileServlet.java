package com.hivetech.servlet.employee;

import com.hivetech.model.Employee;
import com.hivetech.service.implement.EmployeeServiceImpl;
import com.hivetech.service.implement.UploadFileExcelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/employee/UploadFileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class UploadFileServlet extends HttpServlet {
//    private static final int
    private static final Logger LOGGER = Logger.getLogger(UploadFileServlet.class.getName());

    private UploadFileExcelService uploadFileExcelService;

    public void init() {
        uploadFileExcelService = new UploadFileExcelService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        for (Part part: req.getParts()) {
            uploadFileExcelService.uploadEmployee(part);
        }
        req.setAttribute("message", "Upload File Success!");
        getServletContext().getRequestDispatcher("/views/uploadFile.jsp").forward(req, resp);
        LOGGER.info(String.format("Method: %s | action: %s ", req.getMethod(), req.getRequestURI()));

    }

    private File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Upload");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("upload");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
