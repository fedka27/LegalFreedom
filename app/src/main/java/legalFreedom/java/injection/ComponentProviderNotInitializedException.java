package legalFreedom.java.injection;

public class ComponentProviderNotInitializedException extends RuntimeException {
    private static final String ERROR = "DaggerManager not initialized yet. " +
            "You must call DaggerManager.getInstance().init(Application) before you can use it.";

    public ComponentProviderNotInitializedException() {
        super(ERROR);
    }
}
