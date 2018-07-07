package util;

public class StringUtil {

	public static String join(Object obj1, Object obj2) {
		return new StringBuilder(obj1!=null?obj1.toString():"null").append(obj2).toString();
	}



	public static String join(Object obj1, Object obj2, Object obj3) {
		return new StringBuilder(join(obj1, obj2)).append(obj3).toString();
	}



	public static String joins(Object...objs) {
		if(objs==null || objs.length==0)
			return "";

		int len = objs.length;
		StringBuilder sb = new StringBuilder(join(objs[0], ""));
		for(int i=1; i<len; i++) {
			sb.append(objs[i]); }
		return sb.toString();
	}
}
