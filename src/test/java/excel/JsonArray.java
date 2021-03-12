package excel;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

public class JsonArray extends Excel{
	
	@Test
	public void arrayy() throws Throwable {

		JSONObject filename = new JSONObject();

		JSONArray arrayvalue = new JSONArray();
		filename.put(excelread(9, 3, "CreatePost"), excelread(9, 4, "CreatePost"));
		arrayvalue.add(filename);

		JSONObject body4 = new JSONObject();
		body4.put(excelread(3, 2, "CreatePost"), excelread(3, 4, "CreatePost"));
		body4.put(excelread(4, 2, "CreatePost"), excelread(4, 4, "CreatePost"));
		body4.put(excelread(5, 2, "CreatePost"), excelread(5, 4, "CreatePost"));
		body4.put(excelread(6, 2, "CreatePost"), excelread(6, 4, "CreatePost"));
		body4.put(excelread(7, 2, "CreatePost"), excelread(7, 4, "CreatePost"));
		body4.put(excelread(8, 2, "CreatePost"), excelread(8, 4, "CreatePost"));
		body4.put(excelread(9, 2, "CreatePost"), arrayvalue);
		
		System.out.println(body4);



	}
}
