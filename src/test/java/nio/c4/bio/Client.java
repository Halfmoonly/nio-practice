package nio.c4.bio;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
/**
 * @Author: ly
 * @Date: 2024/2/4 12:54
 */
public class Client {
    // Debug模式运行时自定义发送数据，sc.write(Charset.defaultCharset().encode("client1"));
    // 并且通过Edit-Allow multiple instances运行多个Client，模拟多个客户端连接请求
    public static void main(String[] args) throws Exception{
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8080));
        System.out.println("waiting...");
    }

}
