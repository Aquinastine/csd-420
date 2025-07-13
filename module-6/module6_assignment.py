class HashTable:
    def __init__(self, size=100):
        """
        Initialize your data structure here.
        """
        self.size = size
        self.table = [[] for _ in range(size)]

    def hash_function(self, key):
        return hash(key) % self.size

    def put(self, key, value):
        """
        Insert a (key, value) pair into the hash table.
        """
        hash_key = self.hash_function(key)
        key_exists = False
        bucket = self.table[hash_key]
        
        for i, (k, v) in enumerate(bucket):
            if key == k:
                bucket[i] = ((key, value))
                return
            
        bucket.append((key, value))

    def get(self, key):
        """
        Retrieve the value associated with the given key.
        """
        hash_key = self.hash_function(key)
        bucket = self.table[hash_key]
        for k, v in bucket:
            if k == key:
                return v
        
        return None

    def remove(self, key):
        """
        Remove the (key, value) pair associated with the given key.
        """
        hash_key = self.hash_function(key)
        bucket = self.table[hash_key]
        for i, kv in enumerate(bucket):
            k, _ = kv
            if key == k:
                del bucket[i]

def is_anagram(s1, s2):
    """
    Check if two strings are anagrams of each other.
    """
    return sorted(s1) == sorted(s2)
