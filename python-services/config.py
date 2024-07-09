import os

PORT = int(os.environ.get('PORT', os.environ.get('SERVER_PORT', 5000)))

EUREKA_SERVER = "http://localhost:8761/eureka"
APPLICATION_NAME = "python-service"

ZIPKIN_URL = "http://localhost:9411"

DATABASE_URL = "mysql://root:12345@localhost:3306/database"

SQLALCHEMY_DATABASE_URI = DATABASE_URL
SQLALCHEMY_TRACK_MODIFICATIONS = False

# Configuración adicional de SQLAlchemy si es necesaria
SQLALCHEMY_ECHO = True  # Equivalente a show-sql: true