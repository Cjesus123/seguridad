from flask import Flask
import socket
from py_eureka_client import eureka_client

import config

# Configura el nombre de host manualmente
hostname = socket.gethostname()

eureka_client.init(eureka_server=config.EUREKA_SERVER,
                   app_name=config.APPLICATION_NAME,
                   instance_port=5000,
                   instance_host=hostname)

app = Flask(__name__)


@app.route('/')
def hello():
    return "Hello from Python Service!"


if __name__ == "__main__":
    app.run(port=5000)
