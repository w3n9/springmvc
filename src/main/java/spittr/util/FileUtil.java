package spittr.util;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.rs.PutPolicy;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;
import spittr.config.Constant;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {
	public static String generateUUID(){
		return UUID.randomUUID().toString().replace("-","");
	}
	public static String generateFileName(String originFileName,String dir){
		String target="";
		String[] fileds=originFileName.split("\\.");
		System.out.println(fileds.length);
		if(fileds.length<=1){//如果该文件没有后缀名
			target+=dir+"/"+generateUUID();
		}else{//有后缀名
			target=dir+"/"+generateUUID()+"."+fileds[fileds.length-1];
		}
		return target;
	}
	//保存文件到磁盘
	public static Boolean saveToDisk(MultipartFile file,String dir) {
		String originFileName=file.getOriginalFilename();
		String target=generateFileName(originFileName,dir);
		File targetFile=new File(target);
		try{
			file.transferTo(targetFile);
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static String generateUpToken()  {
		Mac mac=new Mac(Constant.QINIU_ACCESS_KEY,Constant.QINIU_SECRET_KEY);
		PutPolicy putPolicy=new PutPolicy(Constant.QINIU_BUCKET);
		try{
			return putPolicy.token(mac);

		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}
	public static Boolean saveToDisk(Part file,String dir){
		String originFileName=file.getSubmittedFileName();
		String target=generateFileName(originFileName,dir);
		try{
			file.write(target);
		}catch (IOException e){
			e.printStackTrace();;
			return false;
		}
		return true;
	}
	public static Boolean saveToQiNiu(MultipartFile file){
		String upToken=generateUpToken();
		PutExtra extra=new PutExtra();
		String key=null;
		try{
			IoApi.Put(upToken,key,file.getInputStream(),extra);
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static Boolean saveToQiNiu(Part file){
		String upToken=generateUpToken();
		PutExtra extra=new PutExtra();
		String key=null;
		try{
			IoApi.Put(upToken,key,file.getInputStream(),extra);
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
