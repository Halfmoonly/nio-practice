package c1FileChannel;

import utils.Dictionaries;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Test06FilesWalkRecursiveDelete {
    /**
     * 递归删除所有子文件和子目录
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //直接删除非空文件夹会报错
        //Files.delete(Paths.get("D:\\Snipaste-1.16.2-x64 - 副本"));

        //下面的删除是正确的
        Files.walkFileTree(Paths.get(Dictionaries.pathDelete), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }
}

