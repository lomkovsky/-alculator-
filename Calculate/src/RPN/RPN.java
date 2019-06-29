package RPN;

import java.util.Stack;

public class RPN {
	// "Input" class method
	static public double Calculate(String input) {
		String output = GetExpression(input); // Convert expression to postfix notation
		double result = Counting(output); // We solve the resulting expression
		return result; // Return the result
	}

	// A method that converts an input string with an expression to a postfix notation
	static private String GetExpression(String input) {
		String output = ""; // String to store the expression
		Stack<Character> operStack = new Stack<Character>(); // Stack for storage of operators

		for (int i = 0; i < input.length(); i++) //For each character in the input string
		{
			// Delimiters skip
			if (IsDelimeter(input.charAt(i)))
				continue; // Go to the next character

			// If the character is a digit, then we read the entire number.
			if (Character.isDigit(input.charAt(i))) // If the figure
			{
				// We read to the separator or operator to get the number
				while (!IsDelimeter(input.charAt(i)) && !IsOperator(input.charAt(i))) {
					output += input.charAt(i); // Add each digit of the number to our line
					i++; // Go to the next character

					if (i == input.length())
						break; // If the character is the last one, then exit the loop
				}

				output += " "; // We add after the number a space in the string with the expression
				i--; // Go back one character to the character in front of the separator
			}

			// If the symbol is an operator
			if (IsOperator(input.charAt(i))) // If operator
			{
				if (input.charAt(i) == '(') // If the character is an opening bracket
					operStack.push(input.charAt(i)); // Write it to the stack
				else if (input.charAt(i) == ')') // If the character is a closing bracket
				{
					// We write out all operators to the opening bracket in the string
					Character s = operStack.pop();

					while (s != '(') {
						output += s.toString() + ' ';
						s = operStack.pop();
					}
				} else // If any other operator
				{
					if (!operStack.isEmpty()) // Åñëè â ñòåêå åñòü ýëåìåíòû
						if (GetPriority(input.charAt(i)) <= GetPriority(operStack.peek())) 
							// And if the priority of our operator is less than or equal to the priority of the operator at the top of the stack
							output += operStack.pop().toString() + " "; // Then add the last statement from the stack to the string with the expression
					Character ci = input.charAt(i);
					// operStack.Push(Character.Parse(ci.toString())); 
					// If the stack is empty, or the operator’s priority is higher, we add operators to the top of the stack
					operStack.push(ci);
				}
			}
		}

		// When we have passed through all the characters, we throw out from the stack all the remaining operators in the string
		while (!operStack.isEmpty())
			output += operStack.pop() + " ";

		return output; // Return an expression in a postfix entry
	}

	// Method that computes the value of an expression already converted to a postfix notation
	static private double Counting(String input) {
		double result = 0; // Result
		Stack<Double> temp = new Stack<Double>(); // Solution stack

		for (int i = 0; i < input.length(); i++) // For each character in the string
		{
			// If the character is a digit, then we read the entire number and write it to the top of the stack
			if (Character.isDigit(input.charAt(i))) {
				String a = "";

				while (!IsDelimeter(input.charAt(i)) && !IsOperator(input.charAt(i))) // Not yet a separator
				{
					a += input.charAt(i); // Add
					i++;
					if (i == input.length())
						break;
				}
				temp.push(Double.parseDouble(a)); // Write to the stack
				i--;
			} else if (IsOperator(input.charAt(i))) // If the symbol is an operator
			{
				// We take the last two values from the stack
				Double a = temp.pop();
				Double b = temp.pop();

				switch (input.charAt(i)) // And we make action on them, according to the operator
				{
				case '+':
					result = b + a;
					break;
				case '-':
					result = b - a;
					break;
				case '*':
					result = b * a;
					break;
				case '/':
					result = b / a;
					break;
				}
				temp.push(result); // The result of the calculation is written back to the stack
			}
		}
		return temp.peek(); // Pick up the result of all calculations from the stack and return it
	}

	// The method returns true if the character being checked is a separator ("space" or "equal to")
	static private Boolean IsDelimeter(char c) {
		if ((" =".indexOf(c) != -1))
			return true;
		return false;
	}

	// The method returns true if the character being tested is an operator
	static private Boolean IsOperator(char ñ) {
		if (("+-/*^()".indexOf(ñ) != -1))
			return true;
		return false;
	}

	// Method returns operator priority
	static private byte GetPriority(char s) {
		switch (s) {
		case '(':
			return 0;
		case ')':
			return 1;
		case '+':
			return 2;
		case '-':
			return 3;
		case '*':
			return 4;
		case '/':
			return 4;
		case '^':
			return 5;
		default:
			return 6;
		}
	}
}
