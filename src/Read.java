import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Read {
    /**
     * Read
     * 
     * @author zeonghun
     * @since 230323
     */
    public void read() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
            // 실행할 Query 설정
            PreparedStatement stmt1 = con.prepareStatement("SELECT * FROM user ORDER BY name ASC");
            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM phone");
            // Query 실행
            ResultSet rs1 = stmt1.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();) {
            System.out.println(
                    "======================================== User Table ========================================");
            while (rs1.next()) {
                System.out.println(String.format("아이디: %s / 이름: %s / 생년월일: %s / 주소: %s / 직업: %s",
                        rs1.getString("id"), rs1.getString("name"), rs1.getString("birth"), rs1.getString("address"), rs1.getString("job")));
            }
            System.out.println(
                    "============================================================================================");
            System.out.println();
            System.out.println();
            System.out.println(
                    "======================================= Phone Table ========================================");
            while (rs2.next()) {
                System.out.println(String.format("아이디: %s / 전화번호: %s",
                        rs2.getString("user_id"), rs2.getString("phone_number")));
            }
            System.out.println(
                    "============================================================================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}