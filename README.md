# Stack-based-Calculator 

## Overview 

This program implements a **calculator** that performs mathematical operations using a **stack-based** approach. It processes mathematical expressions like addition, subtraction, multiplication, division, exponentiation, and factorials. The program utilizes a custom `LinkedStack` class to manage operations and follows standard **operator precedence** rules.

## Features 

- **Basic Arithmetic** : Supports addition, subtraction, multiplication, division, and exponentiation.
- **Factorial Calculation** : Computes the factorial of integers.
- **Parentheses Handling** : Handles expressions with parentheses correctly.
- **Constants** : Supports constants like Euler's number (`e`) and pi (`p`).
- **Operator Precedence** : Correctly evaluates operations in the order of precedence using stacks.

### Key Methods 

1. **`fillStack()`**:
   - Fills the stack with the individual elements of the input expression.
   - Validates the expression and ensures that the operators and operands are in a valid sequence.

2. **`factorial(int n)`**:
   - Computes the factorial of a given integer `n`.

3. **`calcPowers()`**:
   - Evaluates exponentiation (`^`) operations.

4. **`calcMulAndDiv()`**:
   - Handles multiplication (`x`) and division (`/`) operations.

5. **`calcPlusAndSub()`**:
   - Handles addition (`+`) and subtraction (`-`) operations.

6. **`calculate()`**:
   - The main calculation function that processes the input expression and returns the result.
   - It manages the entire sequence of operations by calling helper functions like `calcPowers`, `calcMulAndDiv`, and `calcPlusAndSub`.

## Error Handling 

- The calculator throws an **exception** if the input expression contains invalid characters or malformed operations. For example:
  - Operators cannot be placed at the beginning or end of an expression.
  - Invalid usage of factorial (`!`) or mismatched parentheses will throw an exception.

### Input Format 
- The input is a **string** containing the mathematical expression to be evaluated.
- **Operators**: `+`, `-`, `x`, `/`, `^`, `!`
- **Constants**: `e` for Euler's number, `p` for pi
- **Parentheses**: `()` for grouping
- Example: `"3+5*(2^3)-6"`

### Output Format 
- The output will be the **result** of the expression after evaluation.

## Dependencies 
- The program requires a `LinkedStack` class that is used to manage the stack operations. Ensure this class is defined in your project.
