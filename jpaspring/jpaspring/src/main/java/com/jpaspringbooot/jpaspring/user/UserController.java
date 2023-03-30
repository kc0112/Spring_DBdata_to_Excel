package com.jpaspringbooot.jpaspring.user;

import com.jpaspringbooot.jpaspring.excel.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ExcelService excelService;
    @RequestMapping("/excel")
    public ResponseEntity<InputStreamResource> download() throws IOException {
        String filename = "users.xlsx";

        ByteArrayInputStream actualData = excelService.getActualData();
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<InputStreamResource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
        return body;
    }
}
