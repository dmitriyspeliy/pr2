package com.effective.mobile.task3;

import java.lang.reflect.Array;
import java.util.Optional;
import java.util.logging.Logger;


public class RingBuffer<T> {

    Logger log = Logger.getLogger(RingBuffer.class.getName());

    private int producerOffSet = 0;
    private int consumerOffSet = 0;
    private int availableToWrite;
    private int availableToRead;
    private final int capacity;
    private final T[] arr;


    public RingBuffer(int capacity, Class<T> tClass) {
        this.capacity = capacity;
        arr = (T[]) Array.newInstance(tClass, capacity);
        availableToWrite = capacity;
        availableToRead = 0;
    }

    public synchronized Optional<T> read() {
        T res = null;
        if (availableToRead > 0) {
            res = arr[consumerOffSet++ % capacity];
            availableToRead = producerOffSet - consumerOffSet;
            availableToWrite = capacity - (producerOffSet - consumerOffSet);
            return Optional.of(res);
        } else {
            log.info("Waiting producer write smth");
            return Optional.ofNullable(res);
        }
    }

    public synchronized void write(T a) {
        if (availableToWrite > 0) {
            arr[producerOffSet++ % capacity] = a;
            availableToWrite = capacity - (producerOffSet - consumerOffSet);
            availableToRead = producerOffSet - consumerOffSet;
        } else {
            log.info("No space!");
        }
    }

    public int getProducerOffSet() {
        return producerOffSet;
    }

    public int getConsumerOffSet() {
        return consumerOffSet;
    }

    public int getAvailableToWrite() {
        return availableToWrite;
    }

    public int getAvailableToRead() {
        return availableToRead;
    }

    public T[] getCharList() {
        return arr;
    }
}
