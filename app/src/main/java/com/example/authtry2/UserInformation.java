package com.example.authtry2;

public class UserInformation {
    String Name,Email,Password,Username;

    public UserInformation() {
    }

    public UserInformation(String name, String email, String password, String username) {
        Name = name;
        Email = email;
        Password = password;
        Username = username;


    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public String getUsername()
    {
        return Username;
    }
    public void setUsername(String username)
    {
        Username = username;

    }
}
