package ejbs;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Stateless
@Entity
public class Calculator implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private int number1;
	private int number2;
	private String operation;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setNum1(int num1) {
		this.number1 = num1;
	}

	public int getNum1() {
		return number1;
	}

	public void setNum2(int num2) {
		this.number2 = num2;
	}

	public int getNum2() {
		return number2;
	}

	public void setOp(String op) {
		this.operation = op;
	}

	public String getOp() {
		return operation;
	}

	public int performCalculation(int number1, int number2, String operation) {
		
		switch (operation) {
		case "+":
			return number1 + number2;
		case "-":
			return number1 - number2;
		case "*":
			return number1 * number2;
		case "/":
			if (number2 != 0) {
				return number1 / number2;
			} else {
				throw new ArithmeticException("Division by zero");
			}
		default:
			throw new IllegalArgumentException("Invalid operation: " + operation);
		}
	}
}
