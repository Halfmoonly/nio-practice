package c1FileChannel;

import lombok.extern.slf4j.Slf4j;
import utils.Dictionaries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class Test07FilesWorkRecursiveCopy {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String source = Dictionaries.pathRoot;
        String target = Dictionaries.pathRootCopy;
        Files.walk(Paths.get(source)).forEach(path -> {
            log.info("path:{}", path);
            try {
                //字符串整体替换
                String targetName = path.toString().replace(source, target);
                log.info("targetName:{}", targetName);
                // 是目录
                if (Files.isDirectory(path)) {
                    Files.createDirectory(Paths.get(targetName));
                }
                // 是普通文件
                else if (Files.isRegularFile(path)) {
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
