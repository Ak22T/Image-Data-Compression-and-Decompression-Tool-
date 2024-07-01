
import java.io.*;
public class FileHandler {


    public static void saveCompressedFile(String encodedData, Node huffmanTree, int width, int height, String outputPath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputPath))) {
            oos.writeObject(encodedData);
            oos.writeObject(huffmanTree);
            oos.writeInt(width);
            oos.writeInt(height);
        }
    }

    public static Object[] loadCompressedFile(String inputPath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputPath))) {
            String encodedData = (String) ois.readObject();
            Node huffmanTree = (Node) ois.readObject();
            int width = ois.readInt();
            int height = ois.readInt();
            return new Object[]{encodedData, huffmanTree, width, height};
        }
    }
}
