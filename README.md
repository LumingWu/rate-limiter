# rate-limiter
A thread safe rate limiter for web application.

Learned from: https://medium.com/smyte/rate-limiter-df3408325846

The RateLimiter is for X requests / Y seconds.

If second is not the ideal unit, refill_time and refill_amount should be modified to double.
If the number of slot is a requirement, large modification should be done.
