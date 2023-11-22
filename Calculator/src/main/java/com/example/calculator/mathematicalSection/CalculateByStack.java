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
}
