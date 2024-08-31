package nio.c2ByteBuffer;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import config.Dictionaries;

@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {
        System.out.println((new File("./")).getAbsolutePath());
        // FileChannel
        // 1. 输入输出流， 2. RandomAccessFile
//        try (FileChannel channel = new FileInputStream(Dictionaries.pathRoot+ "data.txt").getChannel()) {
        try (FileChannel channel = new FileInputStream("E:\\github\\netty-practice\\data.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while(true) {
                // 从 channel 读取数据，向 buffer 写入
                int len = channel.read(buffer);
                log.debug("读取到的字节数 {}", len);
                if(len == -1) { // 没有内容了
                    break;
                }
                // 打印 buffer 的内容
                buffer.flip(); // 切换buffer至读模式
                while(buffer.hasRemaining()) { // 是否还有剩余未读数据
                    byte b = buffer.get();
                    log.debug("实际字节 {}", (char) b);
                }
                buffer.clear(); // 调用 clear() 或 compact() 切换buffer至写模式，让channel继续往buffer里面写入数据
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
