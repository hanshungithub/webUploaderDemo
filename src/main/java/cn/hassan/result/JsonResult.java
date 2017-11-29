package cn.hassan.result;

import cn.hassan.util.JsonUtil;

/**
 * ajax请求时，返回json格式的结果
 * 
 * @Description :
 * @author Sam
 * @version 1.0
 * @created Oct 17, 2011 10:53:31 AM
 * @fileName com.weqia.common.pojo.JsonResult.java
 * 
 */
public class JsonResult {
	/**
	 * 是否处理成功
	 */
	private boolean isSuccess;
	/**
	 * 错误码
	 */
	private String errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	/**
	 * 提示信息
	 */
	private String msg;
	/**
	 * 结果信息
	 */
	private Object result;

	/**
	 * 返回json格式的结果
	 * 
	 * @Description
	 * @return
	 * 
	 */
	public String toJson() {
		return JsonUtil.toJson(this);
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
