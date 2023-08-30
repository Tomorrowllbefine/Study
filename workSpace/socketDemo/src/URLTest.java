import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://www.itbaizhan.com/search.html?kw=java");
        System.out.println("默认端口: " + url.getDefaultPort());
        System.out.println("访问资源: " + url.getFile());
        System.out.println("主机名: " + url.getHost());
        System.out.println("访问资源路径: " + url.getPath());
        System.out.println("协议: " + url.getProtocol());
        System.out.println("参数: " + url.getQuery());
    }
}
