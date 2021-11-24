# CINEMA-SERVLET-PROJECT
### *Система-интернет витрина кинотеатра с одним залом.*

Существует две роли: **ADMIN**, **USER**
В системе есть:

+ Расписание показа фильмов на неделю с 8:00 до 22:00 (начало последнего фильма) часа.
+ Незарегистрированный пользователь может видеть список фильмов в прокате, свободные места в зале и имеет возможность зарегистрироваться.
____

 **Зарегистрированному пользователю доступно:**
  + просмотр расписания сеансов на неделю.
  + сортировка записей в таблице с сеансами по дате / времени сеанса, названию фильма.
  + просмотр доступных фильмов, информации по фильму.
  + имеет возможность приобрести билет на выбранный сеанс.
  + просмотреть и отсортировать список купленных билетов.
  
 **Администратор может:**
  + ввести в расписание новый фильм.
  + отменить либо изменить фильм.
  + добавить, удалить, изменить сеанс.
  + посмотреть статистику посещаемости зала в %.
____
UML diagram link: https://zupimages.net/up/21/40/symf.jpg
____
  
## To install the project:
git clone https://github.com/Alekc2110/cinema-servlet-FP.git
____

## Application launch instructions:
Install database and change your access properties in application.properties
Install and configure Tomcat 
Build the project with maven mvn package
Copy the WAR file into $CATALINA_HOME\webapps directory
Go to root folder $CATALINA_HOME\bin and in terminal run the command ./catalina.sh start to run Tomcat
Go to url http://localhost:8081
