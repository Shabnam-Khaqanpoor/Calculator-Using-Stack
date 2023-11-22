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
        if(this.input[0].equals("^")||this.input[0].equals("/")||this.input[0].equals(" ")||this.input[0].equals("!")||this.input[0].equals(")"))throw Exception;

        for (int i = this.input.length - 1; i >= 0; i--) {            //fill stack without errors

            if (!this.stack.isEmpty()) {
                if (this.stack.top().equals("(") && (!this.input[i].equals("^") && !this.input[i].equals("/") && !this.input[i].equals("+") && !this.input[i].equals("-") && !this.input[i].equals(" ") && !this.input[i].equals("(")))
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



    public void calcPowers(LinkedStack<String> signs, LinkedStack<Double> helpStack, LinkedStack<Double> inputStack, LinkedStack<String> signs2) {
        while (!signs.isEmpty()) {
            double popElement = inputStack.pop();                    //calculate power
            if (signs.top().equals("^")) {
                inputStack.push(Math.pow(inputStack.pop(),popElement));
                signs.pop();

            } else {              //spilt others functions
                helpStack.push(popElement);
                signs2.push(signs.pop());
            }

        }

        if (!inputStack.isEmpty()) helpStack.push(inputStack.pop());

        while (!signs.isEmpty()) {                   //do it till finished powers
            if (inputStack.isEmpty()) {
                while (!helpStack.isEmpty()) inputStack.push(helpStack.pop());
            }
            calcPowers(signs, inputStack, helpStack, signs2);
        }

    }

    public void calcMulAndDiv(LinkedStack<String> signs, LinkedStack<Double> helpStack, LinkedStack<Double> inputStack, LinkedStack<String> signs2) {
        while (!signs.isEmpty()) {
            double popElement = helpStack.pop();     //calculate * and /
            if (signs.top().equals("x")) {
                helpStack.push(popElement * helpStack.pop());
                signs.pop();

            } else if (signs.top().equals("/")) {
                helpStack.push(popElement / helpStack.pop());
                signs.pop();

            } else               //spilt + and -
            {
                inputStack.push(popElement);
                signs2.push(signs.pop());
            }
        }

        if (!helpStack.isEmpty()) inputStack.push(helpStack.pop());

        while (!signs.isEmpty()) {
            if (helpStack.isEmpty()) {
                while (!inputStack.isEmpty()) helpStack.push(inputStack.pop());   //do it till finished * and /
            }
            calcMulAndDiv(signs2, inputStack, helpStack, signs);
        }

    }

    public void calcPlusAndSub(LinkedStack<String> signs, LinkedStack<Double> helpStack,LinkedStack<String> signs2, LinkedStack<Double> inputStack) {
        while (!signs2.isEmpty())signs.push(signs2.pop());
        while (!inputStack.isEmpty())helpStack.push(inputStack.pop());

        while (!signs.isEmpty()) {                 //calculate the remain elements
            double popElement = helpStack.pop();
            if (signs.top().equals("+")) {
                helpStack.push(popElement + helpStack.pop());
                signs.pop();

            } else if (signs.top().equals("-")) {
                helpStack.push(popElement-helpStack.pop());
                signs.pop();
            }
        }

    }


    public double calculate(LinkedStack<String> inputStack) throws Exception {
        StringBuilder number = new StringBuilder();
        LinkedStack<Double> helpStack = new LinkedStack<>();
        LinkedStack<String> signs = new LinkedStack<>();
        int counter=0;

        String sub=inputStack.pop();          //check - first of a sentence

        if (sub.equals("-") && !inputStack.top().equals("("))helpStack.push(Double.valueOf(sub+inputStack.pop()));
        else if (sub.equals("-"))number.append(sub);
        else inputStack.push(sub);

        while (!inputStack.isEmpty()) {
            if (inputStack.top().equals("(")) {
                inputStack.pop();
                LinkedStack<String> parentheses = new LinkedStack<>();
                LinkedStack<String> reverseParentheses = new LinkedStack<>();
                while (!(counter ==-1)) {         //spilt parentheses and calculate again
                    if (inputStack.top().equals("("))counter++;
                    if (inputStack.top().equals(")"))counter--;
                    parentheses.push(inputStack.pop());
                }

                parentheses.pop();

                while (!parentheses.isEmpty()) reverseParentheses.push(parentheses.pop());         //reverse


                number.append(calculate(reverseParentheses));

                if (number.charAt(0)=='-'){                         //manage signs before number
                    if (number.charAt(1)=='-')number.delete(0,2);
                    else if (number.charAt(1)=='+')number.deleteCharAt(1);
                } else if (number.charAt(0)=='+')number.deleteCharAt(0);

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

        double result;


                LinkedStack<Double> linkedStack = new LinkedStack<>();

        if (!signs.isEmpty()) {
            LinkedStack<String> signs2 = new LinkedStack<>();

            calcPowers(signs, linkedStack,helpStack, signs2);   //calculate powers

            calcMulAndDiv(signs2, linkedStack, helpStack, signs);   //calculate * and /

            calcPlusAndSub(signs2,linkedStack,signs, helpStack);                  //calculate + and -

            if (helpStack.isEmpty()) result = linkedStack.pop();            //show result
            else result = helpStack.pop();
        } else result = helpStack.pop();


        return result;
    }
}
