.PHONY: build run down restart

build:
	mvnw clean package
	docker compose up --build -d

run:
	docker compose up -d

stop:
	docker compose stop

down:
	docker compose down

restart:
	make down
	make build
	make run