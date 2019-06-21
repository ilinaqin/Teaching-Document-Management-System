package cn.itcast.QLN.utils;

public class UtilTools {
	
	static String pdf ="application/pdf";
	static String jpg = "text/plain";
	static String java ="text/plain";
	/*ppt和doc文件不能打开，Chrome没有这个插件*/
	static String pptx ="application/x-ppt";
	static String doc ="application/msword";
	public static String returnContentType(String str){
		String type="";
		if ("pdf".equals(str)){
			type = UtilTools.pdf;
		}else if("jpg".equals(str)){
			type = UtilTools.jpg;
		}else if("java".equals(str)){
			type=UtilTools.java;
		}else if("pptx".equals(str)){
			type=UtilTools.pptx;
		}else if("doc".equals(str)){
			type=UtilTools.doc;
		}
		return type;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
