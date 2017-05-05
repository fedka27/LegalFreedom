package legalFreedom.java.util.rx;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
/**
 * @author e.matsyuk
 */
public class RxSchedulersTest extends RxSchedulersAbs {

    @Override
    public io.reactivex.Scheduler getMainThreadScheduler() {
        return Schedulers.single();
    }

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.single();
    }

    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.single();
    }

}
