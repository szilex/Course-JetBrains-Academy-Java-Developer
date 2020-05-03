import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String url = new Scanner(System.in).nextLine();

        String[] parameters = url.substring(url.indexOf("?") + 1).split("&");
        String passwordString = "";
        for (String parameter : parameters) {
            String[] values = parameter.split("=");
            if (values.length == 2) {
                System.out.printf("%s : %s\n", values[0], values[1]);
                if (values[0].equals("pass")) {
                    passwordString = String.format("password : %s", values[1]);
                }
            } else {
                System.out.printf("%s : not found\n", values[0]);
                if (values[0].equals("pass")) {
                    passwordString = "password : not found";
                }
            }
        }

        if(!passwordString.isEmpty()) {
            System.out.println(passwordString);
        }
    }
}