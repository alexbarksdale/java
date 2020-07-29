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

## Abstract class use case
- You want to share code among serveral closely related classes
- Expect classes that extend your abstract class to have many commono methods or fields or required access modifiers other than public (protect, private)
- You want to declare non static or non final fields this enables you to define methods that can access and modify the state of an object (get and set)
- When you have a requirement for your base class to provide a default implementation of certain methods but other methods should be open to being overridden by child classes.
- In short: It should provide a common definition of a base class that multiple derived classes can share.

## Interface use case
- You expect that unrelated classes will implement your interface. For example, the interfaces Comparable and Cloneable are implemented by many unrelated classes.
- You want to specify the behavior of a particular data type, but you are not concerned about who implements its behavior.
- You want to share different behavior.
