package academy.learnprogramming.nailedit;

public class Achievement {
    public static final String TAG = "Achievement";
    private boolean isAchieved=false;
    private String description;

    public Achievement(String description) {
        this.description = description;
        isAchieved=false;
    }

    public boolean isAchieved() {
        return isAchieved;
    }

    public void setAchieved(boolean achieved) {
        isAchieved = achieved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
