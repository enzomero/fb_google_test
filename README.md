# Push-notification system based on Firebase Could Message.

![image](./diagram.png)

## Sequences diagram

### 1 Registration

---
     [agent]  [registration-web]  [notify-web]  [firebase-adapter]     [notify-api]  [registration-api]
        |            |               |              [0...N]              [0...N]         [0...N]
        |            |               |                 |                    |               |
        #-----1.0--->#               |                 |                    |               |
        |          (1.1)             |                 |                    |               |
        |            #----------------------------------------1.2-------------------------->#
        |            |               |                 |                    |             (1.3)
        |            |               |                 |                    |               |
     [agent]  [registration-web]  [notify-web]  [firebase-adapter]     [notify-api]  [registration-api]
---

- 1.0 Web request to web service [registration-web] by JSON with a data about the registration.
- 1.1 Web service [registration-web] validates and balances web requests, send it to kafka.
- 1.2 Kafka plays a role of load balancer by sending messages to different partitions.
    * Algorithm based on phone number of user `phoneNumber / numberOfPartitions = N`. Its makes data synchronisation easier.

### 2 Notification

---

     [agent]  [registration-web]  [notify-web]  [firebase-adapter]     [notify-api]  [registration-api]
        |            |               |              [0...N]              [0...N]         [0...N]
        #--------------2.0---------->#                 |                    |               |
        |            |              2.1                |                    |               |
        |            |               #-------2.2------>#                    |               |
        |            |               |                2.3                   |               |
        |            |               |                 #---------------2.4----------------->#
        |            |               |                 |                    |              2.5
        |            |               |                 #<--------------2.6------------------#
        |            |               |                2.7                   |               |
        |            |               |                 #--2.8-->[FCM]       |               |
        |            |               |                2.9                   |               |
        |            |               |                 #--------2.10------->#               |
        |            |               |                 |                    |               |

---

- 2.0 Web request to web service [notify-web] by JSON with notification and phone numbers of recipients.
- 2.1 Web service [notify-web] validates and balances web requests, send it to kafka. If request has set of phones, 
  [notify-web] converts it to one kafka message for each phone.
- 2.2 Kafka plays a role of load balancer by sending messages to different partitions.
    * Algorithm based on phone number of user `phoneNumber / numberOfPartitions = N`. Its makes data synchronisation 
      easier.
- 2.3 Web service [firebase-adapter] listens a partition of kafka topic.
- 2.4 The service gets a notification dto and makes web request to [registration-api] to approve of registration for thr 
  request. 
- 2.5 The [registration-api] gets data of registration from data source.
- 2.6 The [registration-api] responses to [firebase-adapter] by JSON with all tokens registered for particular phone 
  number.
- 2.7 The [firebase-adapter] gets all tokens.
- 2.8 The [firebase-adapter] gets all tokens and sends kafka notification for each of it to [FCM].
- 2.9 If [firebase-adapter] successful send notification to [FCM] it resents a notification to next kafka topic.
- 2.10 The [notify-api] receives notification and saves it to database.  

---

### 3 DTOs

 * JSON for [registration-web]:

        {
            "token" : "asd32r3ff34f45g56h5ht7",
            "phone" : 375331234567,
            "appVersion": "android"
        }

 * JSON for [notify-web]:

        {
            "title" : "we announce the thing",
            "text" : "text which describes the thing",
            "sendTime" : 10101010101,
            "phones":
            [
                375331234567,
                375331234568,
                375331234569,
            ]
        }

### 4 Statistic