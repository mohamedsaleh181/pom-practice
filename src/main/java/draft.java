import java.util.ArrayList;

public class draft {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list);
        String ele = list.get(1);
        System.out.println(ele);
        String ele2 = list.set(0,"H");
        System.out.println(ele2);
        System.out.println(list);
        list.add(-1,"NN");
        System.out.println(list);



    }
    public void reverseString (String x) {
        String reversed = new StringBuilder(x).reverse().toString();
        System.out.println(reversed);
    }

}
