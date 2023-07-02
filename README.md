Note: to make this task a bit interesting and useful for myself, I decided to try new technologies I've never worked with before - 
NoSQL, Spring Data REST and Redis. I probably wouldn't use Data Rest and Redis again if I had to make similar project from scratch: 
- Spring REST DATA - due to little flexibility when it comes to customization of REST responses
- Redis - because of bad handling when it comes to DATE format. I didn't found a way to make a query that will find entries 
  between two dates and what I found is that it might be needed to store data as Unix TimeStamps to achieve that. I found that 
  there is such function in MongoDB, so I would use it instead.



# Getting Started

docker run -d --name redis -p 6379:6379 redis
