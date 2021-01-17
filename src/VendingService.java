import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

abstract class VendingService implements  VendingMachine{
    Scanner in = new Scanner(System.in);
    ArrayList<VendingItem> items = new ArrayList<>();

    @Override
    public void addItem() {
        System.out.println("Please enter the name of the item: ");
        String name = in.nextLine();
        Random random = new Random();
        int id = generateId();
        System.out.println("Please enter the price of the item: ");
        double b = in.nextDouble();
        System.out.println("Please enter the quantity of the item: ");
        int q = in.nextInt();
        in.nextLine();
        VendingItem vendingItem = new VendingItem(name.toLowerCase(), id, b, q);
        if (searchItem(vendingItem.getName())) {
            System.out.println("Item already exists");
        } else {
            if (!(searchItem(vendingItem.getName())) && vendingItem.getQuantity() <= 20) {
                items.add(vendingItem);
                vendingItem.setQuantity(vendingItem.getQuantity());
                System.out.println("Item Added");
            } else {
                System.out.println("Item cannot be added");
            }
        }
    }

/*
    @Override
    public boolean removeItem(int itemId) {
        displayMenu();
        for(VendingItem m : items) {
            if (m.getId() == itemId) {
                items.remove(m);
                return true;
            }
        }
        return false;
    }
 */

    @Override
    public boolean removeItem() {
        if(!items.isEmpty()){
            displayMenu();
            System.out.println("Please enter the ID of the item you want to remove: ");
            int itemId = in.nextInt();
            for(VendingItem m : items) {
                if (m.getId() == itemId) {
                    items.remove(m);
                    return true;
                }
            }
        }
        else{
            System.out.println("No Items in the Vending Machine");
        }
        return false;
    }


    @Override
    public boolean searchItem(String name) {
        for (VendingItem m : items) {
            return m.getName().equalsIgnoreCase(name);
        }
        return false;
    }

   @Override
    public boolean updateItem() {
        displayMenu();
        System.out.println("Please enter the ID of the item you want to update: ");
        int oldId = in.nextInt();
        in.nextLine();
        for (VendingItem m : items) {
            if (m.getId()==oldId) {
                update(oldId, m.getName());
                return true;
            }
            /*
            else{
                continue;
            }

             */
        }
        return false;
    }

    @Override
    public int getItem(int itemId) {
        return itemId;
    }

    public int generateId(){
        Random random = new Random();
        int temp = random.nextInt(100);
        for(VendingItem m : items){
            if(m.getId() == temp){
                generateId();
            }
            else{
                break;
            }
        }
        return temp;
    }

    @Override
    public void dispense() {
        displayMenu();
        System.out.println("Please enter the Item ID of the item you wish to purchase: ");
        int tempId = in.nextInt();
        for (VendingItem m : items) {
            if (m.getId() == tempId) {
                if (m.getQuantity() > 0) {
                    System.out.println("Please pay " + m.getPrice() + " dollars.");
                    double pay = in.nextDouble();
                    in.nextLine();
                    if (calculateBalance(m.getPrice(), pay) >= 0 && pay >= 0) {
                        m.setQuantity(m.getQuantity() - 1);
                        System.out.println("Item Dispensed.");
                        System.out.println("Balance of " + calculateBalance(m.getPrice(), pay) + " has been dispensed.");
                    } else {
                        System.out.println("Insufficient Amount.");
                    }
                }
                else{
                    System.out.println("No items left.");
                }
                //continue;
            }
        }
    }

    public boolean update(int id, String name) {
        System.out.println("Please enter the price of the item: ");
        double b = in.nextDouble();
        System.out.println("Please enter the quantity of the item: ");
        int q = in.nextInt();
        in.nextLine();
        for (VendingItem m : items) {
            if (m.getId() == id) {
                m.setPrice(b);
                if ((m.getQuantity() + maxCapacity(m.getQuantity())) <= 20) {
                    m.setQuantity(q);
                    System.out.println("Item Updated.");
                    return true;
                }
                else{
                    System.out.println("Max quantity exceeded. Only "+maxCapacity(m.getQuantity())+" units of the item allowed.");
                    //continue;
                }
            }
        }
        return false;
    }
        /*
        VendingItem vendingItem = new VendingItem(name.toLowerCase(), id, b, q);
        if (searchItem(vendingItem.getName()) && vendingItem.getQuantity() <= 20) {
            vendingItem.setPrice(b);
            vendingItem.setQuantity(q);
            System.out.println("Item Updated.");
        } else {
            System.out.println("No such item exists. Need to add the item instead.");
        }

         */


    public double calculateBalance(double cost, double amount){
        return amount - cost;
    }

    public int maxCapacity(int max){
        return 20 - max;
    }


    public void displayMenu(){
        System.out.println("----------The Vending Machine Menu----------");
        System.out.println("ID \t\t\t\t\t Name \t\t\t\t\t Price \t\t\t\t\t Quantity");
        for(VendingItem m : items){
            System.out.println(m.getId()+ " \t\t\t\t\t " +m.getName()+ " \t\t\t\t\t " +m.getPrice()+ " \t\t\t\t\t " +m.getQuantity());
        }
    }
}
