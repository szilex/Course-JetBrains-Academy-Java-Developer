import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Main {
    public static void main(String[] args){
        System.out.println("Hello, World!");

        String filePath = "C:\\Users\\szile\\Downloads\\dataset_91069.txt";
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine();
            //Map<Integer, Long> map = new TreeMap<>();
            int year = scanner.nextInt();
            int highestGainYear = year;
            String[] line = scanner.nextLine().trim().split(",");
            long population = 0;
            for(int i = 1; i <= 4; i++){
                population += Long.parseLong(line[line.length-i]) * Math.pow(10,3*(i-1));
            }
            long previousPopulation = population;
            long highestGain = 0;
            long highestGainPopulation = population;
            while(scanner.hasNext()) {
                year = scanner.nextInt();
                line = scanner.nextLine().trim().split(",");
                population = 0;
                for(int i = 1; i <= 4; i++){
                    population += Long.parseLong(line[line.length-i]) * Math.pow(10,3*(i-1));
                }
                if(population - previousPopulation > highestGain) {
                    highestGain = population - previousPopulation;
                    highestGainYear = year;
                    highestGainPopulation = population;
                }
                previousPopulation = population;
                //map.put(year,population);
            }
            System.out.println(highestGainYear + "  " + highestGainPopulation);

        } catch(FileNotFoundException e) {

        }
    }
}