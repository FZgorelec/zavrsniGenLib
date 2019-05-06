package util;

public class RNGThread extends Thread implements IRandomNumberGeneratorProvider {

    private IRandomNumberGenerator rng = new RandomNumberGenerator();

    public RNGThread() {
    }

    public RNGThread(Runnable target) {
        super(target);
    }

    public RNGThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public RNGThread(String name) {
        super(name);
    }

    public RNGThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public RNGThread(Runnable target, String name) {
        super(target, name);
    }

    public RNGThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public RNGThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

    @Override
    public IRandomNumberGenerator getRNG() {
        return rng;
    }
}
