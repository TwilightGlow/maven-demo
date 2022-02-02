package com.example.callback;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Javen
 * @date 2022/1/8
 */
public class ListenableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("ListenFutureDemo - ");
        ListenableFuture<Integer> future = executor.submitListenable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return 1;
            }
        });
        future.addCallback(new ListenableFutureCallback<Integer>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + "失败了");
            }

            @Override
            public void onSuccess(Integer integer) {
                System.out.println(Thread.currentThread().getName() + "成功了");
            }
        });
//        future.get();
    }
}
