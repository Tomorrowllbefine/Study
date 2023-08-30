import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest1 {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://roadmap.sh/java");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){
            StringBuilder sb = new StringBuilder();
            String str = "";
            while( (str = br.readLine()) != null ) {
                sb.append(str);
            }
            System.out.println(sb);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
