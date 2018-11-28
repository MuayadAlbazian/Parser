
//Compile in Athena ----> javac Parser.java
//Run in Athena ---> java Parser
//Used example parser from blackboard written by Scott Gordon and modified it
import java.util.Scanner;
import java.lang.String;


public class Parser {
	
	public static int index = 0;
	public static int error = 0;
	public static String inputStr;
	
	public static void main(String[] args) { 
	
		Parser grammer = new Parser();
		System.out.println("Your input stream of tokens: ");
		Scanner input = new Scanner(System.in);
		inputStr = input.nextLine();
		
		grammer.program();
		Parser m = new Parser();
		m.match('$');
		
		if (error == 0) {
			System.out.println("legal"); 
			}
		else { System.out.println("errors found"); }
			
		}
	
	private char currentToken() {
		return inputStr.charAt(index);
	}
	private void match(char token) {
		if (token == currentToken()) {
			next();
		}
		else error();
	}
	
	private void error()
	{
		System.out.println("error at position: " + index + " Token: " + currentToken());
		error = 1;
		next();
	}
	private void next() {
		if (index < (inputStr.length() - 1)) {
			index++;
		}
	}
	
// NON TERMINALS
	
	private void program() {
		match('B');
		while (currentToken() == 'A' || currentToken() == 'G') 
			deflst();
		match('D');
		while(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z' || currentToken() == 'S' ||
				currentToken() == 'S' || currentToken() == 'P' || currentToken() == 'L' || currentToken() == 'C')
			statmnt();
		match('F');
		match(';');
			
	}
	
	private void deflst() {
		if(currentToken() == 'A' || currentToken() == 'G')
			vardef();
		else error();
		match(';');
		while (currentToken() == 'A' || currentToken() == 'G') 
			vardef();
			match(';');
		match('Q');
		
	}
	
	private void vardef() {
		if(currentToken() == 'A' || currentToken() == 'G') 
			typesym();
		else error();
		if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
			varlst();
		else error();
	}
	private void typesym() {
		if(currentToken() == 'A' || currentToken() == 'G')
		match(currentToken());
		else error();
	}
	private void varlst() {
		if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
			ident();
		else error();
		while(currentToken() == ',') 
			match(',');
			if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
				ident();
			else error();
	}
	private void statmnt( ) {
		if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
			asgnstmt();
		else if(currentToken() == 'S')
			ifstmt();
		else if(currentToken() == 'P')
			loop();
		else if(currentToken() == 'L')
			input();
		else if(currentToken( )== 'C');
			write();
	}
	private void asgnstmt(){
		if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
			ident();
		else error();
		match('#');
		while(currentToken() == '<' || currentToken() == '>' || currentToken() == '=' || currentToken() == '?')
			expr();
		match(';');
	}
	private void ifstmt()  {
		match('S');
		if(currentToken() == '(')
			comp();
		else error();
		match('&');
		while(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z' || currentToken() == 'S' ||
				currentToken() == 'S' || currentToken() == 'P' || currentToken() == 'L' || currentToken() == 'C')
			statmnt();
		if(currentToken() == 'O') {
			while(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z' || currentToken() == 'S' ||
			currentToken() == 'S' || currentToken() == 'P' || currentToken() == 'L' || currentToken() == 'C')
				statmnt();
		}
		else error();
		match('H');
		
	}
	private void loop() {
		match('P');
		if(currentToken() == '(')
			comp();
		else error();
		match('U');
		while(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z' || currentToken() == 'S' ||
				currentToken() == 'S' || currentToken() == 'P' || currentToken() == 'L' || currentToken() == 'C')
			statmnt();
		match('E');	
	}
	public void input( ) {
		match('L');
		if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
			ident();
		else error();
		while(currentToken() == ',') {
			if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
				ident();
			else error();
		}
		
		match(';');
	}
	public void write( ) {
		match('C');
		if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
			ident();
		else error();
		while(currentToken() == ',') {
			if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
				ident();
			else error();
		}
		match(';');
	}
	public void comp() {
		match('(');
		if(currentToken() == '0' || currentToken() == '1' || currentToken() == 'X' || currentToken() == 'Y' ||
				currentToken() == 'Z' || currentToken() == '(')
			operd();
		else error();
		if(currentToken() == '<' || currentToken() == '>' || currentToken() == '=' || currentToken() == '?' )
			oper();
		else error();
		if(currentToken() == '0' || currentToken() == '1' || currentToken() == 'X' || currentToken() == 'Y' ||
				currentToken() == 'Z' || currentToken() == '(')
			operd();
		else error();
		match(')');
	}
	public void expr() {
		if(currentToken() == '0' || currentToken() == '1' || currentToken() == 'X' || currentToken() == 'Y' ||
				currentToken() == 'Z' || currentToken() == '(')
			term();
		else error();
		while(currentToken() == '+') {
			if(currentToken() == '0' || currentToken() == '1' || currentToken() == 'X' || currentToken() == 'Y' ||
					currentToken() == 'Z' || currentToken() == '(')
				term();
			else error();
		}
		
	}
	public void term() {
		if(currentToken() == '0' || currentToken() == '1' || currentToken() == 'X' || currentToken() == 'Y' ||
				currentToken() == 'Z' || currentToken() == '(')
			operd();
		else error();
		while(currentToken() == '*') {
			if(currentToken() == '0' || currentToken() == '1' || currentToken() == 'X' || currentToken() == 'Y' ||
					currentToken() == 'Z' || currentToken() == '(')
				operd();
			else error();
		}
	
	}
	public void operd() {
		if(currentToken() == '0' || currentToken() == '1')
		Int();
		else if(currentToken() == 'X' || currentToken() == 'Y' || currentToken() == 'Z')
			ident();
		else if(currentToken() == '(') {
			match('(');
			expr();
			match(')');
		}
		else error();
		
	}
	
	public void oper() {
		if(currentToken() == '<' || currentToken() == '>' || currentToken() == '=' || currentToken() == '?')
			match(currentToken());
		 else error(); 
	}
	private void ident() {
		if (currentToken() == 'X' || currentToken() == 'Y'  || currentToken() == 'Z')
			 letter();
		else error();
		while(currentToken() == 'X' || currentToken() == 'Y'  || currentToken() == 'Z' || currentToken() == '0' || currentToken() == '1')
			Char();
	}
	private void Char() {
		if (currentToken() == 'X' || currentToken() == 'Y'  || currentToken() == 'Z')
			 letter();
		else error();
		if (currentToken() == '0' || currentToken() == '1')
			 digit();
		
		
	}
	
	private void Int() {
		if (currentToken() == '0' || currentToken() == '1')
			 digit();
		else error();
		while (currentToken() == '0' || currentToken() == '1')
			 digit();
	}
	
	private void letter() {
		if (currentToken() == 'X' || currentToken() == 'Y'  || currentToken() == 'Z')
			 match(currentToken());
		else error();
		
	}
	private void digit() {
		if (currentToken() == '0' || currentToken() == '1')
			 match(currentToken());
		else error();
	}
	
	
	

}

