# vote_restaurant

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e19b70a1a840492abd2ce1539a1deb54)](https://www.codacy.com/app/ksandr-ua/vote_restaurant?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=aleksandrbogomolov/vote_restaurant&amp;utm_campaign=Badge_Grade)

Java приложение с использованием Spring MVC, Spring Security, Spring Data JPA, Hibernate ORM, REST(Jackson), SLF4J, Apache Tomcat, Maven, Thymeleaf, jQuery, Bootstrap.

Демо доступно по адресу [https://vote-restaurant.herokuapp.com/](https://vote-restaurant.herokuapp.com/)

Каждый ресторан предлагает меню каждый день. Пользователи могут голосовать за ресторан в котором они хотели бы обедать. Только один голос учитывается если пользователь голосует снова в тот же день. Если это произошло после 07:00 и до 11:00 мы считаем, что пользователь изменил свое мнение. Если до 07:00 и после 11:00, тогда голос не может быть изменен или добавлен.

Доступны два предустановленных пользователя с различными правами  
* Админ (логин = **admin@yandex.ru** пароль = **admin**) для доступа ко всем возможностям  
* Юзер (логин = **user@yandex.ru** пароль = **password**) с правами пользователя. Также вы можете зарегестировать новый аккаунт с правами пользователя. 

Админ/Юзер не доступны для редактирования и удаления.

Запуск из командной строки

```
mvn clean package
mvn tomcat7:run
```