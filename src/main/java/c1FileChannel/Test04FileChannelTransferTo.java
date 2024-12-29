package c1FileChannel;

import utils.Dictionaries;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Test04FileChannelTransferTo {
    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream(Dictionaries.pathRoot+ "\\data.txt").getChannel();
                FileChannel to = new FileOutputStream(Dictionaries.pathRoot+ "\\to.txt").getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化
            // 但是1次传输有上线 2g 数据
            // 因此下面演示分批传输
            long size = from.size();
            // left 变量代表还剩余多少字节
            for (long left = size; left > 0; ) {
                System.out.println("position:" + (size - left) + " left:" + left);
                left -= from.transferTo((size - left), left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
