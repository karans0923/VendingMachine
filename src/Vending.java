import java.util.*;

public class Vending extends VendingService{
    public static void main(String[] args) {
        ArrayList<VendingItem> itemList = new ArrayList<>();
        Vending v = new Vending();
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        while(!quit){
            displayFunction();
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1 -> v.addItem();
                case 2 -> v.removeItem();
                case 3 -> v.dispense();
                case 4 -> v.updateItem();
                case 5 -> v.displayMenu();
                case 6 -> quit = true;
                default -> System.out.println("Choose an option between 1 to 6");
            }
        }

    }
    public static void displayFunction(){
        System.out.println("----------Welcome to Vending Machine----------");
        System.out.println("1. Add an Item.");
        System.out.println("2. Remove an Item.");
        System.out.println("3. Purchase an Item.");
        System.out.println("4. Update an Item.");
        System.out.println("5. Display Vending Menu.");
        System.out.println("6. Exit.");
        System.out.println("Please enter your choice below: ");
    }
}