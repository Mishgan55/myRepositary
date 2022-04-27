package concurrent.homework;

public class CallCenter {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("callCenter start work\n");
        Operator operator = new Operator();
        Thread[] threads = new Thread[11];
        for (int i = 1; i <= 10; i++)
        {
            threads[i] = new Thread(new Client(operator), "Client " + i);

        }
        for (int i = 1; i <= 10; i++)
        {


            threads[i].start();

        }




    }


}
