# Cron Expression Parser

## About The Project

This project is a Cron Expression Parser console application that parses a cron string and expands each field to show the times at which it will run. The application accepts a standard cron format, which includes five time fields: minute, hour, day of month, month, and day of week, along with a command.

### Cron Format Overview:
- **Minute**: 0-59
- **Hour**: 0-23
- **Day of Month**: 1-31
- **Month**: 1-12
- **Day of Week**: 0-6 (Sunday = 0 or 7)

## Prerequisites

Ensure you have the following installed on your system:

- **Java Version**: 23
- **Maven Version**: 3.9.9

## Installation

To get started with the Cron Expression Parser project, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/rapveen/CronParser.git
   cd CronParser
2. Build the project using Maven:
    ```bash
   mvn clean install

This will create a jar file in the `target` directory.

Navigate to the target directory.

```bash
cd target
```
Run the jar file with a cron expression:

```bash
java -jar cronparser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
````
### Example Output:
```bash
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```
## Running Tests
To run the unit tests for this project, use the following Maven command:

```bash 
    mvn test
```

This will execute all the tests and provide a summary of the test results.

### Tests Included
For detailed information about the test cases, refer to the `testcase.md` file.