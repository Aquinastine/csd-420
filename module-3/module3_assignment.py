class Stack:
    def __init__(self):
        self.items = []
    def push(self, item):
        self.items.append(item)
    def pop(self):
        if not self.is_empty():
            return self.items.pop()
        return None
    def peek(self):
        if not self.is_empty():
            return self.items[-1]
        return None
    def is_empty(self):
        return len(self.items) == 0
    def length(self):
        return len(self.items)
    def list(self):
        return(self.items)

class QueueUsingStacks:
    """
    A queue implementation using two stacks.
    """
    def __init__(self):
        self.in_stack = []
        self.out_stack = []

    def enqueue(self, item):
        self.in_stack.append(item)

    def dequeue(self):
        if not self.out_stack:
            while self.in_stack:
                self.out_stack.append(self.in_stack.pop())
        return self.out_stack.pop() if self.out_stack else None

def validate_brackets(string):
    """
    Check if the brackets in the given string are balanced.
    Returns True if balanced, False otherwise.
    """
    #create a bracket stack
    bracketStack = Stack()
    
    bracketStack = Stack()
    bracketMatches = {")": "(", "]": "[", "}": "{"}

    for char in string:
        if char in bracketMatches.values():  # Opening brackets
            bracketStack.push(char)
        elif char in bracketMatches:  # Closing brackets
            if bracketStack.is_empty() or bracketStack.pop() != bracketMatches[char]:
                return False

    return bracketStack.is_empty()

    
def next_greater_element(nums):
    """
    Given a list of numbers, for each element find the next greater element and return their list.
    If no greater element exists for an element, use -1 instead.
    """
    result = [-1] * len(nums)  # Initialize result with -1s
    stack = Stack()  # Stack to store indices

    for i in range(len(nums)):
        # While stack is not empty and current element is greater than the one at index on top of stack
        while not stack.is_empty() and nums[i] > nums[stack.peek()]:
            index = stack.pop()
            result[index] = nums[i]
        stack.push(i)  # Push current index onto stack

    return result

# Add a third function here for the students to implement
def reverse_stack(stack):
    """
    Reverse the given stack using only push and pop operations.
    The function should return the reversed stack.
    """
    reversedStack = Stack()
    
    try:
        while True:
            reversedStack.push(stack.pop())
    except IndexError:
        return reversedStack.list()