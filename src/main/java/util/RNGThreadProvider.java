package util;

import java.util.concurrent.ThreadFactory;

public class RNGThreadProvider implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new RNGThread(r);
    }
}
