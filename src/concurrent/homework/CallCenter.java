package concurrent.homework;

public class CallCenter {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("callCenter start work\n");
        Operator operator = new Operator();
        Thread[] threads = new Thread[Client.CLIENTS];
        // start streams
        for (int i = 1; i <= 15; i++) {

            threads[i] = new Thread(new Client(operator), "Client " + i);


        }
        synchronized (threads) {

            for (int i = 1; i <= 15; i++) {


                threads[i].start();
                Thread.sleep(50);
            }


        }



    }


}
