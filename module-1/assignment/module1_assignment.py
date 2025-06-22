def find_max_min(numbers):
    """
    Find the maximum and minimum numbers in a list.
    Return them as a tuple (max, min).
    """
    try:
        #Check to see if the list/iterable is all numbers, if not pass a 
        #message to console and return a tuple with two null values
        #I had to add an allowance for an empty string to past test 5
        is_all_numbers = all(isinstance(x, (int, float)) for x in numbers)
        if is_all_numbers == False and len(numbers) > 0:
            print("please enter a list of numbers")
            return (None, None)
        elif numbers == []: return (None,None)
        
        #Return the min and max
        return (max(numbers),min(numbers))
    
    #Exceptions are reported and null tuple is returned
    except Exception as e:
        print(f"An error occurred: {e}")
        return (None, None)
        

def check_symmetry(string):
    """
    Check if the given string is symmetrical.
    Return True if it is, False otherwise.
    """
    try:
        #Check to make sure we have a string and return false if not
        if not isinstance(string, str):
            print("Input must be a string.")
            return False
        
        #get the middle of the string (whole number division)
        mid = len(string) // 2
        
        #compare the first half of the string to the first half of the
        #reversed string
        return string[:mid] == string[::-1][:mid] 

    
    #Exceptions are reported and false is returned
    except Exception as e:
        print(f"An error occurred: {e}")
        return False

def merge_sorted_lists(list1, list2):
    """
    Merge two sorted lists into a single sorted list.
    Return the merged sorted list.
    """
    try:
        #assuming that the two arguments are ordered iterables, return
        #them sorted
        return sorted(list1 + list2)
    
    #if there is a problem return an empty list and report the error
    except Exception as e:
        print(f"An error occurred: {e}")
        return []

def sum_of_squares(nums):
    """
    Calculate and return the sum of squares of the numbers in the list.
    """
    try:
        #Check to make sure everything is a number if not return null
        if not all(isinstance(x, (int, float)) for x in nums):
            print("Please provide a list of numbers.")
            return None
        
        #return the sume of the squares
        return sum(x ** 2 for x in nums)
    
    #if there is a problem return null and report the error
    except Exception as e:
        print(f"An error occurred: {e}")
        return None

def string_reversal(s):
    """
    Reverse the given string and return it.
    """
    try:
        #Check to make sure the argument is a string, if not return null
        if not isinstance(s, str):
            print("Input must be a string.")
            return None
        #return the reversed string
        return s[::-1]
    
    #if there is a problem return null and report the error
    except Exception as e:
        print(f"An error occurred: {e}")
        return None

def find_second_largest(nums):
    """
    Find and return the second largest number in the list.
    If the list is too short, return None.
    """
    try:
        #Check to make sure everything is a number if not return null
        if not all(isinstance(x, (int, float)) for x in nums):
            print("List must contain only numbers.")
            return None

        #Find the unique numbers by converting the argument into a set,
        #removing and duplicates and save them back into a list so they
        #can be ordered.
        unique_nums = list(set(nums))
        
        #In order to have a second to last there must be at least 2 elements
        if len(unique_nums) < 2: 
            print("List must contain at least two distinct numbers.")
            return None

        #revers sort the list and return the second element
        unique_nums.sort(reverse=True)
        return unique_nums[1]
    
    #if there is a problem return null and report the error
    except Exception as e:
        print(f"An error occurred: {e}")
        return None
    
print(check_symmetry("racecar"))