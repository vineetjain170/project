package com.example.servingwebcontent;
import java.io.Serializable;

public class User implements Serializable{

    private String fullname;
    private String email;
    private String password;
    private String confirm_password;
  
    public String getFullname() {
      return fullname;
    }
  
    public void setFullname(String fullname) {
      this.fullname = fullname;
    }
  
    public String getEmail() {
      return email;
    }
  
    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
        return password;
      }
    
      public void setPassword(String password) {
        this.password = password;
      }
    
      public String getConfirm_password() {
        return confirm_password;
      }
    
      public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
      }
  
  }