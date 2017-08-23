package main.java.run;


import main.java.ratelimiter.RateLimiter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        RateLimiter limiter = new RateLimiter(10, 1, 1);
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            if(scanner.nextLine().equals("exit")){
                break;
            }
            System.out.println("Status: " + limiter.requestRate());
            System.out.println("Token: " + limiter.getToken());
            System.out.println("Time: " + limiter.getLastUpdate());
        }
    }

}
