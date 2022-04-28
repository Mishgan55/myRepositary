package concurrent.homework;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Operator {
    private final Semaphore semaphore;

    private final Lock operatorLock;

    private boolean freeOperator[];

    private ConcurrentLinkedQueue<Operator> operator;

    public Operator() {

        semaphore = new Semaphore(5);
        freeOperator = new boolean[6];
        operatorLock = new ReentrantLock();
        Arrays.fill(freeOperator, true);

    }

    public void conversation(Object client) throws InterruptedException {
        try {
            semaphore.acquire();
            int assignedOperator = getOperator();



            System.out.println("Operator " + assignedOperator +
                    " start conversation with  " + Thread.currentThread().getName());

            Thread.sleep(1000);

            releaseOperator(assignedOperator);

            System.out.println("Operator " + assignedOperator +
                    " finished the conversation with the " + Thread.currentThread().getName());

            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private int getOperator() throws InterruptedException {
        int foundOperator = -1;
        operatorLock.lock();
        Thread.sleep(1000);
        for (int i = 1; i < freeOperator.length; i++) {

            if (freeOperator[i]) {
                foundOperator = i;
                freeOperator[i] = false;
                System.out.println("Operator " + i + " is connects with a new client");
                break;


            }
        }
        operatorLock.unlock();
        return foundOperator;


    }

    private void releaseOperator(int operator) throws InterruptedException {
        operatorLock.lock();

        freeOperator[operator] = true;
        operatorLock.unlock();

    }


}
