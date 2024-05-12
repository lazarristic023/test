package com.example.Project.Service;

import com.example.Project.Model.Request;
import com.example.Project.Model.RequestStatus;
import com.example.Project.Repository.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    public RequestRepo requestRepo;

   public List<Request> getAllRequests(){
       return requestRepo.findAll();
   }

   public Request create(Request newRequest){
       return requestRepo.save(newRequest);
   }

   public Request getByClientId(Long clientId){
       return requestRepo.findRequestsByClientId(clientId);
   }

}
