# Notes I'll probably forget

## Static methods
- Static methods can't access instance methods and instance variables directly.
- They are usually used for operations that don't require any data from an instance of the class (from 'this')
    - Can't use 'this'

- If you see a method that **does not use instance variables** that method should be declared as a static method.
- Reference: https://prnt.sc/tpmn4c

## Instance methods
- Can access instance methods and instance variables directly.
- Can also access static methods and static variables directly.
- Example: https://prnt.sc/tqe925

## Static Variables
- Shared through instances
