package mak.util;

public class Utility {

    public static void sleep(){
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
