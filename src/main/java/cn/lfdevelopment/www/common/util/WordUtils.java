package cn.lfdevelopment.www.common.util;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LiuFa on 2016/10/29.
 */
public class WordUtils {

    /**
     *  html转word
     * @param content html内容
     * @param filePath 文件路径
     * @param fileName 文件名，带后缀名
     * @throws IOException
     */
    public static void htmlToWord(String content,String filePath,String fileName) throws IOException {
        byte b[] = content.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        POIFSFileSystem poifs = new POIFSFileSystem();
        DirectoryEntry directory = poifs.getRoot();
        directory.createDocument("WordDocument", bais);
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        FileOutputStream ostream = new FileOutputStream(filePath+ fileName);
        poifs.writeFilesystem(ostream);
        bais.close();
        ostream.close();
    }
}
