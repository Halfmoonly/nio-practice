package c2ByteBuffer;

import java.nio.ByteBuffer;
import static utils.ByteBufferUtil.debugAll;

public class Test03ByteBufferApiRewind {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();
        // 模拟一次读完，position指针指向4
        buffer.get(new byte[4]);
        debugAll(buffer);
        // rewind 从头开始重新读
        buffer.rewind();
        System.out.println((char)buffer.get());
    }
}
