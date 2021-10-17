package com.luoyun.course.concurrency;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ThreadNotifyDemo
 * 启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程
 * @author luoyun
 * @data 2021/10/17
 */
public class ThreadNotifyDemo {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        SumResult sumResult = new SumResult();
        ThreadNotifyDemo demo = new ThreadNotifyDemo();
        //demo.threadNotify(sumResult);
        //demo.threadJoin(sumResult);
        //demo.atomic(sumResult);
        //demo.withSynchronized(sumResult);
        //demo.withLock(sumResult);
        //demo.withLockAndCondition(sumResult);
        //demo.park(sumResult);
        //LockSupport.park();
        //demo.countdownLatch(sumResult);
        //demo.cyclicBarrier(sumResult);
        //demo.semaphore(sumResult);
        //demo.threadFuture(sumResult);
        //demo.completableFuture(sumResult);
        System.out.println("异步计算结果为："+ sumResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }


    private synchronized void threadNotify(SumResult sumResult){
        Object lock = new Object();
       new Thread(() -> {
           synchronized (lock) {
               sumResult.setResult(sum());
               lock.notify(); //lock.notifyAll();
           }
        }).start();
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch(InterruptedException e){
             e.printStackTrace();
        }
    }

    private void threadJoin(SumResult sumResult){
        Thread thread = new Thread(() -> {
            sumResult.setResult(sum());
        });
        thread.start();
        try {
            thread.join();
        } catch(InterruptedException e){
             e.printStackTrace();
        }
    }

    private void atomic(SumResult sumResult){
        AtomicBoolean finish = new AtomicBoolean(false);
        new Thread(() -> {
            sumResult.setResult(sum());
            finish.set(true);
        }).start();
        //轮询是否finish
        while (!finish.get()) {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void withSynchronized(SumResult sumResult){
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                sumResult.setResult(sum());
            }
        }).start();
        //sleep下 让计算线程先拿到锁
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        //阻塞无中断、无超时等待锁
        synchronized (lock) {
        }

    }

    private void withLock(SumResult sumResult){
        ReentrantLock lock = new ReentrantLock(true);
        new Thread(() -> {
            try {
                lock.lock();
                sumResult.setResult(sum());
            }finally {
                lock.unlock();
            }
        }).start();
        //sleep下 让计算线程先拿到锁
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        try {
            lock.lock();
        }finally {
            lock.unlock();
        }
    }

    private void withLockAndCondition(SumResult sumResult){
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                sumResult.setResult(sum());
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }).start();
        lock.lock();
        try {
            try {
                condition.await();
            } catch(InterruptedException e){
                 e.printStackTrace();
            }
        }finally {
            lock.unlock();
        }
    }

    private void park(SumResult sumResult) {
        sumResult.setThread(Thread.currentThread());
        new Thread(() -> {
            sumResult.setResult(sum());
            LockSupport.unpark(sumResult.getThread());
        }).start();
        LockSupport.park();
    }

    private void countdownLatch(SumResult sumResult){
        CountDownLatch countDown = new CountDownLatch(1);

        new Thread(() -> {
            sumResult.setResult(sum());
            countDown.countDown();
        }).start();
        try {
            countDown.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void cyclicBarrier(SumResult sumResult){
        //等待主线程和子线程一起到齐
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(() -> {
            sumResult.setResult(sum());
            try {
                cyclicBarrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
        try {
            cyclicBarrier.await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void semaphore(SumResult sumResult){
        Semaphore semaphore = new Semaphore(0);
        new Thread(() -> {
            try {
                sumResult.setResult(sum());
            }finally {
                semaphore.release();
            }
        }).start();
        //计算线程计算完成后release1个信号量给主线程
        try {
            semaphore.acquire();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void future(SumResult sumResult){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> sumResult.setResult(sum()));
        try {
            future.get();
        } catch(Exception e){
             e.printStackTrace();
        }
    }

    private void completableFuture(SumResult sumResult) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> sum());
        int result = 0;
        try {
            result = future.get();
        } catch(Exception e){
            e.printStackTrace();
        }
        sumResult.setResult(result);
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    static class SumResult {

        private int result = 0;

        private Thread thread;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }
    }

}
