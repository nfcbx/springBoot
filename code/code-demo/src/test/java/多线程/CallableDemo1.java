package 多线程;

import java.util.concurrent.*;

public class CallableDemo1 {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);


        Callable<Object> callable = new Callable<Object>() {

            @Override
            public Object call() throws Exception {

                return "test";
            }
        };

        Future<Object> submit = executorService.submit(callable);

        try {
            Object o = submit.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }




}
