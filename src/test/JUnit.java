package test;

import main.java.ratelimiter.RateLimiter;
import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import static junit.framework.Assert.assertTrue;

public class JUnit {

    @Test
    public void limitTest(){
        RateLimiter limiter = new RateLimiter(10, 1, 10);
        int false_count = 0;
        for(int i = 0; i < 11; i++){
            if(!limiter.requestRate()){
                false_count += 1;
            }
        }
        assertTrue("False count: " + false_count, false_count == 1);
    }

    @Test
    public void refillFullTest() throws InterruptedException {
        RateLimiter limiter = new RateLimiter(10, 1, 10);
        for(int i = 0; i < 10; i++){
            limiter.requestRate();
        }
        Thread.sleep(1000);
        limiter.requestRate();
        assertTrue("Tokens: " + limiter.getToken(), limiter.getToken() == 9);
    }

    @Test
    public void refillPartialTest() throws InterruptedException {
        RateLimiter limiter = new RateLimiter(10, 1, 2);
        for(int i = 0; i < 10; i++){
            limiter.requestRate();
        }
        Thread.sleep(1000);
        limiter.requestRate();
        assertTrue("Tokens: " + limiter.getToken(), limiter.getToken() == 1);
    }

}
