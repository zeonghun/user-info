import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    /**
     * (non-javadoc)
     * 
     * @author zeonghun
     * @since 230324
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Crud crud = new Crud();

        int index;

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

                index = sc.nextInt();

                System.out.println();
                switch (index) {
                    case 1:
                        crud.insert();
                        break;
                    case 2:
                        crud.update();
                        break;
                    case 3:
                        crud.delete();
                        break;
                    case 4:
                        crud.read();
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
                index = 1;
            }
        } while (index != 5);
        sc.close();
    }
}