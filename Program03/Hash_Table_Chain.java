package Program03;


public class Hash_Table_Chain {

    private static class Node<K, E> {

        K key;
        E data;
        Node next;

        public Node(K key, E data) {

            this.key = key;
            this.data = data;
            this.next = null;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        public K getKey() {
            return key;
        }

        public E getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public boolean hasNext() {
            return this.next != null;
        }
    }

    public static class HashTableChain<E> {

        Node[] arr;
        int size;
        int MAX;

        public HashTableChain(int size) {

            arr = new Node[size];
            this.size = 0;
            this.MAX = size;
        }

        public void insert(int key, E data) {

            Node<Integer, E> node = new Node<>(key, data);
            int index = hashfunc(key);

            if (size == MAX) {
                return;
            }else if (arr[index] == null) {

                arr[index] = node;

            } else {

                Node curr = arr[index];

                while (curr.hasNext()) {

                    curr = curr.getNext();
                }

                curr.setNext(node);
            }


        }

        public void delete(int key) {

            int index = hashfunc(key);

            if (arr[index].getKey().equals(key)) {   // Case 1: first in chain is key

                arr[index] = (arr[index].hasNext()) ? arr[index] = arr[index].getNext() : null;
                size--;

            } else {     // Case 2; key should be elsewhere in the chain

                Node curr = arr[index].getNext();
                Node pev = arr[index];
                while (curr.hasNext()) {

                    if (curr.getKey().equals(key)) {

                        pev.setNext(curr.getNext());
                        size--;
                        return;
                    }

                    pev = curr;
                    curr = curr.getNext();
                }

                throw new ArrayIndexOutOfBoundsException(); // key is does not exist
            }

        }

        public boolean search(int key) {

            int index = hashfunc(key);
            Node curr = arr[index];

            while (curr.hasNext()) {

                if (curr.getKey().equals(key)) {
                    return true;
                }
                curr = curr.getNext();
            }

            return false; // key is does not exist
        }

        private int hashfunc(int var) {

            return (var % MAX);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < MAX; i++) {

                builder.append(i);
                builder.append(":(");
                Node curr = arr[i];

                if (curr == null) {     // Case 1: index is null

                    builder.append("null");
                } else if(curr.getNext() == null){      // Case 2: one in chain

                    builder.append("(");
                    builder.append(curr.getKey());
                    builder.append(", ");
                    builder.append(curr.getData());
                    builder.append(")");

                } else{        // Case 3: more then one in chain

                    while (curr.hasNext()) {

                        builder.append("(");
                        builder.append(curr.getKey());
                        builder.append(", ");
                        builder.append(curr.getData());
                        builder.append("), ");
                    }
                }
                builder.append(")\n");
            }
            return builder.toString();
        }

    }

    public static void main(String[] args) {

        HashTableChain<String> chain = new HashTableChain<>(10);

        chain.insert(10, "Hello");
        chain.insert(15, "darkness");
        chain.insert(40, "my");
        chain.insert(84, "old");
        chain.insert(180, "Friend");

        System.out.print(chain);
    }


}
