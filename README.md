
# Проект по автоматизации тестирования API для [Reqres](https://reqres.in/)
<p align="center">  
<a href="https://reqres.in/"><img title="Reqres" src="images/screenshot/reqres.png" width="950"/>  
</a></p>

> Reqres - сервис, предоставляющий публичный API для практики тестирования и разработки

## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Тестовое покрытие</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#allure-testOps">Интеграция с Allure TestOps</a>

* <a href="#jira">Интеграция с Jira</a>

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>

____
<a id="tools"></a>
## **Технологии и инструменты:**

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/intellij-idea.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/logo/java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/logo/github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/gradle.svg" width="50" height="50"  alt="Gradle"/></a>   
<a href="https://rest-assured.io/"><img src="images/logo/rest-assured.png" width="50" height="50"  alt="REST-assured"/></a>  
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/allure.svg" width="50" height="50"  alt="Allure"/></a>  
<a href="https://qameta.io/"><img src="images/logo/allure-testOps.svg" width="50" height="50"  alt="TestOps"/></a> 
<a href="https://www.jenkins.io/"><img src="images/logo/jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> 
<a href="https://www.atlassian.com/software/jira"><img src="images/logo/jira.svg" width="50" height="50"  alt="Jira"/></a>  
<a href="https://telegram.org/"><img src="images/logo/telegram.svg" width="50" height="50"  alt="Telegram"/></a>
</p>

Автотесты разработаны на языке программирования `Java` с использованием библиотеки `REST-assured`. При проектировании тестов были применены модели с использованием библиотеки `lombok`. 

В качестве фреймворка для запуска тестов используется `Junit5`, в качестве сборщика проекта - `Gradle`.

Произведена настройка CI системы `Jenkins`, по результатам каждого запуска автотестов создаётся `Allure` отчёт с использованием rest-assured listener с custom templates для лучшей читаемости.

Реализована интеграция с `Allure TestOps` – системой тест-менеджмента для управления процессом тестирования, которая, в свою очередь, интегрирована с таск-трекером `Jira`.

После выполнения автотестов `Telegram` бот присылает сообщение с информацией о результатах запуска.

____
<a id="cases"></a>
## **Тестовое покрытие:**
____

#### Логин пользователя

- [x] Успешный логин пользователя
- [x] Неуспешный логин пользователя - отсутствует email и password
- [x] Неуспешный логин пользователя - отсутствует password
- [x] Неуспешный логин пользователя - отсутствует email
- [x] Неуспешный логин несуществующего пользователя
- [x] Неуспешный логин пользователя - некорректное тело запроса
- [x] Неуспешный логин пользователя - отсутствует тело запроса и ContentType

#### Получение данных ресурса

- [x] Получение данных существующего ресурса
- [x] Получение данных несуществующего ресурса - ресурс не найден

#### Получение данных пользователя

- [x] Получение данных существующего пользователя
- [x] Получение данных несуществующего пользователя - пользователь не найден

#### Удаление пользователя

- [x] Успешное удаление пользователя

____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/jenkins.svg" width="25"/> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/C34-Vicktalina-unit23-2/)
____
<p align="center">  
<img src="images/screenshot/jenkins_build.png" alt="Jenkins" width="950"/>
</p>

### **Параметры сборки в Jenkins:**

- `TASK` (набор автотестов с соответствующими тегами: `all_reqres_api_tests` - все API тесты, `login_reqres_api_tests` - тесты на логин)

<a id="console"></a>
## Команды для запуска из терминала
___
***Локальный запуск:***
```bash  
gradle clean test
```

***Удалённый запуск через Jenkins:***
```bash  
clean ${TASK}
```
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/allure.svg" width="25"/> Allure [отчет](https://jenkins.autotests.cloud/job/C34-Vicktalina-unit23-2/4/allure/)
___

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screenshot/allure_report.png" width="850"/>  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="images/screenshot/allure_report_test.png" width="850"/>  
</p>

### *Графики*

<p align="center">  
<img title="Allure Graphics" src="images/screenshot/allure_report_graphs.png" width="850"/>
</p>

____

<a id="allure-testOps"></a>
## <img alt="TestOps" height="25" src="images/logo/allure-testOps.svg" width="25"/> Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/4819/dashboards)

### Основная страница Allure TestOps

<p align="center">  
<img title="Allure TestOps main" src="images/screenshot/allure_testops.png" width="850"/>
</p>

### Авто тест-кейсы

<p align="center">  
<img title="Allure TestOps test" src="images/screenshot/allure_testops_test.png" width="850"/>
</p>

___

<a id="jira"></a>
## <img alt="Jira" height="25" src="images/logo/jira.svg" width="25"/> Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-1471)

<p align="center">  
<img title="Jira" src="images/screenshot/jira.png" width="850"/>
</p>

___

<a id="telegram"></a>
## <img alt="Allure" height="25" src="images/logo/telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screenshot/telegram_alert.png" width="550"/>  
</p>


