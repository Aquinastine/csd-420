def array_sum(arr):
    """
    Calculate and return the sum of elements in an array 'arr'.
    """
    try:
        #Check to see if the list/iterable is all numbers, if not pass a 
        #message to console and return a tuple with two null values
        #I had to add an allowance for an empty string to past test 5
        is_all_numbers = all(isinstance(x, (int, float)) for x in arr)
        if is_all_numbers == False and len(arr) > 0:
            print("please enter a list of numbers")
            return False
        
        
        #Initialize the count variable
        sum = 0
        for x in arr: #Loop through each element in the array
            sum += x
        
        return sum
    
    except Exception as e:
        print(f"An error occurred: {e}")
        return None
            
        

def find_middle_node(linked_list):
    """
    Find and return the middle node of a singly linked list.
    If the list has an even number of nodes, return the second middle node.
    """
    try:
   
        fast = linked_list # Start at the head
        slow = linked_list # Start at the head

        while fast and fast.next is not None:
            slow = slow.next  # Move to the next node
            fast = fast.next.next # Move two nodes
        
        return slow
    
    except Exception as e:
        print(f"An error occurred: {e}")
        return None
    

def remove_duplicates_from_sorted_array(arr):
    """
    Given a sorted array, remove the duplicates in-place such that each element appears only once.
    Return the new length of the array.
    """
            
    #check to see if the argument is a list or tuple
    if not isinstance(arr, (list, tuple)):
        raise TypeError("Input must be a list or tuple.")

    if not all(isinstance(x, (int, float, str)) for x in arr):
        raise ValueError("All elements must be of the same and comparable type (int, float, or str).")

    #if nums is empty return 0
    if not arr:
        return 0

    # Convert to list if input is a tuple
    arr = list(arr)

    #Loop through the list and check for duplicates
    insert_pos = 1
    for i in range(1, len(arr)):
        #if the ith element is 
        if arr[i] != arr[i - 1]: 
            arr[insert_pos] = arr[i]
            insert_pos += 1

    return insert_pos
