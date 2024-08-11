package com.eanco.fitivation.util;

import java.util.concurrent.Executors;

public class ThreadUtils {
    public static class Executor {

        private static final Object LOCK = new Object();
        private static Executor sInstance;
        private final java.util.concurrent.Executor executor;

        private Executor(java.util.concurrent.Executor executor) {
            this.executor = executor;
        }

        public static Executor getsInstance(){
            synchronized (LOCK){
                sInstance = new Executor(Executors.newSingleThreadExecutor());
            }
            return sInstance;
        }

        public java.util.concurrent.Executor getExecutor() {
            return executor;
        }

    }
}
