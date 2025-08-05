# Custom HTTP Server

A lightweight HTTP server built from scratch using native Java, demonstrating low-level network programming and HTTP protocol implementation.

## 🚀 Features

- **Pure Java Implementation**: Built entirely with native Java APIs, no external web frameworks
- **Multi-threaded Architecture**: Handles concurrent client connections using worker threads
- **HTTP Protocol Support**: Implements core HTTP/1.1 features
- **Configurable Server**: JSON-based configuration for port and web root settings
- **Request Parsing**: Custom HTTP request parser with proper error handling
- **Status Code Support**: Comprehensive HTTP status code implementation
- **Logging**: Integrated logging using SLF4J and Logback

## 📁 Project Structure

```
src/main/java/org/HttpServer/
├── Main.java                           # Entry point and server startup
├── config/                            # Configuration management
│   ├── Configuration.java             # Configuration data model
│   ├── ConfigurationManager.java      # Configuration loader and manager
│   └── HttpConfigurationException.java # Configuration error handling
├── core/                              # Core server components
│   ├── ServerListenerThread.java      # Main server socket listener
│   └── HttpConnectionWorkerThread.java # Individual connection handler
├── http/                              # HTTP protocol implementation
│   ├── HttpMessage.java               # Base HTTP message structure
│   ├── HttpMethod.java                # Supported HTTP methods (GET, HEAD)
│   ├── HttpParser.java                # HTTP request parser
│   ├── HttpParsingException.java      # Parsing error handling
│   ├── HttpRequest.java               # HTTP request representation
│   └── HttpStatusCodes.java           # HTTP status codes enumeration
└── util/
    └── Json.java                      # JSON utilities
```

## 🛠️ Technologies Used

- **Java 23**: Core language and runtime
- **Maven**: Build automation and dependency management
- **Jackson**: JSON processing library
- **SLF4J + Logback**: Logging framework
- **JUnit 5**: Unit testing framework

## ⚙️ Configuration

The server uses a JSON configuration file located at `src/main/resources/http.json`:

```json
{
  "port": 8080,
  "webroot": "/tmp"
}
```

- **port**: The port number the server listens on (default: 8080)
- **webroot**: The root directory for serving files (default: /tmp)

## 🚦 Getting Started

### Prerequisites

- Java 23 or higher
- Maven 3.6+

### Building the Project

```bash
mvn clean compile
```

### Running the Server

```bash
mvn exec:java -Dexec.mainClass="org.HttpServer.Main"
```

Or compile and run directly:

```bash
mvn clean package
java -cp target/classes org.HttpServer.Main
```

### Testing

Run the unit tests:

```bash
mvn test
```

## 🔧 Supported HTTP Methods

Currently supports:
- **GET**: Retrieve resources from the server
- **HEAD**: Get response headers without body

## 📊 HTTP Status Codes

The server implements various HTTP status codes:

- **400**: Bad Request
- **401**: Method Not Allowed  
- **414**: URL Too Long
- **500**: Internal Server Error
- **501**: Not Implemented

## 🏗️ Architecture

### Multi-threaded Design

1. **ServerListenerThread**: Accepts incoming connections on the configured port
2. **HttpConnectionWorkerThread**: Handles individual client requests in separate threads
3. **HttpParser**: Parses incoming HTTP requests according to HTTP/1.1 specification

### Request Flow

1. Server listens on configured port
2. Accepts client connection
3. Spawns worker thread for each connection
4. Parses HTTP request
5. Processes request and generates response
6. Sends response back to client

## 🧪 Testing

The project includes unit tests for the HTTP parser component. Tests can be found in:
- `src/test/java/org/HttpServer/http/HttpParserTest.java`


## 📄 License

This project is licensed under the terms specified in the LICENSE file.
