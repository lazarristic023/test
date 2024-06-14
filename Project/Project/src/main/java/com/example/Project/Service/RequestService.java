package com.example.Project.Service;

import com.example.Project.Model.Request;
import com.example.Project.Repository.RequestRepo;
import com.example.Project.Utilities.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    public RequestRepo requestRepo;

   public List<Request> getAllRequests() throws Exception {
       List<Request> retVal = new ArrayList<>();
       for(Request r: requestRepo.findAll()) {
           r.setUsername(AESUtil.decrypt(r.getUsername()));
           retVal.add(r);
       }
       return retVal;
   }

   public Request create(Request newRequest) throws Exception {
       newRequest.setUsername(AESUtil.encrypt(newRequest.getUsername()));
       return requestRepo.save(newRequest);
   }

    public Request getByClientId(String username) throws Exception {

       return requestRepo.findRequestsByUsername(AESUtil.encrypt(username));
    }

}
