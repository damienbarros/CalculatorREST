<h1>Calculator challenge</h1>

Functional requirements: </br>
    • RESTful API that exposes the following operations: sum, subtraction, multiplication and division.</br>
    • Offer Support for 2 operands only (a and b, to simplify).</br>
    • Give support to arbitrary precision signed decimal numbers.

How to run: 
1. mvn clean package
2. docker-compose up --build
3. Access localhost:8080 </br>
Example: http://localhost:8080/api/v1/calculate/sum?a=5&b=10
          
