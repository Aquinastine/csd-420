import heapq

class MinHeap:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.heap = []

    def insert(self, element):
        """
        Inserts an element into the heap.
        """
        self.heap.append(element)
        self._bubble_up(len(self.heap) - 1)
        #print("DEBUG: Inserting", element)
        #print("DEBUG: Heap now:", self.heap)

    def extract_min(self):
        """
        Removes and returns the minimum element from the heap.
        """
        if not self.heap:
            return None
        self._swap(0, len(self.heap) - 1)
        min_element = self.heap.pop()
        self._bubble_down(0)
        return min_element

    def get_min(self):
        """
        Returns the minimum element from the heap without removing it.
        """
        if not self.heap:
            return None
        return self.heap[0]

    def _bubble_up(self, index):
        parent = (index - 1) // 2
        while index > 0 and self.heap[index] < self.heap[parent]:
            self._swap(index, parent)
            index = parent
            parent = (index - 1) // 2

    def _bubble_down(self, index):
        length = len(self.heap)
        smallest = index

        while True:
            left = 2 * index + 1
            right = 2 * index + 2

            if left < length and self.heap[left] < self.heap[smallest]:
                smallest = left

            if right < length and self.heap[right] < self.heap[smallest]:
                smallest = right

            if smallest != index:
                self._swap(index, smallest)
                index = smallest
            else:
                break

    def _swap(self, i, j):
        self.heap[i], self.heap[j] = self.heap[j], self.heap[i]


def find_kth_largest(nums, k):
    min_heap = []
    for num in nums:
        heapq.heappush(min_heap, num)
        if len(min_heap) > k:
            heapq.heappop(min_heap)
    return min_heap[0] if min_heap else None