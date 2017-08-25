# rate-limiter
A thread safe rate limiter for web application.

Inspired by: https://medium.com/smyte/rate-limiter-df3408325846

The RateLimiter is for X requests / Y seconds.

If second is not the ideal unit, refill_time and refill_amount should be modified to double.
If the number of slot is a requirement, large modification should be done.

Original bucket idea(In case the website is down):

- refillCount = floor((now() - bucket.lastUpdate) / bucket.refillTime)
- bucket.value = min(
    - bucket.maxAmount,
    - bucket.value + refillCount * bucket.refillAmount
- )
- bucket.lastUpdate = min(
    - now(), 
    - bucket.lastUpdate + refillCount * bucket.refillTime
- )

Later I realized the update time can simplify further:

RC <= (N - U) / RT , because refillCount is the round down of the right hand side.

U + RC * RT <= U + ((N - U) / RT) * RT

U + RC * RT <= U + N - U

U + RC * RT <= N

Therefore, the minimum is always the bucket.lastUpdate + refillCount * bucket.refillTime.
