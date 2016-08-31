package extend;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class testReg {

	@Test
	public void test() {
		String code="public class RunCode { fasfasdfasdfasdfas";
		Pattern pattern = Pattern.compile("class[a-zA-Z0-9 ]*\\{");
		Matcher m=pattern.matcher(code);
		if (m.find()){
			System.out.println(m.group().replace("class", "").replace("{", "").trim());
		}
	}

}
