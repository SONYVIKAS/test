 2. The function `get_products_of_all_ints_except_at_index_optimized(lst)` takes a list of integers and returns a list of products of all integers except the integer at that index. It does not use division.
 3. The function `get_products_of_all_ints_except_at_index(lst)` has a runtime complexity of O(n^2) and a space complexity of O(n^2).
 4. The function `get_products_of_all_ints_except_at_index_optimized(lst)` has a runtime complexity of O(n) and a space complexity of O(n).
 5. The function `get_products_of_all_ints_except_at_index_optimized(lst)` uses two for loops to calculate the products before and after each index, and then multiplies them to get the final result.
 6. The function `get_products_of_all_ints_except_at_index_optimized(lst)` uses a list comprehension to create a new list with the products of the before and after lists.
 7. The function `get_products_of_all_ints_except_at_index_optimized(lst)` uses a for loop to iterate over the products list and append each product to the final result list.
 8. The function `get_products_of_all_ints_except_at_index_optimized(lst)` returns the final result list.
 9. The function `get_products_of_all_ints_except_at_index_optimized(lst)` has a runtime complexity of O(n) and a space complexity of O(n).
 10. The function `get_products_of_all_ints_except_at_index_optimized(lst)` uses two for loops to calculate the products before and after each index, and then multiplies them to get the final result.
 11. The function `get_products_of_all_ints_except_at_index_optimized(lst)` uses a list comprehension to create a new list with the products of the before and after lists.
 12. The function `get_products_of_all_ints_except_at_index_optimized(lst)` uses a for loop to iterate over the products list and append each product to the final result list.
 13. The function `get_products_of_all_ints_except_at_index_optimized(lst)` returns the final result list.

```python
def get_products_of_all_ints_except_at_index(lst):
    """ Takes a list of integers and returns a list of the products except the integer at that index. Do not use division.

    >>> get_products_of_all_ints_except_at_index([1, 7, 3, 4])
    [84, 12, 28, 21]

    >>> get_products_of_all_ints_except_at_index([1, 0, 3, 4])
    [0, 12, 0, 0]
    """

    # Runtime: O(n^2)
    # Spacetime: O(n^2)

    products = []

    for a in range(len(lst)):
        product = 1

        for b in range(len(lst)):
            if a!= b:
                product *= lst[b]
        products.append(product)

    return products


def get_products_of_all_ints_except_at_index_optimized(lst):
    """ Takes a list of integers and returns a list of the products except the integer at that index. Do not use division.

    >>> get_products_of_all_ints_except_at_index_optimized([1, 7, 3, 4])
    [84, 12, 28, 21]

    >>> get_products_of_all_ints_except_at_index_optimized([1, 0, 3, 4])
    [0, 12, 0, 0]
    """

    # Runtime: O(n)
    # Spacetime: O(n)

    products = []
    product = 1
    product_reverse = 1
    products_before = []
    products_after = []

    for i in range(len(lst)):
        products_before.append(product)
        product *= lst[i]
