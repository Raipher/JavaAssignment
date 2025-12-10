package Assignment;

import java.util.Map;

public class UserManager {
        private Map<String, User> usersByUsername;
        private UserFileRepository userRepo;
        private int nextUserId;

        public UserManager(Map<String, User> usersByUsername, UserFileRepository userRepo) {
            this.usersByUsername = usersByUsername;
            this.userRepo = userRepo;
            this.nextUserId = computeInitialNextUserId();
        }
        
        private int computeInitialNextUserId() {
            int maxId = 0;
            if (usersByUsername != null) {
                for (User u : usersByUsername.values()) {
                    if (u != null) {
                        int id = u.getUserId();
                        if (id > maxId) maxId = id;
                    }
                }
            }
            return maxId + 1;
        }

        private void save() {
            userRepo.saveUsers(usersByUsername);
        }
        
        public boolean usernameExists(String username) {
            return username != null && usersByUsername.containsKey(username);
        }
        
        public User getUser(String username) {
            if (username == null) return null;
            return usersByUsername.get(username);
        }

        public synchronized boolean addUser(User newUser) {
            if (newUser == null) return false;

        // 检查用户名是否已存在
            if (usersByUsername.containsKey(newUser.getUsername())) {
                return false;
            }

        // 分配用户ID
            if (newUser.getUserId() <= 0) {
                newUser.setUserId(nextUserId++);
            } else {
                 if (newUser.getUserId() >= nextUserId) {
                    nextUserId = newUser.getUserId() + 1;
                }
            }

            usersByUsername.put(newUser.getUsername(), newUser);
            save();
            return true;
        }

        public synchronized boolean updateUser(User updatedUser) {
            if (updatedUser == null) return false;

            if (!usersByUsername.containsKey(updatedUser.getUsername())) {
                return false;
            }

            if (updatedUser.getUserId() >= nextUserId) {
                nextUserId = updatedUser.getUserId() + 1;
            }

            usersByUsername.put(updatedUser.getUsername(), updatedUser);
            save();
            return true;
        }

        public synchronized boolean deactivateUser(String username) {
            User user = usersByUsername.get(username);
            if (user == null) {
                return false;
            }

            user.deactivate();
            save();
            return true;
        }

        public Map<String, User> getAllUsers() {
            return usersByUsername;
        }

        public synchronized int getNextUserId() {
            return nextUserId;
        }
    }
