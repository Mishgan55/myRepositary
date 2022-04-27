package concurrent.homework;

public class Client implements Runnable, NameClient {
    private Operator operator;
    private String name;

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client(Operator operator) {
        this.operator = operator;
        this.name = randomName();
    }

    @Override
    public synchronized void run() {

        System.out.println(Thread.currentThread().getName() + " came to CallCentre ");
        try {

            operator.conversation(new Object());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
