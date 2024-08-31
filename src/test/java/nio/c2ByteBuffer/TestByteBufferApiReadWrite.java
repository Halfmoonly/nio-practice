package nio.c2ByteBuffer;

import java.nio.ByteBuffer;

import static nio.c2ByteBuffer.ByteBufferUtil.debugAll;

public class TestByteBufferApiReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61); // 'a'
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64}); // b  c  d
        debugAll(buffer);
//        System.out.println(buffer.get());//当前position指针指向数据位的下一位，是没有数据的
        buffer.flip();//切换到读取模式，position指针重置为0
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        debugAll(buffer);

        //前面读了两个个，compact会将后面两个没读的前移，前移后的buffer情况为63 64 63 64，position指向第二组63，这好像出现了幻觉但是无所谓
        buffer.compact();
        debugAll(buffer);

        buffer.put(new byte[]{0x65, 0x6f});//覆盖最后面的幻觉位
        debugAll(buffer);
    }
}
