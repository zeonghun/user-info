import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {
    /**
     * Update
     * 
     * @author zeonghun
     * @since 230323
     */
    public void update() {
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("1. 이름 수정");
        System.out.println("2. 생년월일 수정");
        System.out.println("3. 주소 수정");
        System.out.println("4. 직업 수정");
        System.out.println();
        System.out.print("항목 선택: ");
        int num = sc.nextInt();
        System.out.println();
        if (num == 1) {
            try (
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET name = ? WHERE id = ?");) {
                System.out.print("이름을 변경할 아이디: ");
                String id = sc.next();
                System.out.print("변경할 이름: ");
                sc.nextLine();
                String name = sc.nextLine();
                // parameter 설정
                stmt.setString(1, name);
                stmt.setString(2, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (num == 2) {
            try (
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET birth = ? WHERE id = ?");) {
                System.out.print("생년월일을 변경할 아이디: ");
                String id = sc.next();
                System.out.print("변경할 생년월일: ");
                sc.nextLine();
                String birth = sc.nextLine();
                // parameter 설정
                stmt.setString(1, birth);
                stmt.setString(2, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (num == 3) {
            try (
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET address = ? WHERE id = ?");) {
                System.out.print("주소를 변경할 아이디: ");
                String id = sc.next();
                System.out.print("변경할 주소: ");
                sc.nextLine();
                String address = sc.nextLine();
                if (address.equals("")  || address.equals(" ")) {
                    address = "null";
                }
                // parameter 설정
                stmt.setString(1, address);
                stmt.setString(2, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (num == 4) {
            try (
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET job = ? WHERE id = ?");) {
                System.out.print("직업을 변경할 아이디: ");
                String id = sc.next();
                System.out.print("변경할 직업: ");
                sc.nextLine();
                String job = sc.nextLine();
                if (job.equals("")  || job.equals(" ")) {
                    job = "null";
                }
                // parameter 설정
                stmt.setString(1, job);
                stmt.setString(2, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("1~4중에서 입력하세요!");
    }
}