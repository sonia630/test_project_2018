package threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AsyncTask {
    public static void main(String[] args) {
        List<Future<String>> list = new ArrayList<>();

        ExecutorService service = Executors.newCachedThreadPool();


        for (int i = 0; i < 100; i++) {
            MyTask1 task = new MyTask1(i);
            Future<String> result = service.submit(task);
            list.add(result);
        }


        /*
        for (Future<String> future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                service.shutdown();
            }
        }*/


        System.out.println("main exit");

//        for (int i = 0; i < 100; i++) {
//            FutureTask<String> ft = new FutureTask<>(task1);
//            Future<String> result3 = (Future<String>) service.submit(ft);
//            System.out.println("...等待完成任务3.。执行结果是.i=" + i + "：" + result3.get());
//        }
        service.shutdown();

    }

}

class MyTask1 implements Callable {
    private int id;

    public MyTask1(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
//        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println( "success result of thread:#" + id);
        return "success result of thread:#" + id;
    }
}
