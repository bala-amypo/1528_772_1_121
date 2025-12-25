package com.example.demo.dto;

public class AuthRequest {

    private String email;
    private String password;

    public AuthRequest() {}

    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final AuthRequest r = new AuthRequest();

        public Builder email(String email) {
            r.setEmail(email);
            return this;
        }

        public Builder password(String password) {
            r.setPassword(password);
            return this;
        }

        public AuthRequest build() {
            return r;
        }
    }
}
