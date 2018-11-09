package com.nukkitx.server.scheduler;

import java.util.concurrent.ForkJoinPool;

public class AsyncPool extends ForkJoinPool {

    public AsyncPool(int size) {
        super(size, defaultForkJoinWorkerThreadFactory, new AsyncExceptionHandler(), true);
    }

    private static class AsyncExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
        	
        }
    }
}
