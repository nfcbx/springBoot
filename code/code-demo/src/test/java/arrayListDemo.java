/**
 * Created by highness on 2018/10/28 0028.
 */
public class arrayListDemo {

    public static void main(String[] args) {

//        EventBus eventBus = new EventBus();
        String aaa = new String("aaa");
        String bbb = new String("aaa");

        System.out.println(aaa.hashCode());
        System.out.println(bbb.hashCode());

        System.out.println(aaa == bbb);
        System.out.println(aaa.equals(bbb));


    }

}
