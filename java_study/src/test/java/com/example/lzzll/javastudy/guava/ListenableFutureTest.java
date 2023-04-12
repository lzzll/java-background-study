package com.example.lzzll.javastudy.guava;//package com.example.lzzll.guava;
//
//import com.google.common.base.Function;
//import com.google.common.util.concurrent.*;
//import org.junit.Test;
//import sun.nio.ch.ThreadPool;
//
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
///**
// * @Author lf
// * @Date 2019/9/27 10:59
// * @Description:
// */
//public class ListenableFutureTest {
//
//    private static ExecutorService getThreadPool(){
//        ExecutorService executorService = Executors.newCachedThreadPool();
////        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
////            @Override
////            public Thread newThread(Runnable r) {
////                return new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        System.out.println("线程启动！");
////                    }
////                });
////            }
////        });
//        return executorService;
//    }
//
//    // 如果创建executorService时指定了线程任务，那么运行时提交的任务将不会执行
//    @Test
//    public void testGetThreadPool(){
//        getThreadPool().execute(()-> System.out.println("测试线程启动！"));
//    }
//
//
//    // 测试guava中ListenableFuture的多线程之间的回调时，不适合使用单元测试的形式，需要使用主方法来运行，如果使用单元测试，那么线程回调函数的执行将出现问题
//    public static void main(String[] args) {
//        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
//        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
//              @Override
//              public Integer call() throws Exception {
//                  System.out.println("call execute..");
//                  TimeUnit.SECONDS.sleep(1);
//                  return 7;
//              }
//          }
//        );
//        // 实现回调函数方式一
////        listenableFuture.addListener(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    System.out.println("执行回调函数！"+listenableFuture.get());
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                } catch (ExecutionException e) {
////                    e.printStackTrace();
////                }
////            }
////        },executorService);
//
//        // 实现回调函数方式二，推荐使用
//        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
//            @Override
//            public void onSuccess(Integer result) {
//                System.out.println("执行回调函数！"+result);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                System.out.println("回调函数出现异常！");
//            }
//        });
//    }
//
//    // jdk中实现的异步调用回调
//    @Test
//    public void testFutureTask1(){
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        // 直接new一个future对象
//        FutureTask<String> future = new FutureTask<>(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "使用java futrue的api实现异步调用";
//            }
//        });
//        executorService.execute(future);
//        try {
//            String str = future.get(3, TimeUnit.SECONDS);
//            System.out.println(str);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testFutureTask2(){
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        // 获取future的第二种方式
//        Future<?> future = executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("线程任务开启！");
//            }
//        });
//        try {
//            Object obj = future.get(3, TimeUnit.SECONDS);
//            System.out.println(String.valueOf(obj));
//            boolean flag1 = future.isCancelled();
//            boolean flag2 = future.isDone();
//            System.out.println("future isCancelled "+flag1+"\nfuture isDone "+flag2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 等待并转化future
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    @Test
//    public void testThen() throws ExecutionException, InterruptedException {
//        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
//            return "zero";
//        }, getThreadPool());
//
//        CompletableFuture<Integer> f2 = f1.thenApply(new Function<String, Integer>() {
//
//            @Override
//            public Integer apply(String t) {
//                System.out.println(2);
//                return Integer.valueOf(t.length());
//            }
//        });
//
//        CompletableFuture<Double> f3 = f2.thenApply(r -> r * 2.0);
//        System.out.println(f3.get());
//    }
//
//    /**
//     * future完成处理,可获取结果
//     */
//    @Test
//    public void testThenAccept(){
//        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
//            return "zero";
//        }, getThreadPool());
//        f1.thenAccept(e -> {
//            System.out.println("get result:"+e);
//        });
//    }
//
//    /**
//     * future完成处理
//     */
//    @Test
//    public void testThenRun(){
//        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
//            return "zero";
//        }, getThreadPool());
//        f1.thenRun(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("finished");
//            }
//        });
//    }
//
//    /**
//     * compose相当于flatMap,避免CompletableFuture<CompletableFuture<String>>这种
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    @Test
//    public void testThenCompose() throws ExecutionException, InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
//            return "zero";
//        }, executor);
//        // 进行了两层future的引用
//        CompletableFuture<CompletableFuture<String>> f4 = f1.thenApply(ListenableFutureTest::calculate);
//        System.out.println("f4.get:"+f4.get().get());
//
//        // 只有一层future的引用
//        CompletableFuture<String> f5 = f1.thenCompose(ListenableFutureTest::calculate);
//        System.out.println("f5.get:"+f5.get());
//
//        System.out.println(f1.get());
//    }
//
//    public static CompletableFuture<String> calculate(String input) {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println(input);
//            return input + "---" + input.length();
//        }, executor);
//        return future;
//    }
//
//    /**
//     * thenCombine用于组合两个并发的任务,产生新的future有返回值
//     * thenCombine(组合两个future，有返回值)
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    @Test
//    public void testThenCombine() throws ExecutionException, InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println("f1 start to sleep at:"+System.currentTimeMillis());
//                Thread.sleep(1000);
//                System.out.println("f1 finish sleep at:"+System.currentTimeMillis());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "zero";
//        }, executor);
//        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println("f2 start to sleep at:"+System.currentTimeMillis());
//                Thread.sleep(3000);
//                System.out.println("f2 finish sleep at:"+System.currentTimeMillis());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "hello";
//        }, executor);
//
//        CompletableFuture<String> reslutFuture =
//                f1.thenCombine(f2, new BiFunction<String, String, String>() {
//
//                    @Override
//                    public String apply(String t, String u) {
//                        System.out.println("f3 start to combine at:"+System.currentTimeMillis());
//                        return t.concat(u);
//                    }
//                });
//
//        System.out.println(reslutFuture.get());
//        System.out.println("finish combine at:"+System.currentTimeMillis());
//    }
//
//    /**
//     * thenAcceptBoth用于组合两个并发的任务,产生新的future没有返回值
//     * thenAcceptBoth(组合两个future,没有返回值)
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    @Test
//    public void testThenAcceptBoth() throws ExecutionException, InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println("f1 start to sleep at:"+System.currentTimeMillis());
//                TimeUnit.SECONDS.sleep(1);
//                System.out.println("f1 stop sleep at:"+System.currentTimeMillis());
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return "zero";
//        }, executor);
//        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println("f2 start to sleep at:"+System.currentTimeMillis());
//                TimeUnit.SECONDS.sleep(3);
//                System.out.println("f2 stop sleep at:"+System.currentTimeMillis());
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return "hello";
//        }, executor);
//
//        CompletableFuture<Void> reslutFuture = f1.thenAcceptBoth(f2, new BiConsumer<String, String>() {
//            @Override
//            public void accept(String t, String u) {
//                System.out.println("f3 start to accept at:"+System.currentTimeMillis());
//                System.out.println(t + " over");
//                System.out.println(u + " over");
//            }
//        });
//
//        System.out.println(reslutFuture.get());
//        System.out.println("finish accept at:"+System.currentTimeMillis());
//    }
//
//    // 测试completableFuture中的其它方法
//    // applyToEither()返回两个异步调用的future中先返回的那个返回值；
//    // apply
//    @Test
//    public void testMethod(){
//        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "f1";
//        }, getThreadPool());
//
//        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "f2";
//        }, getThreadPool());
//
//        // applyToEither()返回两个异步调用的future中先返回的那个返回值；
//        CompletableFuture<String> resultFuture1 = f1.applyToEither(f2, new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        });
//        // 取两者最先返回来的那个，无返回值
//        CompletableFuture<Void> resultFuture2 = f1.acceptEither(f2, (String s) -> {
//            System.out.println(s);
//        });
//        try {
////            System.out.println(resultFuture1.get());
//            System.out.println(resultFuture2.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 等待多个future返回
//     * allOf(等待所有future返回),此方法是CompletableFuture类中的静态方法
//     */
//    @Test
//    public void testAllOf() throws InterruptedException {
//        List<CompletableFuture<String>> futures = IntStream.range(1,10)
//                .mapToObj(i ->
//                        longCost(i)).collect(Collectors.toList());
//        final CompletableFuture<Void> allCompleted = CompletableFuture.allOf(futures.toArray(new CompletableFuture[]{}));
//        allCompleted.thenRun(() -> {
//            futures.stream().forEach(future -> {
//                try {
//                    System.out.println("get future at:"+System.currentTimeMillis()+", result:"+future.get());
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//            });
//
//        });
//        Thread.sleep(15000); //wait
//    }
//
//    /**
//     * 等待多个future当中最快的一个返回
//     * anyOf(取多个future当中最快的一个返回)
//     * @throws InterruptedException
//     */
//    @Test
//    public void testAnyOf() throws InterruptedException {
//        List<CompletableFuture<String>> futures = IntStream.range(1,10)
//                .mapToObj(i ->
//                        longCost(i)).collect(Collectors.toList());
//        final CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(futures.toArray(new CompletableFuture[]{}));
//        firstCompleted.thenAccept((Object result) -> {
//            System.out.println("get at:"+System.currentTimeMillis()+",first result:"+result);
//        });
//        TimeUnit.SECONDS.sleep(10);
//    }
//
//    private CompletableFuture<String> longCost(long i){
//        return CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println("f"+i+" start to sleep at:"+System.currentTimeMillis());
//                Thread.sleep(1000);
//                System.out.println("f"+i+" stop sleep at:"+System.currentTimeMillis());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return String.valueOf(i);
//        },getThreadPool());
//    }
//}
