package com.example.calculator.mathematicalSection;


public class CalculateByStack {

    Exception Exception = new Exception();              //if it doesn't mean ,send error!
    String[] input;
    public LinkedStack<String> stack = new LinkedStack<>();

    public CalculateByStack(String input) throws Exception {
        this.input = input.split("");
        fillStack();
    }

    void fillStack() throws Exception {
        if(this.input[0].equals("^")||this.input[0].equals("/")||this.input[0].equals(" ")||this.input[0].equals("!"))throw Exception;

        for (int i = this.input.length - 1; i >= 0; i--) {            //fill stack without errors

            if (!this.stack.isEmpty()) {
                if (this.stack.top().equals("(") && (!this.input[i].equals("^") && !this.input[i].equals("/") && !this.input[i].equals("+") && !this.input[i].equals("-") && !this.input[i].equals(" ")))
                    throw Exception;
                if (this.stack.top().equals(")") && (this.input[i].equals("x") || this.input[i].equals("/") || this.input[i].equals("+") || this.input[i].equals("-") || this.input[i].equals("^")))
                    throw Exception;
                if (this.stack.top().equals("!") && this.input[i].equals("(")) throw Exception;
                if ((this.stack.top().equals("x") || this.stack.top().equals("^") || this.stack.top().equals("/")) && (this.input[i].equals("-") || this.input[i].equals("+") || this.input[i].equals("/") || this.input[i].equals("^") || this.input[i].equals("x"))) {
                    throw Exception;
                }
                if (this.stack.top().equals("-") && this.input[i].equals("-")) throw Exception;
                if (this.stack.top().equals("+") && (this.input[i].equals("+") || this.input[i].equals("/") || this.input[i].equals("^") || this.input[i].equals("x"))) {
                    throw Exception;
                }
                if (this.stack.top().equals("-") && this.input[i].equals("+")) this.stack.push(this.stack.pop());
                if (this.stack.top().equals("+") && this.input[i].equals("-")) this.stack.pop();
                if (this.input[i].equals(" ")) continue;
            }
            this.stack.push(this.input[i]);
        }
    }

        public double factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }


    public double calculate(LinkedStack<String> inputStack) throws Exception {
        StringBuilder number = new StringBuilder();
        LinkedStack<Double> helpStack = new LinkedStack<>();
        LinkedStack<String> signs = new LinkedStack<>();

        if (inputStack.top().equals("-"))helpStack.push(Double.valueOf(inputStack.pop()+inputStack.pop()));
        else if(inputStack.top().equals("+"))inputStack.pop();           //- or + of the first

        while (!inputStack.isEmpty()) {
            if (inputStack.top().equals("(")) {
                inputStack.pop();
                LinkedStack<String> parentheses = new LinkedStack<>();
                LinkedStack<String> reverseParentheses = new LinkedStack<>();
                while (!inputStack.top().equals(")")) {         //spilt parentheses and calculate again
                    parentheses.push(inputStack.pop());
                }

                while (!parentheses.isEmpty()) reverseParentheses.push(parentheses.pop());         //reverse

                if (inputStack.isEmpty()) throw Exception;
                inputStack.pop();
                number.append(calculate(reverseParentheses));

            } else if (inputStack.top().equals("!")) {           //calculate factorial first
                inputStack.pop();
                if (number.isEmpty())number = new StringBuilder(String.valueOf(helpStack.top()));   //for repeated factorial

                double fact = Double.parseDouble(number.toString());
                if (fact % 1 != 0) throw Exception;              //error if it's not an integer

                helpStack.push(factorial((int) fact));            //calculate factorial
                number=new StringBuilder();
            } else if (inputStack.top().equals("^") || inputStack.top().equals("+") || inputStack.top().equals("-") || inputStack.top().equals("/") || inputStack.top().equals("x")) {
                if (!number.isEmpty())
                    helpStack.push(Double.parseDouble(number.toString()));       //spilt numbers from signs
                signs.push(inputStack.pop());
                number = new StringBuilder();
            } else if (inputStack.top().equals("e")) {             //replace the value of e
                inputStack.pop();
                number.append(Math.E);
            } else if (inputStack.top().equals("p")) {           //replace the value of pi
                inputStack.pop();
                inputStack.pop();
                number.append(Math.PI);
            } else number.append(inputStack.pop());               //find numbers
        }

        if (!number.isEmpty()) helpStack.push(Double.parseDouble(number.toString()));              //the last element

        double result=0;


        return result;
    }
}
