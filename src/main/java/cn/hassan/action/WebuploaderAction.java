package cn.hassan.action;

import cn.hassan.result.JsonResult;
import cn.hassan.util.FileUtil;
import cn.hassan.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

import java.io.File;

public class WebuploaderAction extends ActionSupport {

	private File file;
	private String fileFileName;

	private String chunk;// 当前第几个分片
	private String chunks;// 总分片个数
	private String size;// 单个文件的总大小
	//多文件上传的文件对象，多文件是一个一个文件上传，所以multiFile是当前正在上传的文件对象
	private File multiFile;
	//多文件上传文件对象的文名，当前正在上传的文件名
	private String multiFileFileName;

	//自定义属性值
	private String fileSize;//所有文件的总大小
	private String[] multiFileName  ;// 文件名列表
	private String[] fileSizeOneByOne;//每个文件大小
	private String status;

	public String ajaxAttachUpload(){
		try {
			String path = "F:\\webupload\\" + fileFileName;
			File file = this.getFile();
			FileUtil.randomAccessFile(path,file);
			if(chunk == null){
				//不分片的情况
				JsonUtil.write(JsonUtil.toJson("0"));
			}else{
				//分片的情况
				//chunk 分片索引，下标从0开始
				//chunks 总分片数
				if (Integer.valueOf(chunk) == (Integer.valueOf(chunks) - 1)) {
					JsonUtil.write(JsonUtil.toJson("0"));
				} else {
					JsonUtil.write(JsonUtil.toJson("2"));
				}
			}
		} catch (Exception e) {
			JsonUtil.write(JsonUtil.toJson("3"));
		}
		return null;
	}

	public String ajaxAttachUpload2() {
		String path =  "F:\\webupload\\"+multiFileFileName;
		/**
		 * TODO:可添加自己的业务逻辑实现
		 * fileSize;//所有文件的总大小
		 * multiFileName  ;// 文件名列表
		 * fileSizeOneByOne;//每个文件大小
		 *
		 * 后台已经可以拿到这些属性，可以根据每个文件的大小，和总大小计算出  上传的进度百分比等。
		 * 或者可以 判断文件上传的大小是否正确。是否丢字节。
		 */
		try {
			//拿到当前正在上传的文件对象
			File file = this.getMultiFile();
			FileUtil.randomAccessFile(path, file);
			if(chunk == null){
				//不分片的情况
				JsonUtil.write(JsonUtil.toJson("0"));
			}else{
				//分片的情况
				//chunk 分片索引，下标从0开始
				//chunks 总分片数
				if (Integer.valueOf(chunk) == (Integer.valueOf(chunks) - 1)) {
					JsonUtil.write(JsonUtil.toJson("0"));
				} else {
					JsonUtil.write(JsonUtil.toJson("2"));
				}
			}
		} catch (Exception e) {
			JsonUtil.write(JsonUtil.toJson("3"));
		}
		return null;
	}


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getChunk() {
		return chunk;
	}

	public void setChunk(String chunk) {
		this.chunk = chunk;
	}

	public String getChunks() {
		return chunks;
	}

	public void setChunks(String chunks) {
		this.chunks = chunks;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String[] getMultiFileName() {
		return multiFileName;
	}

	public void setMultiFileName(String[] multiFileName) {
		this.multiFileName = multiFileName;
	}

	public String[] getFileSizeOneByOne() {
		return fileSizeOneByOne;
	}

	public void setFileSizeOneByOne(String[] fileSizeOneByOne) {
		this.fileSizeOneByOne = fileSizeOneByOne;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMultiFileFileName() {
		return multiFileFileName;
	}

	public void setMultiFileFileName(String multiFileFileName) {
		this.multiFileFileName = multiFileFileName;
	}

	public File getMultiFile() {
		return multiFile;
	}

	public void setMultiFile(File multiFile) {
		this.multiFile = multiFile;
	}
}
