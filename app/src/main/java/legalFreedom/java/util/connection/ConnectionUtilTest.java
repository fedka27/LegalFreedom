package legalFreedom.java.util.connection;

public class ConnectionUtilTest extends ConnectionUtilAbs {
    private boolean isOnline = false;

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    @Override
    public boolean isOnline() {
        return isOnline;
    }
}
