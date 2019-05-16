package circularlinkedlist;

public class Player implements Comparable {

    private String username;

    public Player(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public int compareTo(java.lang.Object o) {
        Player x = (Player) o;
        int c = this.username.compareToIgnoreCase(x.getUsername());
        if (c > 0) {
            return 1;
        } else if (c < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
