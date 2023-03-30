package com.jpaspringbooot.jpaspring.excel;

import com.jpaspringbooot.jpaspring.jpa.UserRepository;
import com.jpaspringbooot.jpaspring.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private UserRepository userRepository;

    public ByteArrayInputStream getActualData() throws IOException {
        List<User>all = userRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = ExcelHelper.dataToExcel(all);
        return byteArrayInputStream;
    }
}
