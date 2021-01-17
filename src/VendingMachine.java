public interface VendingMachine {
    void addItem();
    //boolean removeItem(int id);
    boolean removeItem();
    boolean searchItem(String name);
    boolean updateItem();
    int getItem(int itemId);
    void dispense();
}
