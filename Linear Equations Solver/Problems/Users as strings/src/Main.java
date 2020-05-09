class User {

    private String login;
    private String firstName;
    private String lastName;

    public User(String login, String firstName, String lastName) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("login=%s,firstName=%s,lastName=%s", login, firstName, lastName);
    }
}