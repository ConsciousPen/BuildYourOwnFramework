public class NewThread_Runnable implements Runnable {
    Thread t;

    NewThread_Runnable() {
        t = new Thread(this, "Demo поток");
        System.out.println("Child: " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Child: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child прерван");
        }
        System.out.println("Child завершён");
    }

    static class ThreadDemo {
        public static void main(String args[]) {
            new NewThread_Runnable();

            try {
                for(int i = 5; i > 0; i--){
                    System.out.println("Main поток: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Main поток прерван");
            }
            System.out.println("Main поток завершён");
        }
    }

}
