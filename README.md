# 🎨 Facemash_1

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-7.6-blue?logo=gradle&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-Red?logoColor=white)
![GitHub Repo](https://img.shields.io/badge/GitHub-Repo-black?logo=github&logoColor=white)

Facemash_1 — это веб-приложение, где пользователи выбирают, какая из двух предложенных картинок лучше.  
Приложение создано на **Spring Boot**, с безопасностью через **Spring Security**, базой данных **PostgreSQL** и упрощением кода с помощью **Lombok**.  

> ⚠️ Внимание: на данный момент система рейтингов **Elo не работает корректно** — исправление в процессе.

---

## 🚀 Установка и запуск

1. Клонируйте репозиторий:

```bash
git clone https://github.com/Lev2011-karcev/facemash_1.git

	2.	Настройте базу данных PostgreSQL и создайте БД для приложения.
	3.	Откройте файл src/main/resources/application.properties и укажите параметры подключения к БД:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

	4.	Соберите и запустите проект через Gradle:

./gradlew bootRun

	5.	Перейдите в браузере по адресу:

http://localhost:8080


⸻

⚙️ Функционал
	•	👤 Пользователи выбирают, какая картинка лучше.
	•	🖼️ Администраторы могут добавлять новые картинки.
	•	🏆 Система рейтингов Elo временно не работает.

⸻

🛠 Используемые технологии
	•	🟢 Java 23 / Spring Boot 3
	•	🔒 Spring Security авторизация админов(определенные ip адреса)
	•	🐘 PostgreSQL — хранение данных
	•	✨ Lombok — генерация геттеров/сеттеров и упрощение кода
	•	⚙️ Gradle — сборка проекта

⸻

📌 Бейджи

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-7.6-blue?logo=gradle&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-Red?logoColor=white)


⸻

📄
💡 Идеи для дальнейшего развития
	•	⚡ Исправить работу системы Elo для корректного расчета рейтинга.
	•	📸 Добавить отображение истории голосований для пользователей.
⸻

🙌 Контакты
	•	GitHub: Lev2011-karcev
	•	Email: lev.kartsev.00@mail.ru

⸻

