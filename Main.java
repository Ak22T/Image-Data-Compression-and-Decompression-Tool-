import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'c' for compression or 'd' for decompression:");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("c")) {
            System.out.println("Enter the path of the image to compress:");
            String imagePath = scanner.nextLine();
            String compressedFilePath = "compressed_image.dat";

            try {
                // Get original file size
                long originalFileSize = Files.size(Paths.get(imagePath));

                // Compress the image
                long startTime = System.currentTimeMillis();
                ImageCompressor.compressImage(imagePath, compressedFilePath);
                long endTime = System.currentTimeMillis();

                // Get compressed file size
                long compressedFileSize = Files.size(Paths.get(compressedFilePath));

                // Print the compression performance metrics
                System.out.println("Compression Time Taken: " + (endTime - startTime) + " ms");
                System.out.println("Original File Size: " + originalFileSize + " bytes");
                System.out.println("Compressed File Size: " + compressedFileSize + " bytes");

            } catch (IOException e) {
                System.err.println("Error processing the image: " + e.getMessage());
                e.printStackTrace();
            }

        } else if (choice.equalsIgnoreCase("d")) {
            System.out.println("Enter the path of the compressed file:");
            String compressedFilePath = scanner.nextLine();
            String decompressedFilePath = "decompressed_image.png";

            try {
                // Check if the file exists
                if (!Files.exists(Paths.get(compressedFilePath))) {
                    System.err.println("The compressed file does not exist: " + compressedFilePath);
                    return;
                }

                // Get compressed file size
                long compressedFileSize = Files.size(Paths.get(compressedFilePath));

                // Decompress the image
                long startTime = System.currentTimeMillis();
                int[][] decompressedData = ImageDecompressor.decompressImage(compressedFilePath);
                long endTime = System.currentTimeMillis();

                // Write the decompressed image data back to a file
                ImageWriter.writeImage(decompressedData, decompressedFilePath);

                // Get decompressed file size
                long decompressedFileSize = Files.size(Paths.get(decompressedFilePath));

                // Print the decompression performance metrics
                System.out.println("Decompression Time Taken: " + (endTime - startTime) + " ms");
                System.out.println("Compressed File Size: " + compressedFileSize + " bytes");
                System.out.println("Decompressed File Size: " + decompressedFileSize + " bytes");

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error processing the image: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid choice. Please enter 'c' for compression or 'd' for decompression.");
        }

        scanner.close();
    }
}
