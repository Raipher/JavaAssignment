package Assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UserFileRepository {
    private final Path filePath;

    public UserFileRepository(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();

        if (Files.notExists(filePath)) {
            return users;
        }

        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 7) continue;

                int userId = Integer.parseInt(parts[0]);
                String username = parts[1];
                String fullName = parts[2];
                String password = parts[3];
                String email = parts[4];
                String role = parts[5];
                boolean isActive = Boolean.parseBoolean(parts[6]);

                User u = new User(userId, username, fullName, password, email, role);
                if (!isActive) {
                    u.deactivate();
                }
                users.put(username, u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void saveUsers(Map<String, User> users) {
        try (BufferedWriter bw = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            for (User u : users.values()) {
                String line = u.getUserId() + ";" +
                              u.getUsername() + ";" +
                              u.getFullName() + ";" +
                              u.getPassword() + ";" +
                              u.getEmail() + ";" +
                              u.getRole() + ";" +
                              u.isActive();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}