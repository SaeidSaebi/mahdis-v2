public class User {
    public User(String username, String password) {
    }

    class User {
        String username;
        String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public boolean login(String inputUsername, String inputPassword) {
            return this.username.equals(inputUsername) && this.password.equals(inputPassword);
        }
    }
}
