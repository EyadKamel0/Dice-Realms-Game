import java.util.Scanner;

public class basel_lesson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Estimation or Tarneeb?");
        String gameName = scanner.nextLine();
        Boolean Flag = false;
        while (Flag == false) {
            if (gameName.equals("Estimation") || gameName.equals("Tarneeb")) {
                Flag = true;
            }
            else{
                System.out.println("Please enter a valid game");
                gameName=scanner.nextLine();
            }
        }
        System.out.println(gameName);

    }
    public int[] tafneet(int[] x){
        
     return x;
    }
}

