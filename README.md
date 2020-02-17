# Запуск проекта
```bash
git clone https://github.com/Linolium/process-registration-camunda-keycloak.git && cd process-registration-camunda-keycloak
mvn package (можно сделать package через IDE если не установлен maven)
docker build -t process_registration .
docker-compose up
```

# Общие данные
http://localhost:8080 - camunda   
login - demo 
password -demo 

http://localhost:8081 - keycloak  
login - admin  
password - admin  

# Проверка
Для тестирования процесса необходимо отправить post запрос с json телом на http://localhost:8080/rest/engine/default/process-definition/key/process_registration/start

Пример тела запроса

```json
{
  "variables": {
    "json" : {
        "value" : "{\"username\": \"Alex\",\"password\": \"1234\",\"email\": \"alex@gmail.com\"}",
        "type": "String"
    }
  },
 "businessKey" : "businessKey"
}
```
