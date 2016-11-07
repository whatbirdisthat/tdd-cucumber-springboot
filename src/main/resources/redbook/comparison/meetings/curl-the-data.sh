curl -H "accept:application/json" https://pre-api.test.sunbets.co.uk/v1/info-service/racing/dates/today/meetings > ppw-meetings.json
curl -H "accept:application/json" https://api.sunbets.co.uk/v1/info-service/racing/dates/today/meetings > prod-meetings.json
curl -H "accept:application/json" https://api.beta.tab.com.au/v1/tab-info-service/racing/dates/today/meetings?jurisdiction=VIC > tab-meetings.json


