package concurrent.homework;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Operator {
    private static final int OPERATORS=5;

    private static final int FREEOPERATORS=6;

    private final Semaphore semaphore;

    private final Lock operatorLock;

    private boolean freeOperator[];

    private ConcurrentLinkedQueue<Operator> operator;

    public Operator() {

        semaphore = new Semaphore(OPERATORS);
        freeOperator = new boolean[FREEOPERATORS];
        operatorLock = new ReentrantLock();
        Arrays.fill(freeOperator, true);

    }

    public void conversation(Object client) throws InterruptedException {
        try {
            //acquire() requests access to the block of code following the call to this method,
            //if access is not allowed, the thread that called this method blocks until
            //until the semaphore allows access
            semaphore.acquire();
            int assignedOperator = getOperator();


            System.out.println("Operator " + assignedOperator +
                    " start conversation with  " + Thread.currentThread().getName());

            Thread.sleep(1000);

            releaseOperator(assignedOperator);

            System.out.println("Operator " + assignedOperator +
                    " finished the conversation with the " + Thread.currentThread().getName());

            semaphore.release();// releases the resource

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private int getOperator() throws InterruptedException {
        int foundOperator = -1;
        operatorLock.lock();
        Thread.sleep(1000);
        for (int i = 1; i < freeOperator.length; i++) {
//Looking for a free seat and calling the operator
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
