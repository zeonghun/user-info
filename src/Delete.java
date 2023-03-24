import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    /**
     * Delete
     * 
     * @author zeonghun
     * @since 230323
     */
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. 사용자 아이디 삭제");
        System.out.println("2. 전화번호만 삭제");
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
                PreparedStatement stmt1 = con.prepareStatement("DELETE FROM phone WHERE user_id = ?");
                PreparedStatement stmt2 = con.prepareStatement("DELETE FROM user WHERE id = ?");) {
                System.out.print("삭제할 아이디: ");
                String id = sc.next();
                // parameter 설정
                stmt1.setString(1, id);
                stmt1.executeUpdate();
                stmt2.setString(1, id);
                stmt2.executeUpdate();
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
                PreparedStatement stmt = con.prepareStatement("DELETE FROM phone WHERE phone_number = ?");) {
                System.out.print("삭제할 전화번호: ");
                sc.nextLine();
                String phone_number = sc.nextLine();
                if(phone_number.contains("-")) {
                    phone_number = phone_number.replace("-","");
                } else if(phone_number.contains(".")){
                    phone_number = phone_number.replace(".","");
                } else if(phone_number.contains("/")){
                    phone_number = phone_number.replace("/","");
                } else if(phone_number.contains(".")){
                    phone_number = phone_number.replace(" ","");
                }
                // parameter 설정
                stmt.setString(1, phone_number);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("1~2중에서 입력하세요!");
    }
}