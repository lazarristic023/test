package com.example.Project.Model;

import com.example.Project.Enum.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="admins")
@Getter
@Setter
public class Administrator extends User{
    private boolean isPredefined;
    private String firstName;
    private String lastName;
    private boolean isFirstLogging=true;

    public Administrator(String username, String email, String password, Role role, boolean isPredefined,String firstName,String lastName,String city,String country, String phone) {
        super(username, email, password, role,city,country,phone, false);
        this.isPredefined = isPredefined;
        this.firstName = firstName;
        this.lastName = lastName;

    }
    public Administrator(){}

    public boolean isPredefined() {
        return isPredefined;
    }

    public void setPredefined(boolean predefined) {
        isPredefined = predefined;
    }
}
