package it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem;

public class User {

    private String email;
    private String encryptedPassword;
    private String username;

    public User(String email, String encryptedPassword, String username) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
