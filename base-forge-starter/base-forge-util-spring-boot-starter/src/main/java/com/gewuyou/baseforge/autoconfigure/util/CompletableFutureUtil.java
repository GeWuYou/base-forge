package com.gewuyou.baseforge.autoconfigure.util;


import com.gewuyou.baseforge.autoconfigure.util.exception.UtilException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 异步工具类
 *
 * @author gewuyou
 * @since 2024-10-20 15:46:30
 */
@Slf4j
public class CompletableFutureUtil {
    private CompletableFutureUtil() {

    }

    /**
     * 异步执行Supplier，并返回CompletableFuture对象
     *
     * @param supplier 异步执行的Supplier
     * @param executor 执行的线程池
     * @param <T>      返回值类型
     * @return CompletableFuture对象
     * @apiNote 这个和CompletableFuture.supplyAsync()没什么区别就是加了个exceptionally处理异常
     */
    public static <T> CompletableFuture<T> supplyAsyncWithExceptionAlly(Supplier<T> supplier, Executor executor) {
        return CompletableFuture
                .supplyAsync(supplier, executor)
                .exceptionally(e -> {
                    log.error(e.getMessage(), e);
                    throw new UtilException(e.getMessage());
                });
    }

    /**
     * 批量异步执行Runnable
     *
     * @param executor 执行的线程池
     * @param runnable 异步执行的Runnable
     * @apiNote 不建议大批量添加任务，容易造成异步任务堆积，导致线程池资源不足
     */
    public static void runAsyncWithExceptionAlly(Executor executor, Runnable... runnable) {
        for (Runnable r : runnable) {
            CompletableFuture
                    .runAsync(r, executor)
                    .exceptionally(e -> {
                         log.error(e.getMessage(), e);
                         throw new UtilException(e.getMessage());
                    });
        }
    }


    /**
     * 异步执行Runnable，并返回CompletableFuture对象
     *
     * @param runnable 异步执行的Runnable
     * @param executor 执行的线程池
     * @return CompletableFuture对象
     */
    public static CompletableFuture<Void> runAsyncWithExceptionAlly(Runnable runnable, Executor executor, Function<Throwable, Void> fn) {
        return CompletableFuture
                .runAsync(runnable, executor)
                .exceptionally(fn);
    }

    /**
     * 异步执行Supplier，并返回CompletableFuture对象
     *
     * @param supplier 异步执行的Supplier
     * @param executor 执行的线程池
     * @param <T>      返回值类型
     * @return CompletableFuture对象
     * @apiNote 这个和CompletableFuture.supplyAsync()没什么区别就是加了个exceptionally处理异常
     */
    public static <T> CompletableFuture<T> supplyAsyncWithExceptionAlly(Supplier<T> supplier, Executor executor, Function<Throwable, ? extends T> fn) {
        return CompletableFuture
                .supplyAsync(supplier, executor)
                .exceptionally(fn);
    }
}