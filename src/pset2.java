package ProblemStream.src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class pset2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file: ");
        String path = scanner.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> list = new ArrayList<>();
			String line = br.readLine();

			while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
        }
        System.out.print("Enter a salary: ");
        double salary = scanner.nextDouble();

        List<String> emails = list.stream()
                .filter(x -> x.getSalary() > salary)
                .map(x -> x.getEmail())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Email of people whose salary is more than: " + String.format("%.2f", salary));

        emails.forEach(System.out::println);

        double sum = list.stream()
                .filter(x -> x.getName().charAt(0) == 'M')
                .map(x -> x.getSalary())
                .reduce(0.0, (x,y) -> x + y);
            System.out.println();
        System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));

        List<Double> names = list.stream()
                .filter(x -> x.getName().charAt(0) == 'A')
                .map(x -> x.getSalary())
                .sorted()
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Emails of people name started with 'A': ");        
        names.forEach(System.out::println);

        System.out.print("Insert your salary: ");
        double founds = scanner.nextDouble();

        List<String> total = list.stream()
                .filter(x -> x.getSalary() > founds)
                .map(x -> x.getName())
                .sorted()
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("Name of people: ");
        total.forEach(System.out::println);
    }catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
        }
    scanner.close(); 
    }
}
