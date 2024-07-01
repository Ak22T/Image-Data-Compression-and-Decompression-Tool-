import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ImageDecompressor {
    public static int[][] decompressImage(String inputFilePath) throws IOException, ClassNotFoundException {
         Map<Integer, String> codebook;
        String compressedData;

        // Load the codebook and compressed data from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFilePath))) {
            codebook = (Map<Integer, String>) ois.readObject();
            compressedData = (String) ois.readObject();
        }

        return decodeImage(compressedData, codebook);
    }

    private static int[][] decodeImage(String compressedData, Map<Integer, String> codebook) {
        Map<String, Integer> reverseCodebook = new HashMap<>();
        for (Map.Entry<Integer, String> entry : codebook.entrySet()) {
            reverseCodebook.put(entry.getValue(), entry.getKey());
        }

        List<Integer> pixelList = new ArrayList<>();
        StringBuilder currentCode = new StringBuilder();

        for (char bit : compressedData.toCharArray()) {
            currentCode.append(bit);
            if (reverseCodebook.containsKey(currentCode.toString())) {
                pixelList.add(reverseCodebook.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }

        int sqrtSize = (int) Math.sqrt(pixelList.size());
        int[][] pixelData = new int[sqrtSize][sqrtSize];
        int index = 0;
        for (int i = 0; i < sqrtSize; i++) {
            for (int j = 0; j < sqrtSize; j++) {
                pixelData[i][j] = pixelList.get(index++);
            }
        }
        return pixelData;
    }
}
