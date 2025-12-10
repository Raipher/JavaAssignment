package Assignment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class PasswordResetService {
    private final Map<String, String> tokenToUsername = new ConcurrentHashMap<>();
    private final Map<String, Long> tokenExpiry = new ConcurrentHashMap<>();
    private final Map<String, Long> requestWindowStart = new ConcurrentHashMap<>();
    private final Map<String, Integer> requestCounts = new ConcurrentHashMap<>();

    private final long TOKEN_TTL_MS = 15 * 60 * 1000L; // 15 minutes
    private final long WINDOW_MS = 60 * 60 * 1000L; // 60 minutes
    private final int MAX_REQUESTS_PER_WINDOW = 3;

    public PasswordResetService() {
        
    }

    public synchronized String requestResetForExistingUser(String username) {
        if (isRateLimited(username)) {
            return null;
        }

        String token = generateToken();
        long expiry = System.currentTimeMillis() + TOKEN_TTL_MS;
        tokenToUsername.put(token, username);
        tokenExpiry.put(token, expiry);
        incrementCount(username);
        return token;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private boolean isRateLimited(String username) {
        long now = System.currentTimeMillis();
        Long windowStart = requestWindowStart.get(username);
        if (windowStart == null || now - windowStart >= WINDOW_MS) {
            // start new window
            requestWindowStart.put(username, now);
            requestCounts.put(username, 0);
            return false;
        } else {
            int count = requestCounts.getOrDefault(username, 0);
            return count >= MAX_REQUESTS_PER_WINDOW;
        }
    }

    private void incrementCount(String username) {
        long now = System.currentTimeMillis();
        Long windowStart = requestWindowStart.get(username);
        if (windowStart == null || now - windowStart >= WINDOW_MS) {
            requestWindowStart.put(username, now);
            requestCounts.put(username, 1);
        } else {
            requestCounts.put(username, requestCounts.getOrDefault(username, 0) + 1);
        }
    }

    public synchronized String getUsernameForToken(String token) {
        if (token == null) return null;
        Long expiry = tokenExpiry.get(token);
        if (expiry == null || System.currentTimeMillis() > expiry) {
            // expired or not found
            tokenToUsername.remove(token);
            tokenExpiry.remove(token);
            return null;
        }
        return tokenToUsername.get(token);
    }

    public synchronized boolean consumeToken(String token) {
        String username = getUsernameForToken(token);
        if (username == null) return false;
        tokenToUsername.remove(token);
        tokenExpiry.remove(token);
        return true;
    }

    public Long getExpiryForToken(String token) {
        return tokenExpiry.get(token);
    }
}
