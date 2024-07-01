

class Node implements Comparable<Node> {
    int frequency;
    int value;
    Node left, right;

    Node(int frequency, int value) {
        this.frequency = frequency;
        this.value = value;
    }

    Node(int frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.frequency, o.frequency);
    }
}
