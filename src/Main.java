import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    /**
     * (non-javadoc)
     * 
     * @author zeonghun
     * @since 230323
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Insert insert = new Insert();
        Update update = new Update();
        Delete delete = new Delete();
        Read read = new Read();

        int num;

        do {
            try {
                System.out.println();
                System.out.println("1. Insert");
                System.out.println("2. Update");
                System.out.println("3. Delete");
                System.out.println("4. Read");
                System.out.println("5. Exit");
                System.out.println();
                System.out.print("항목 선택: ");

                num = sc.nextInt();

                System.out.println();
                switch (num) {
                    case 1:
                        insert.insert();
                        break;
                    case 2:
                        update.update();
                        break;
                    case 3:
                        delete.delete();
                        break;
                    case 4:
                        read.read();
                        break;
                    case 5:
                        System.out.println("종료합니다");
                        System.out.println();
                        break;
                    default:
                        System.out.println("1~5중에서 입력하세요!");
                }
            } catch (InputMismatchException ime) {
                System.err.println("숫자만 입력해주세요!");
                num = 1;
            }
        } while (num != 5);
        sc.close();
    }
}