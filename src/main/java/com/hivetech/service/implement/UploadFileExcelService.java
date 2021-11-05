package com.hivetech.service.implement;

import com.hivetech.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UploadFileExcelService {
    @Autowired
    EmployeeReponsitory repository;
    public void save(MultipartFile file) {
        try {
            List<Employee> tutorials = UploadFileExeclHelper.excelEmployees(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Employee> getAllTutorials() {
        return repository.finalAll();
    }
}

}
