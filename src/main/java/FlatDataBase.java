import java.sql.*;
import java.util.Scanner;

public class FlatDataBase {
    final static String url = "jdbc:mysql://localhost:3306/FlatDataBase?useLegacyDatetimeCode=false&serverTimezone=Europe/Kiev";
    final static String login = "root";
    final static String password = "MySQL164897";
    private static FlatDataBase flatDataBase = new FlatDataBase();
    private Connection conn;
    {
        try {
            conn = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FlatDataBase() {
    }

    public static FlatDataBase getFlatDataBase() {
        return flatDataBase;
    }

    public void addFlatToDataBase(Scanner scanner) {
        System.out.println("Enter the district of the flat: ");
        String district = scanner.next();
        System.out.println("Enter the address of the flat: ");
        String address = scanner.next();
        System.out.println("Enter the square of the flat: ");
        String square = scanner.next();
        System.out.println("Enter the count of rooms of the flat: ");
        int countOfRooms = scanner.nextInt();
        System.out.println("Enter the price of the flat: ");
        int price = scanner.nextInt();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "insert into information(District, address, square, countOfRooms, price) values (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, district);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, square);
            preparedStatement.setInt(4, countOfRooms);
            preparedStatement.setInt(5, price);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getDataOfTable() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM information");
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    System.out.println(resultSetMetaData.getColumnName(i) + "\t\t");
                }
                System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    System.out.println(resultSet.getString(i) + "\t\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createSqlQuery() {
        Scanner scanner = new Scanner(System.in);
        String parameter = "";
        String valueOfParameter;
        System.out.println("Choose the parameter that will be searched (Available parametr):" +
                "\n1 - District\n2 - Address\n3 - Square\n4 - Count Of Rooms\n5 - Price");
        parameter = scanner.next();
        System.out.println("Enter your parameter: " + parameter);
        valueOfParameter = scanner.next();
        String query = "Select * From information where " + parameter + " = " + valueOfParameter;
        return query;
    }

    public void findElementOfQuery() {
        String query = createSqlQuery();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
            int iterator = 0;
            while (resultSet.next()) {
                iterator++;
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    System.out.println(resultSet.getString(i) + "\t\t");
                }
                System.out.println();
            }
            if (iterator == 0) {
                System.out.println("Sorry, you entered the wrong parameter. Please try again");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}