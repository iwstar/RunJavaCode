package extend;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.Test;

public class testCommand {

	@Test
	public void test() {
		System.out.println(testCommand.class.getResource ("").getFile ());
		BufferedReader br = null; 
		try {  
			Runtime runtime=Runtime.getRuntime();
			runtime.exec("");
            Process p = runtime.exec("java D:\\Develop\\java\\javamaven\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\wtpwebapps\\RunJavaCode\\RunCode");  
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            String line = null;  
            StringBuilder sb = new StringBuilder();  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
            System.out.println(sb.toString());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
	}

}
