package com.example.Project.Service;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Dto.CompanyDto;
import com.example.Project.Dto.EmployeeDto;
import com.example.Project.Model.Client;
import com.example.Project.Model.Company;
import com.example.Project.Model.Employee;
import com.example.Project.Repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    public List<CompanyDto> getAll() {
        try {
            List<Company> companies = companyRepo.findAll();
            List<CompanyDto> companyDTOs = new ArrayList<>();

            for (Company company : companies) {
                CompanyDto companyDTO = new CompanyDto();
                companyDTO.setName(company.getName());
                companyDTO.setAddress(company.getAddress());
                // Mapiranje ostalih atributa

                List<ClientDto> clientDTOs = new ArrayList<>();
                for (Client client : company.getClients()) {
                    ClientDto clientDTO = new ClientDto();
                    clientDTO.setEmail(client.getEmail());
                    clientDTO.setPassword(client.getPassword());
                    clientDTO.setCity(client.getCity());
                    clientDTO.setRole(String.valueOf(client.getRole()));
                    clientDTO.setCountry(client.getCountry());
                    clientDTO.setClientFirmName(client.getClientFirmName());
                    clientDTO.setUsername(client.getUsername());
                    clientDTO.setPhone(client.getPhone());
                    clientDTO.setType(String.valueOf(client.getType()));
                    clientDTO.setClientFirmResidentialAddress(client.getClientFirmResidentialAddress());
                    clientDTO.setClientSurnameFirmPIB(client.getClientSurnameFirmPIB());

                    clientDTOs.add(clientDTO);
                }
                companyDTO.setClients(clientDTOs);

                // Dodaj zaposlenike za ovu kompaniju
                List<EmployeeDto> employeeDTOs = new ArrayList<>();
                for (Employee employee : company.getEmployees()) {
                    EmployeeDto employeeDTO = new EmployeeDto();
                    employeeDTO.setUsername(employee.getUsername());
                    employeeDTO.setEmail(employee.getEmail());
                    employeeDTO.setPassword(employee.getPassword());
                    employeeDTO.setRole(String.valueOf(employee.getRole()));
                    employeeDTO.setFirstName(employee.getFirstName());
                    employeeDTO.setLastName(employee.getLastName());
                    employeeDTO.setCity(employee.getCity());
                    employeeDTO.setCountry(employee.getCountry());
                    employeeDTO.setPhone(employee.getPhone());

                    employeeDTOs.add(employeeDTO);
                }
                companyDTO.setEmployees(employeeDTOs);

                companyDTOs.add(companyDTO);
            }

            logger.info("EventID: 5000 | Date: {} | Time: {} | Source: CompanyService | Type: INFO | Message: Successfully retrieved all companies",
                    java.time.LocalDate.now(), java.time.LocalTime.now());

            return companyDTOs;
        } catch (Exception e) {
            logger.error("EventID: 5000 | Date: {} | Time: {} | Source: CompanyService | Type: ERROR | Message: Error retrieving all companies | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }
}
