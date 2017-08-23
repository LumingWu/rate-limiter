package main.java.ratelimiter;


import java.time.Duration;
import java.time.Instant;

public class RateLimiter{
    
    /**
     * maxAmount: the maximum number of tokens the bucket can hold
     * refillTime: the amount of time between refills
     * refillAmount: the number of tokens that are added to the bucket during a refill
     * value: the current number of tokens in the bucket
     * lastUpdate: the last time the bucket was updated
     */
    
    private int max_slot;
    private int slot;
    private int refill_time;
    private int refill_amount;
    private Instant last_update;
    
    public RateLimiter(int maxslot, int refilltime, int refillamount){
        max_slot = maxslot;
        slot = max_slot;
        refill_time = refilltime;
        refill_amount = refillamount;
        last_update = Instant.now();
    }
    
    public RateLimiter(int maxslot, int refilltime){
        this(maxslot, refilltime, 1);
    }
    
    public synchronized boolean hasRate(){
        Instant now = Instant.now();
        int refill_count = (int) Math.floor(Duration.between(last_update, now).getSeconds() / refill_time);
        slot = Math.min(max_slot, slot + refill_count);
        last_update = last_update.plusSeconds(refill_count * refill_time);
        if(now.isBefore(last_update)){
            last_update = now;
        }
        if(slot > 0){
            slot -= 1;
            return true;
        }
        return false;
    }

    public int getSlot(){
        return slot;
    }

    public Instant getLastUpdate(){
        return last_update;
    }


}