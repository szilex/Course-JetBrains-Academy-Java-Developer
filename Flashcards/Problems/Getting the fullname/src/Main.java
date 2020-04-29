class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName;
    }

    public String getFullName() {
        StringBuilder stringBuilder = new StringBuilder();
        if(firstName != null)
            stringBuilder.append(firstName);
        if(lastName != null){
            if(stringBuilder.length() > 0){
                stringBuilder.append(" ");
            }
            stringBuilder.append(lastName);
        }
        return stringBuilder.length() > 0 ? stringBuilder.toString() : "Unknown";
    }
}