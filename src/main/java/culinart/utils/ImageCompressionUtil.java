package culinart.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

public class ImageCompressionUtil {

    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[50 * 1024 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignorar) {

        }
        return outputStream.toByteArray();

    }

    public static byte[] decompress(byte[] data) throws DataFormatException {
        Inflater Inflater = new Inflater();
        Inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[ 50 * 1024 * 1024];


        while (!Inflater.finished()){
            int count = Inflater.inflate(tmp);
            outputStream.write(tmp,0,count);
        }
        try{
            outputStream.close();
        }catch (Exception ignorar){

        }
        return outputStream.toByteArray();


    }
}
