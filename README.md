Note: to make this task a bit interesting and useful for myself, I decided to try new technologies I've never worked with before - 
NoSQL, Spring Data REST and Redis. I probably wouldn't use Data Rest and Redis again if I had to make similar project from scratch: 
- Spring REST DATA - due to little flexibility when it comes to customization of REST responses
- Redis - because of bad handling when it comes to DATE format. I didn't found a way to make a query that will find entries 
  between two dates and what I found is that it might be needed to store data as Unix TimeStamps to achieve that. I found that 
  there is such function in MongoDB, so I would use it instead.

  
# Getting Started
To start application on Linux OS you need to have docker installed and do the following:
1. Application can be started using start.sh script (enter project catalog and type `./start.sh` command in commandline).
2. In another tab execute `./demo.sh` command to see REST endpoints in action.


IMPORTANT: Data in demo is using sequence-generated IDs, so it can be run only on clean database.
If you want to re-run demo you need to have clean database. To clean redis database execute:
1. docker exec -it redis bash
2. redis-cli
3. flushall