

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTree {
    public static Node buildHuffmanTree(int[][] pixelData) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int[] row : pixelData) {
            for (int pixel : row) {
                frequencyMap.put(pixel, frequencyMap.getOrDefault(pixel, 0) + 1);
            }
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new Node(entry.getValue(), entry.getKey()));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            Node parent = new Node(left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }

        return priorityQueue.poll();
    }

    public static void generateCodes(Node node, String prefix, Map<Integer, String> codebook) {
        if (node.left == null && node.right == null) {
            codebook.put(node.value, prefix);
            return;
        }
        generateCodes(node.left, prefix + "0", codebook);
        generateCodes(node.right, prefix + "1", codebook);
    }
}
