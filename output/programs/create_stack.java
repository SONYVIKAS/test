  public class Stack<T> {
      private LinkedList<T> items;
      private LinkedList<T> minStack;

      public Stack() {
          this.items = new LinkedList<T>();
          this.minStack = new LinkedList<T>();
      }

      @Override
      public String toString() {
          if (this.items.isEmpty()) {
              return "Stack (empty)";
          } else {
              return "Stack tail=" + this.items.getLast() + " length=" + this.items.size();
          }
      }

      public void push(T item) {
          this.items.add(item);

          if (this.minStack.isEmpty() || this.minStack.getLast() > item) {
              this.minStack.add(item);
          } else {
              this.minStack.add(this.minStack.getLast());
          }
      }

      public T pop() {
          if (this.isEmpty()) {
              throw new IndexOutOfBoundsException("pop from empty list");
          }

          this.minStack.removeLast();
          return this.items.removeLast();
      }

      public Iterator<T> iterator() {
          return new Iterator<T>() {
              @Override
              public boolean hasNext() {
                  return!Stack.this.isEmpty();
              }

              @Override
              public T next() {
                  if (!hasNext()) {
                      throw new NoSuchElementException();
                  }
                  return Stack.this.pop();
              }
          };
      }

      public int length() {
          return this.items.size();
      }

      public void empty() {
          this.items.clear();
          this.minStack.clear();
      }

      public boolean isEmpty() {
          return this.items.isEmpty();
      }

      public T findMin() {
          if (!this.isEmpty()) {
              return this.minStack.getLast();
          }
          return null;
      }
  }