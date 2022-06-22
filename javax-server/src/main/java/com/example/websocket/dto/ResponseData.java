package com.example.websocket.dto;

/**
 * * @author Amit Sharma
 **/

public class ResponseData {
    String serviceName;
    String timeTaken;
    String param;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "serviceName='" + serviceName + '\'' +
                ", timeTaken='" + timeTaken + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
