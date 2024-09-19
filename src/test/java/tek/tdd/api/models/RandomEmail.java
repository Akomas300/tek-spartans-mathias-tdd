package tek.tdd.api.models;

public class RandomEmail {
    public static String randomEmail(){
        int random=(int)(Math.random()*84432);
        return "sommi"+random+"@gmail.com";
    }
}
