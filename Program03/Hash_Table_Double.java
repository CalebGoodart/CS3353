package Program03;

public class Hash_Table_Double {

    private class Node<K, E>{

        K key;
        E data;

        public Node(K key, E data){

            this.key = key;
            this.data = data;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }
    }

    public static class HashTableDouble<E>{

        Node[] arr;
        int MAX;
        int size;
        int q;

        public HashTableDouble(int size, int q){

            arr = new Node[size];
            this.MAX = size;
            this.size = 0;
            this.q = q;
        }


    }

    public static void main(String[] args){


    }
}
