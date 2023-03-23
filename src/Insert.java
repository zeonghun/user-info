import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert {
    /**
     * Insert
     * 
     * @author zeonghun
     * @since 230323
     */
    public void insert() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. 새로운 사용자 추가");
        System.out.println("2. 기존 사용자 전화번호 추가");
        System.out.println();
        System.out.print("항목 선택: ");
        int num = sc.nextInt();
        System.out.println();
        if (num == 1) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try (
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                PreparedStatement stmt = con.prepareStatement("INSERT INTO user(id, name, birth, address, job) VALUES(?, ?, ?, ?, ?)");) {
                // User user = new User();
                System.out.print("추가할 아이디: ");
                String id = sc.next();
                // user.setId(sc.next());
                System.out.print("추가할 이름: ");
                String name = sc.next();
                System.out.print("추가할 생년월일: "); // TODO: yyyy-MM-dd
                String birth = sc.next();
                System.out.print("추가할 주소: ");
                sc.nextLine();
                String address = sc.nextLine();
                System.out.print("추가할 직업: ");
                String job = sc.next();
                // user <-- id, name

                // parameter 설정
                stmt.setString(1, id);
                // stmt.setString(1, user.getId());
                stmt.setString(2, name);
                stmt.setString(3, birth); // TODO: Date type으로 insert
                stmt.setString(4, address);
                stmt.setString(5, job);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (num == 2) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try (
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                    PreparedStatement stmt = con.prepareStatement("INSERT INTO phone(user_id, phone_number ) VALUES(?, ?)");) {
                System.out.print("번호를 추가할 아이디: ");
                String user_id = sc.next();
                System.out.print("추가할 전화번호(-포함): "); // TODO: XXX-XXXX-XXXX
                String phone_number = sc.next();
                // parameter 설정
                stmt.setString(1, user_id);
                stmt.setString(2, phone_number);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("1~2중에서 입력하세요!");
    }
}