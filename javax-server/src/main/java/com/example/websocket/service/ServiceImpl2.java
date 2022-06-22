package com.example.websocket.service;

import com.example.websocket.dto.ResponseData;
import org.springframework.stereotype.Service;

/**
 * * @author Amit Sharma
 **/
@Service
public class ServiceImpl2 implements Worker{
    @Override
    public ResponseData doWork(String param) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        /*for(int i=1;i<15000;i++){
            System.out.println(i);
        }*/
        ResponseData rd = new ResponseData();
        rd.setServiceName("Service 2");
        rd.setParam(param);
        long timeTaken = System.currentTimeMillis() - startTime;
        rd.setTimeTaken("time taken in milis : "+ timeTaken);
        return rd;
    }
}
