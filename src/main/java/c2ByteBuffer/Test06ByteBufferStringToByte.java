package c2ByteBuffer;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static utils.ByteBufferUtil.debugAll;

public class Test06ByteBufferStringToByte {
    public static void main(String[] args) {
        // 1. 字符串转为 ByteBuffer，写完之后没有调用flip仍然保持写模式
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes());
        debugAll(buffer1);
        //必须手动触发flip，否则读取失败
        buffer1.flip();
        String str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str1);

        // 2. Charset ，写完之后自动切换为读模式
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer2);
        String str2 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str2);

        // 3. wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);
        String str3 = StandardCharsets.UTF_8.decode(buffer3).toString();
        System.out.println(str3);

    }
}
