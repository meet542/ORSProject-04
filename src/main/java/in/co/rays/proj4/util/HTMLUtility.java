package in.co.rays.proj4.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.proj4.bean.DropdownListBean;

public class HTMLUtility {

	// Static pre-load
	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "'>");

		Set<String> keys = map.keySet();
		String val = null;

		boolean select = true;
		if (select) {
			sb.append("<option selected value=''>------------Select-------------</option>");
		}

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	// dynamic pre-load
	public static String getList(String name, String selectedVal, List list) {

		List<DropdownListBean> dd = (List<DropdownListBean>) list;

		StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "'>");

		String key = null;
		String val = null;

		boolean select = true;
		if (select) {
			sb.append("<option selected value=''>------------Select-------------</option>");
		}

		for (DropdownListBean obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");

		System.out.println(sb.toString());

		return sb.toString();
	}

	public static void main(String[] args) {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("male", "male");
		map.put("female", "female");

		System.out.println(getList("gender", "male", map));

	}

}
