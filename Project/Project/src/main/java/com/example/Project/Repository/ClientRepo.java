package com.example.Project.Repository;

import com.example.Project.Model.Client;
import com.example.Project.Model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.clientFirmName = :clientFirmName WHERE c.id = :id")
    void updateClientFirmNameById(Long id, String clientFirmName);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.clientSurnameFirmPIB = :clientSurnameFirmPIB WHERE c.id = :id")
    void updateClientSurnameFirmPIBById(Long id, String clientSurnameFirmPIB);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.clientFirmResidentialAddress = :clientFirmResidentialAddress WHERE c.id = :id")
    void updateClientFirmResidentialAddressById(Long id, String clientFirmResidentialAddress);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.city = :city WHERE c.id = :id")
    void updateCityById(Long id, String city);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.country = :country WHERE c.id = :id")
    void updateCountryById(Long id, String country);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.phone = :phone WHERE c.id = :id")
    void updatePhoneById(Long id, String phone);

    @Query("SELECT c FROM Client c WHERE c.email = ?1")
    Client findByEmail(String email);
}
