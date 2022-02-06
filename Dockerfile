FROM openjdk

WORKDIR /app

COPY . .

EXPOSE 8080

CMD ["openjdk","Test.java"]