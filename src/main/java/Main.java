
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlatDataBase flatDataBase = FlatDataBase.getFlatDataBase();
        int input;
        while (true) {
            System.out.println("Enter 1 to output the table.");
            System.out.println("Enter 2 to search flat by parameter.");
            System.out.println("Enter 3 to add flat by parameter.");
            input = scanner.nextInt();
            if (input == 1) {
                flatDataBase.getDataOfTable();
                System.out.println("Enter 0 to exit. If you want to continue enter any other number.");
                input = scanner.nextInt();
                if (input == 0)
                    break;
            } else if (input == 2) {
                flatDataBase.findElementOfQuery();
                System.out.println("Enter 0 to exit. If you want to continue enter any other number.");
                input = scanner.nextInt();
                if (input == 0)
                    break;
            } else if (input == 3) {
                flatDataBase.addFlatToDataBase(scanner);
                System.out.println("Enter 0 to exit. If you want to continue enter any other number.");
                input = scanner.nextInt();
                if (input == 0)
                    break;
            }
        }
    }
}