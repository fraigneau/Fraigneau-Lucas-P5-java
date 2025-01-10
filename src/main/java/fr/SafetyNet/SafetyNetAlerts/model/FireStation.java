package fr.SafetyNet.SafetyNetAlerts.model;

public class FireStation {

    private String address;
    private String station;

    public FireStation() {
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "FireStation [address=" + address + ", station=" + station + "]";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

}
