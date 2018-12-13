package academy.learnprogramming.nailedit;

public class ToDoItem {
    private static final String TAG = "ToDoItem";
    private String itemName;
    private boolean isCompleted;

    public ToDoItem(String itemName){
        this.itemName=itemName;
        isCompleted=false;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItem(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return itemName;
    }

    public void setCompleted(){
        isCompleted=true;
    }

    public boolean getCompleted(){
        return isCompleted;
    }

}
