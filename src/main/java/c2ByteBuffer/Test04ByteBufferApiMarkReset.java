package c2ByteBuffer;

import java.nio.ByteBuffer;

import static utils.ByteBufferUtil.debugAll;

public class Test04ByteBufferApiMarkReset {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();
        debugAll(buffer);
        // mark & reset
        // mark 做一个标记，记录 position 位置， reset 是将 position 重置到 mark 的位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.mark(); // 加标记，索引2 的位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.reset(); // 将 position 重置到索引 2
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());

        // 区别于get(i) 不会改变读索引的位置
//        System.out.println((char) buffer.get(3));
//        debugAll(buffer);
    }
}
