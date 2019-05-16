package circularlinkedlist;

public class MainClass {

    public static void main(String[] args) {
        CircularLinkedList<Player> list = new CircularLinkedList();
        list.add(new Player("bomber"));
        list.add(new Player("artillerist"));
        list.addPreventingRepetition(new Player("bomber"));
        list.add(new Player("shooter"));

        System.out.println(list);

        list.set(2, new Player("infant"));
        System.out.println(list);

        list.sort();
        System.out.println("\n-SORTED-\n" + list + "\n");

        list.remove(1);
        System.out.println(list);

        list.remove(list.get(0));
        System.out.println(list);

        list.remove(list.size() - 1);
        System.out.println(list);
    }

}
