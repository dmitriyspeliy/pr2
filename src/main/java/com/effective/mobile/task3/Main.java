package com.effective.mobile.task3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(7, Integer.class);
        for (int i = 1; i <= 7; i++) {
            ringBuffer.write(i);
            System.out.println("LIST " + Arrays.toString(ringBuffer.getCharList()));
            System.out.println("AvailableToRead: " + ringBuffer.getAvailableToRead());
            System.out.println("AvailableToWrite: " + ringBuffer.getAvailableToWrite());
            System.out.println("ConsumerOffSet: " + ringBuffer.getConsumerOffSet());
            System.out.println("ProducerOffSet: " + ringBuffer.getProducerOffSet());
        }

        ringBuffer.read();
        System.out.println("LIST " + Arrays.toString(ringBuffer.getCharList()));
        System.out.println("AvailableToRead: " + ringBuffer.getAvailableToRead());
        System.out.println("AvailableToWrite: " + ringBuffer.getAvailableToWrite());
        System.out.println("ConsumerOffSet: " + ringBuffer.getConsumerOffSet());
        System.out.println("ProducerOffSet: " + ringBuffer.getProducerOffSet());

        ringBuffer.write(8);
        System.out.println("LIST " + Arrays.toString(ringBuffer.getCharList()));
        System.out.println("AvailableToRead: " + ringBuffer.getAvailableToRead());
        System.out.println("AvailableToWrite: " + ringBuffer.getAvailableToWrite());
        System.out.println("ConsumerOffSet: " + ringBuffer.getConsumerOffSet());
        System.out.println("ProducerOffSet: " + ringBuffer.getProducerOffSet());

        ringBuffer.read();
        ringBuffer.read();
        ringBuffer.read();
        ringBuffer.read();
        ringBuffer.read();
        System.out.println("LIST " + Arrays.toString(ringBuffer.getCharList()));
        System.out.println("AvailableToRead: " + ringBuffer.getAvailableToRead());
        System.out.println("AvailableToWrite: " + ringBuffer.getAvailableToWrite());
        System.out.println("ConsumerOffSet: " + ringBuffer.getConsumerOffSet());
        System.out.println("ProducerOffSet: " + ringBuffer.getProducerOffSet());

        ringBuffer.read();
        ringBuffer.read();
        System.out.println("LIST " + Arrays.toString(ringBuffer.getCharList()));
        System.out.println("AvailableToRead: " + ringBuffer.getAvailableToRead());
        System.out.println("AvailableToWrite: " + ringBuffer.getAvailableToWrite());
        System.out.println("ConsumerOffSet: " + ringBuffer.getConsumerOffSet());
        System.out.println("ProducerOffSet: " + ringBuffer.getProducerOffSet());
        ringBuffer.read();
        System.out.println("LIST " + Arrays.toString(ringBuffer.getCharList()));
        System.out.println("AvailableToRead: " + ringBuffer.getAvailableToRead());
        System.out.println("AvailableToWrite: " + ringBuffer.getAvailableToWrite());
        System.out.println("ConsumerOffSet: " + ringBuffer.getConsumerOffSet());
        System.out.println("ProducerOffSet: " + ringBuffer.getProducerOffSet());
    }
}
