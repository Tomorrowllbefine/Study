import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetTest {
    public static void main(String[] args) throws UnknownHostException {
//        // 实例化InetAddress对象
//        InetAddress localHost = InetAddress.getLocalHost();
//
//        // 返回当前计算机的IP地址
//        System.out.println("IP: "+ localHost.getHostAddress());
//        // 计算机名
//        System.out.println("计算机名: " + localHost.getHostName());

//        InetAddress host = InetAddress.getByName("www.scnu.edu.cn");
//        System.out.println("IP: " + host.getHostAddress());
//        System.out.println("计算机名: " + host.getHostName());

        InetAddress host = InetAddress.getByName("121.8.171.51");
        System.out.println("IP: " + host.getHostAddress());
        System.out.println("计算机名: " + host.getHostName());
    }
}
