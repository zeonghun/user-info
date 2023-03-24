import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * INSERT, UPDATE, DELETE, READ
 * 
 * @author zeonghun
 * @since 230324
 */
public class Crud {

    private final String sqlConnection = "jdbc:mysql://127.0.0.1:3306/user_info";

    /**
     * Insert
     * 
     * @author zeonghun
     * @since 230324
     */
    public void insert() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("1. 새로운 사용자 추가");
        System.out.println("2. 기존 사용자 전화번호 추가");
        System.out.println();
        System.out.print("항목 선택: ");
        int index = sc.nextInt();
        System.out.println();

        // 새로운 사용자 추가
        if (index == 1) {
            System.out.print("추가할 아이디: ");
            user.setId(sc.next());
            System.out.print("추가할 이름: ");
            sc.nextLine();
            user.setName(sc.nextLine());
            System.out.print("추가할 생년월일: "); // TODO: yyyy-MM-dd
            user.setBirth(sc.nextLine());
            System.out.print("추가할 주소: ");
            // sc.nextLine();
            user.setAddress(sc.nextLine());
            if (user.getAddress().equals("") || user.getAddress().equals(" ")) {
                user.setAddress("null");
            }
            System.out.print("추가할 직업: ");
            user.setJob(sc.nextLine());
            if (user.getJob().equals("") || user.getJob().equals(" ")) {
                user.setJob("null");
            }

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try (
                Connection con = DriverManager.getConnection(sqlConnection, "root", "1234");
                PreparedStatement stmt = con.prepareStatement("INSERT INTO user(id, name, birth, address, job) VALUES(?, ?, ?, ?, ?)");) {
                // parameter 설정
                stmt.setString(1, user.getId());
                stmt.setString(2, user.getName());
                stmt.setString(3, user.getBirth()); // TODO: Date type으로 insert
                stmt.setString(4, user.getAddress());
                stmt.setString(5, user.getJob());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        // 기존 사용자 전화번호 추가
        } else if (index == 2) {
            System.out.print("번호를 추가할 아이디: ");
            user.setId(sc.next());
            System.out.print("추가할 전화번호: ");
            sc.nextLine();
            String phoneNumber = sc.nextLine();
            phoneNumber = PhoneNumberChange(phoneNumber);

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try (
                Connection con = DriverManager.getConnection(sqlConnection, "root","1234");
                PreparedStatement stmt = con.prepareStatement("INSERT INTO phone(user_id, phone_number ) VALUES(?, ?)");) {
                // parameter 설정
                stmt.setString(1, user.getId());
                stmt.setString(2, phoneNumber);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("1~2중에서 입력하세요!");
        }
    }

    /**
     * Update
     * 
     * @author zeonghun
     * @since 230324
     */
    public void update() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("1. 이름 수정");
        System.out.println("2. 생년월일 수정");
        System.out.println("3. 주소 수정");
        System.out.println("4. 직업 수정");
        System.out.println();
        System.out.print("항목 선택: ");
        int index = sc.nextInt();
        System.out.println();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 이름 수정
        if (index == 1) {
            System.out.print("이름을 변경할 아이디: ");
            user.setId(sc.next());
            System.out.print("변경할 이름: ");
            sc.nextLine();
            user.setName(sc.nextLine());

            try (
                Connection con = DriverManager.getConnection(sqlConnection, "root", "1234");
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET name = ? WHERE id = ?");) {
                // parameter 설정
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        // 생년월일 수정
        } else if (index == 2) {
            System.out.print("생년월일을 변경할 아이디: ");
            user.setId(sc.next());
            System.out.print("변경할 생년월일: ");
            sc.nextLine();
            user.setBirth(sc.nextLine());

            try (
                Connection con = DriverManager.getConnection(sqlConnection, "root", "1234");
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET birth = ? WHERE id = ?");) {
                // parameter 설정
                stmt.setString(1, user.getBirth());
                stmt.setString(2, user.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        // 주소 수정
        } else if (index == 3) {
            System.out.print("주소를 변경할 아이디: ");
            user.setId(sc.next());
            System.out.print("변경할 주소: ");
            sc.nextLine();
            user.setAddress(sc.nextLine());
            if (user.getAddress().equals("") || user.getAddress().equals(" ")) {
                user.setAddress(null);
            }

            try (
                Connection con = DriverManager.getConnection(sqlConnection, "root", "1234");
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET address = ? WHERE id = ?");) {
                // parameter 설정
                stmt.setString(1, user.getAddress());
                stmt.setString(2, user.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        // 직업 수정
        } else if (index == 4) {
            System.out.print("직업을 변경할 아이디: ");
            user.setId(sc.next());
            System.out.print("변경할 직업: ");
            sc.nextLine();
            user.setJob(sc.nextLine());
            if (user.getJob().equals("") || user.getJob().equals(" ")) {
                user.setJob(null);
            }

            try (
                Connection con = DriverManager.getConnection(sqlConnection, "root", "1234");
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET job = ? WHERE id = ?");) {
                // parameter 설정
                stmt.setString(1, user.getJob());
                stmt.setString(2, user.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("1~4중에서 입력하세요!");
        }
    }

    /**
     * Delete
     * 
     * @author zeonghun
     * @since 230324
     */
    public void delete() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("1. 사용자 아이디 삭제");
        System.out.println("2. 전화번호만 삭제");
        System.out.println();
        System.out.print("항목 선택: ");
        int index = sc.nextInt();
        System.out.println();

        // 사용자 아이디 삭제
        if (index == 1) {
            System.out.print("삭제할 아이디: ");
            user.setId(sc.next());

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try (
                Connection con = DriverManager.getConnection(sqlConnection, "root", "1234");
                PreparedStatement stmt1 = con.prepareStatement("DELETE FROM phone WHERE user_id = ?");
                PreparedStatement stmt2 = con.prepareStatement("DELETE FROM user WHERE id = ?");) {
                // parameter 설정
                stmt1.setString(1, user.getId());
                stmt1.executeUpdate();
                stmt2.setString(1, user.getId());
                stmt2.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        // 전화번호만 삭제
        } else if (index == 2) {
            System.out.print("삭제할 전화번호: ");
            sc.nextLine();
            String phoneNumber = sc.nextLine();
            phoneNumber = PhoneNumberChange(phoneNumber);
            
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try (
                Connection con = DriverManager.getConnection(sqlConnection, "root", "1234");
                PreparedStatement stmt = con.prepareStatement("DELETE FROM phone WHERE phone_number = ?");) {
                // parameter 설정
                stmt.setString(1, phoneNumber);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("1~2중에서 입력하세요!");
        }
    }

    /**
     * Read
     * 
     * @author zeonghun
     * @since 230324
     */
    public void read() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(sqlConnection, "root", "1234");
                // 실행할 Query 설정
                PreparedStatement stmt1 = con.prepareStatement("SELECT * FROM user ORDER BY name ASC");
                PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM phone");
                // Query 실행
                ResultSet rs1 = stmt1.executeQuery();
                ResultSet rs2 = stmt2.executeQuery();) {
            System.out.println(
                    "======================================== User Table ========================================");
            while (rs1.next()) {
                System.out.println("아이디: " + rs1.getString("id") + " / 이름: " + rs1.getString("name") + " / 생년월일: "
                        + rs1.getString("birth") + " / 주소: " + rs1.getString("address") + " / 직업: "
                        + rs1.getString("job"));
            }
            System.out.println(
                    "============================================================================================");
            System.out.println();
            System.out.println();
            System.out.println(
                    "======================================= Phone Table ========================================");
            while (rs2.next()) {
                System.out.println("아이디: " + rs2.getString("user_id") + " / 전화번호: " + rs2.getString("phone_number"));
            }
            System.out.println(
                    "============================================================================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 입력받은 전화번호에서 특정 문자를 제거하는 메소드
     * 
     * @param phoneNumber 입력받은 전화번호
     * @return 특정 문자를 제거한 전화번호
     * 
     * @author zeonghun
     * @since 230324
     */
    public String PhoneNumberChange(String phoneNumber) {
        if (phoneNumber.contains("-")) {
            phoneNumber = phoneNumber.replace("-", "");
        } else if (phoneNumber.contains(".")) {
            phoneNumber = phoneNumber.replace(".", "");
        } else if (phoneNumber.contains("/")) {
            phoneNumber = phoneNumber.replace("/", "");
        }
        return phoneNumber;
    }
}