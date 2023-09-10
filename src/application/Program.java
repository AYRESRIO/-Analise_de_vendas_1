package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;
import model.service.SaleService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}

			SaleService ss = new SaleService();

			Double sum = ss.filteredSum(list,
					p -> p.getSeller().charAt(0) == 'L' && (p.getMonth() == 1 || p.getMonth() == 7));

			list = list.stream().filter(x -> x.getYear() == 2016).sorted(new MyComparator()).limit(5)
					.collect(Collectors.toList());

			list.forEach(System.out::println);
			System.out.println();
			System.out.println("Total value sold by seller Logan in months 1 and 7: " + String.format("$ %.2f ", sum));

		}

		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}

}
