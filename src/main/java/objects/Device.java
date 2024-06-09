package objects;

public class Device {
    private long subscriber_id;
    private String deviceName;
    private String deviceOS;
    private boolean isActive;

    @Override
    public String toString() {
        return "Devices{" +
                "subscriber_id=" + subscriber_id +
                ", deviceName='" + deviceName + '\'' +
                ", deviceOS='" + deviceOS + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public Device(long subscriber_id, String deviceName, String deviceOS, String isActive) {
        this.subscriber_id = subscriber_id;
        this.deviceName = deviceName;
        this.deviceOS = deviceOS;
        this.isActive = (isActive.toLowerCase().equals("y"));
    }

    public long getSubscriber_id() {
        return subscriber_id;
    }

    public void setSubscriber_id(long subscriber_id) {
        this.subscriber_id = subscriber_id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public String isActive() {
        return (isActive)?"Y":"N";
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
