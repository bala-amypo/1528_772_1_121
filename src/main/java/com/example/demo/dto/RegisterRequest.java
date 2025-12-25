package com.example.demo.dto;

public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String role;

    public RegisterRequest() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final RegisterRequest r = new RegisterRequest();

        public Builder name(String name) { r.setName(name); return this; }
        public Builder email(String email) { r.setEmail(email); return this; }
        public Builder password(String password) { r.setPassword(password); return this; }
        public Builder role(String role) { r.setRole(role); return this; }

        public RegisterRequest build() { return r; }
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
