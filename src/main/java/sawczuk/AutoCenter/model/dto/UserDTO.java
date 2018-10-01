package sawczuk.AutoCenter.model.dto;

public class UserDTO {
    private String username;
    private String password;
    private String currentPassword;
    private String email;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getEmail() {
        return email;
    }
}
