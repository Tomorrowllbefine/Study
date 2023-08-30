import java.net.InetSocketAddress;

public class InetSocketAddressTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("www.baidu.com",80);
        System.out.println("域名: " + inetSocketAddress.getAddress().getHostName());
        System.out.println("IP: " + inetSocketAddress.getAddress().getHostAddress());
        System.out.println("port: " + inetSocketAddress.getPort());
        System.out.println(inetSocketAddress.getHostName());
    }
}
