import java.io.File;
import java.io.IOException;

public class PerformanceMetrics {

    public static double calculateCompressionRatio(long originalSize, long compressedSize) {
        return (double) originalSize / compressedSize;
    }

    public static void measurePerformance(String imagePath) throws IOException, ClassNotFoundException {
        // Measure compression performance
        long startTime = System.currentTimeMillis();
        String outputFilePath = "compressed_image.dat";
        ImageCompressor.compressImage(imagePath, outputFilePath);
        long compressionTime = System.currentTimeMillis() - startTime;

        // Measure decompression performance
        startTime = System.currentTimeMillis();
        int[][] decompressedImage = ImageDecompressor.decompressImage(outputFilePath);
        long decompressionTime = System.currentTimeMillis() - startTime;

        // Calculate sizes
        File originalFile = new File(imagePath);
        long originalSize = originalFile.length() * 8; // Assuming original size is in bits
        long compressedSize = new File(outputFilePath).length();

        // Calculate compression ratio
        double compressionRatio = calculateCompressionRatio(originalSize, compressedSize);

        // Output performance metrics
        System.out.println("Compression Time: " + compressionTime + " ms");
        System.out.println("Decompression Time: " + decompressionTime + " ms");
        System.out.println("Compression Ratio: " + compressionRatio);
        System.out.println("Original File Size: " + originalSize + " bytes");
        System.out.println("Compressed File Size: " + compressedSize + " bytes");
    }
}
