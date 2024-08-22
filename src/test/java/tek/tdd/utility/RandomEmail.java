package tek.tdd.utility;

import java.util.Random;

public class RandomEmail {
   // Random random=new Random();
    public static String randomEmail(String name){
int random=(int) (Math.random()*100000);

return name+random+"@mail.com";
    }
}//"Mathias"+random+@apol.cbvn