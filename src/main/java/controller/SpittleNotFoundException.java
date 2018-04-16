package controller;

public class SpittleNotFoundException extends Throwable {
    private long spittleId;

    public SpittleNotFoundException(long spittleId) {
        this.spittleId = spittleId;
    }

    public long getSpittleId() {
        return spittleId;
    }
}
