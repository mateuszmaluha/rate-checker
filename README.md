# rate-checker

#### Application for checking bitcoin to USD rate.

##### Available endpoints:
- /api/latest-rate
- /api/historical-rates?startDate=`startDate`&endDate=`endDate` \
startDate and endDate should be in format: yyyyMMdd


Period of updating db is configured in properties (`updateRates` in milliseconds).
Default db is H2 file.