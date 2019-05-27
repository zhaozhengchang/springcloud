package com.ceiec.twmp.tmp.task.chart;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

 class SelfDefinedThreadFactory implements ThreadFactory {
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        SelfDefinedThreadFactory(String namePrefix) {
             this.namePrefix = namePrefix+"-";
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread( r,namePrefix + threadNumber.getAndIncrement());
            if (t.isDaemon()) {
                t.setDaemon(true);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
