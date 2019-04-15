package Program03;

public class Hash_Table_Linear {

    private static class Node<K, E>{

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

    public static class HashTableLinear<E>{

        Node[] arr;
        int size;
        int MAX;

        public HashTableLinear(int size){

            arr = new Node[size];

            this.MAX = size;
            this.size = 0;

        }

        public void add(int key, E data){

            int index = hashCode(key);
            Node<Integer, E> node = new Node<>(key, data);

            if (arr[index] == null){

                arr[index] = node;
                size++;

            }else {

                for (int i = 1; arr[index] != null; i++){

                    index = hashCode(key + i);
                }
                arr[index] = node;
                size++;
            }
        }

        public void remove(int key){

            int index = hashCode(key);

            if (arr[index].getKey().equals(key)){

                arr[index] = null;
                size--;

            }else{
                for (int i = 1; !arr[index].getKey().equals(key); i++){
                    if (i < MAX){
                        throw new ArrayIndexOutOfBoundsException();
                    }

                    index = hashCode(key + i);
                }
                arr[index] = null;
                size--;
            }
        }

        public int hashCode(int key){

            return key % MAX;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < MAX; i++){

                if (arr[i] == null){
                    builder.append(i);
                    builder.append(": null ");

                }else {

                    builder.append(i);
                    builder.append(":(");
                    builder.append(arr[i].getKey());
                    builder.append(", ");
                    builder.append(arr[i].getData());
                    builder.append("), ");
                }
            }

            return builder.toString();
        }
    }

    public static void main(String[] args){

        HashTableLinear<String> linear = new HashTableLinear<>(10);

        linear.add(10, "Hello");
        linear.add(15, "darkness");
        linear.add(40, "my");
        linear.add(84, "old");
        linear.add(180, "Friend");

        System.out.print(linear);


    }
}
