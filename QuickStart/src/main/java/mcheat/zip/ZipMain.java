package mcheat.zip;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author wangjy
 * @date 2018/3/23
 */
public class ZipMain {

    /**
     * 获取zip文件中的指定文件
     *
     * @param zis      zip流
     * @param fileName zip中的指定文件名
     * @return zip中指定文件的字节数组
     */
    public byte[] getSpfFromZip(ZipInputStream zis, String fileName) {
        try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    break;
                } else {
                    if (entry.getName().equals(fileName)) {
                        // 获取当前条目的字节数组
                        byte[] temp = new byte[1024];
                        int length;
                        while ((length = zis.read(temp, 0, 1024)) != -1) {
                            bout.write(temp, 0, length);
                        }

                        byte[] buf = bout.toByteArray();
                        return buf;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
