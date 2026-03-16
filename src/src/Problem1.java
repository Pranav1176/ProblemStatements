import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Problem1 {

    // Stores username -> userId
    private HashMap<String, Integer> users = new HashMap<>();

    // Stores username -> attempt frequency
    private HashMap<String, Integer> attempts = new HashMap<>();

    // Check username availability
    public boolean checkAvailability(String username) {

        // Increase attempt count
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        // Check if username exists
        return !users.containsKey(username);
    }

    // Register new user
    public void registerUser(String username, int userId) {

        if (checkAvailability(username)) {
            users.put(username, userId);
            System.out.println(username + " registered successfully.");
        } else {
            System.out.println("Username already taken.");
        }
    }

    // Suggest alternative usernames
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        // Append numbers
        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;

            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        // Replace underscore with dot
        String modified = username.replace("_", ".");
        if (!users.containsKey(modified)) {
            suggestions.add(modified);
        }

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {

        String mostAttempted = null;
        int maxAttempts = 0;

        for (Map.Entry<String, Integer> entry : attempts.entrySet()) {

            if (entry.getValue() > maxAttempts) {
                maxAttempts = entry.getValue();
                mostAttempted = entry.getKey();
            }
        }

        return mostAttempted + " (" + maxAttempts + " attempts)";
    }

    // Main method to test system
    public static void main(String[] args) {

        Problem1 system = new Problem1();

        // Pre-existing users
        system.registerUser("john_doe", 101);
        system.registerUser("alice123", 102);

        // Availability checks
        System.out.println("Check john_doe: " + system.checkAvailability("john_doe"));
        System.out.println("Check jane_smith: " + system.checkAvailability("jane_smith"));

        // Suggestions
        System.out.println("Suggestions for john_doe: " + system.suggestAlternatives("john_doe"));

        // Simulate many attempts
        for (int i = 0; i < 10543; i++) {
            system.checkAvailability("admin");
        }

        // Most attempted username
        System.out.println("Most attempted: " + system.getMostAttempted());
    }
}