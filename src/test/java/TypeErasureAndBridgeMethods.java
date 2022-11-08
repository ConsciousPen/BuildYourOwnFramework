import java.io.Serializable;

public class TypeErasureAndBridgeMethods {
    /**
     *      Bridge methods are usually occur when inheritance used
     *  it is an additional method added during type erasure in order to avoid ambigious situations
     ---------------------- BEFORE TYPE ERASURE ----------------------
     class Node<T> {
        private T data;

        public Node(T data) {
            this.data = data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    class MyNode extends Node<Integer> {
        public MyNode(Integer data) {
            super(data);
        }

        public void setData(Integer data) {
            super.setData(data);
        }
    }
    ---------------------- AFTER TYPE ERASURE ----------------------
    class Node {
        private Object data;

        public Node(Object data) {
            this.data = data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    class MyNode extends Node {
        public MyNode(Integer data) {
            super(data);
        }

        public void setData(Integer data) {
            super.setData(data);
        }
    }
    ----------------------                    ----------------------
            As you can see after type erasure there is a problem :
     setData method arguments wont match.
     The Java compiler is going to create a method in order to solve the problem
     -> this is the bridge methods
     */


    public static void main(String[] args) {

    }

}
