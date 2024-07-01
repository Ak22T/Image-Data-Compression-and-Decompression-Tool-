import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageCompressor {
    public static void compressImage(String imagePath, String outputFilePath) throws IOException {
        int[][] pixelData = ImageReader.readImage(imagePath);
        Node huffmanTree = HuffmanTree.buildHuffmanTree(pixelData);
        Map<Integer, String> codebook = new HashMap<>();
        HuffmanTree.generateCodes(huffmanTree, "", codebook);
        String compressedData = encodeImage(pixelData, codebook);

        // Save the codebook and compressed data to a file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFilePath))) {
            oos.writeObject(codebook);
            oos.writeObject(compressedData);
        }
    }

    private static String encodeImage(int[][] pixelData, Map<Integer, String> codebook) {
        StringBuilder encodedData = new StringBuilder();
        for (int[] row : pixelData) {
            for (int pixel : row) {
                encodedData.append(codebook.get(pixel));
            }
        }
        return encodedData.toString();
    }
}
