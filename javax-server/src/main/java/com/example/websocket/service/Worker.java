package com.example.websocket.service;

import com.example.websocket.dto.ResponseData;

/**
 * * @author Amit Sharma
 **/
public interface Worker {

    ResponseData doWork(String param) throws InterruptedException;
}
