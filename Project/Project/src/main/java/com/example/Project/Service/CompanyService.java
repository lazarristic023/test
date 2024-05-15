package com.example.Project.Service;

import com.example.Project.Model.Company;
import com.example.Project.Repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    public List<Company> getAll(){
        return companyRepo.findAll();
    }
}
