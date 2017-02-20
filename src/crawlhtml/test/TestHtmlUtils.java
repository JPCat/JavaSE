package crawlhtml.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import crawlhtml.util.HtmlUtils;

public class TestHtmlUtils
{
	@Test
	public void test1()
	{
		String addressUrl = "http://www.mzhu8.com/mulu/6/1.html";
		String encoding = "gbk";
		String result = HtmlUtils.getHtmlContent(addressUrl, encoding);
		System.out.println(result);
	}

	@Test
	public void test2() throws MalformedURLException
	{
		String addressUrl = "http://www.mzhu8.com/mulu/6/1.html";
		URL url = new URL(addressUrl);
		String result = HtmlUtils.getEncoding(url);
		System.out.println(result);
	}

	@Test
	public void test3()
	{
		String addressUrl = "http://www.mzhu8.com/mulu/6/1.html";
		String encoding = "gbk";
		List<String> result = HtmlUtils.getHtmlContentUseParser(addressUrl, encoding);
		for(String s:result){
			System.out.println(s);
		}
	}

}
