import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCompanies = scanner.nextInt();

        int[] incomes = new int[numberOfCompanies];
        int[] taxes = new int[numberOfCompanies];

        for(int i = 0; i < numberOfCompanies; i++ ) {
            incomes[i] = scanner.nextInt();
        }

        for(int i = 0; i < numberOfCompanies; i++ ) {
            taxes[i] = scanner.nextInt();
        }

        int maxTax = 0;
        int indexOfCompany = 0;
        for(int i = 0; i < numberOfCompanies; i++) {
            int tax = incomes[i] * taxes[i];
            if(tax > maxTax) {
                maxTax = tax;
                indexOfCompany = i;
            }
        }

        System.out.println(indexOfCompany+1);
    }
}