package c3Selector.nio;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static utils.ByteBufferUtil.debugRead;

/**
 * @Author: ly
 * @Date: 2024/2/4 13:31
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws Exception {


// 使用 nio 来理解非阻塞模式, 单线程
// 0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
// 1. 创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false); // 非阻塞模式,让accept方法不再阻塞
// 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));
// 3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // 4. accept 建立与客户端连接， SocketChannel 用来与客户端之间通信
            SocketChannel sc = ssc.accept(); // 非阻塞，线程还会继续运行，如果没有连接建立，但sc是null
            if (sc != null) {
                log.debug("connected... {}", sc);
                sc.configureBlocking(false); // 非阻塞模式，让read方法不再阻塞
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                // 5. 接收客户端发送的数据
                int read = channel.read(buffer);// 非阻塞，线程仍然会继续运行，如果没有读到数据，read 返回 0
                if (read > 0) {
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                    log.debug("after read...{}", channel);
                }
            }
        }
    }
}
