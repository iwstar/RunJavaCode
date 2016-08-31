package com.aaa.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RunCodeController {
	private static final Logger log = LogManager.getLogger(RunCodeController.class);

	@RequestMapping("openCodePage/{dest}/")
	public String openCodePage(@PathVariable String dest) {
		return dest;
	}

	@RequestMapping(value = "runCode/", method = RequestMethod.POST)
	public void runCode(String code, HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
		// String jclassPath =
		// RunCodeController.class.getResource("").getFile();
		Pattern pattern = Pattern.compile("class[a-zA-Z0-9 ]*\\{");
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			// System.out.println(m.group());
			String fileName = m.group().replace("class", "").replace("{", "").trim();
			String filePath = request.getServletContext().getRealPath(File.separator) + File.separator;
			log.info(filePath + fileName + ".java");
			File delsf = new File(filePath + fileName + ".java");
			File delcf = new File(filePath + fileName + ".class");
			if (delsf.exists() && delsf.isFile()) {
				delsf.delete();
			}
			if (delcf.exists() && delcf.isFile()) {
				delcf.delete();
			}

			File f = new File(filePath + fileName + ".java");
			BufferedReader br = null;
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write(code);
				bw.close();
				Runtime runtime = Runtime.getRuntime();
				// Thread.sleep(2000);
				Process p0 = runtime.exec("javac " + filePath + fileName + ".java");
				p0.waitFor();
				Process p = runtime.exec("java -classpath " + filePath + " " + fileName);
				br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				log.info(sb.toString());
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.write(sb.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter printWriter;
				printWriter = response.getWriter();
				printWriter.write("error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
