package com.design.framework.base.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.design.framework.serializer.DateJsonDeserializer;
import com.design.framework.serializer.JsonDateSerializer;
import com.design.framework.utils.IdUtils;

/**
 * 实体基类
 * 
 * @author JohnDeng
 * @datatime 2018年8月6日下午10:41:55
 */
public class BaseEntity<ID> implements Serializable {

	/**
	 * @author JohnDeng
	 * @datatime 2018年8月6日下午10:04:36
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private ID id;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = DateJsonDeserializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = DateJsonDeserializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 创建用户id
	 */
	@Column(name = "create_id")
	private String createId;

	/**
	 * 修改用户id
	 */
	@Column(name = "update_id")
	private String updateId;

	/**
	 * 删除标记；0未删除，1已删除
	 */
	@Column(name = "del_flag")
	private Integer delFlag;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	 /**
     * 新增统一设置新增ID，创建时间，修改时间，创建人ID，修改人ID,删除标志
     * @author JohnDeng
     * @date 2018年8月8日下午3:27:58
     * @param request
     * @param response
     * @return
     */
    public ID setAddParam(HttpServletRequest request) {
    	ID id = (ID) IdUtils.getId();
       /* this.createId = UserUtils.getUserId(request);
        this.updateId = UserUtils.getUserId(request);*/
        this.delFlag = 0;
        this.createTime = new Date();
        this.updateTime = new Date();
        this.id = id;
        return id;
    }

     /**
      * 修改统一设置修改时间，修改人ID,删除标志
      * @author JohnDeng
      * @date 2018年8月8日下午3:29:05
      * @param request
      * @param response
      */
    public void setUpdateParam(HttpServletRequest request) {
        this.delFlag = 0;
       /* this.updateId = UserUtils.getUserId(request);*/
        this.updateTime = new Date();
    }

    /**
     * 删除统一设置修改时间，修改人ID,删除标志
     * @author JohnDeng
     * @date 2018年8月8日下午3:29:38
     * @param request
     * @param response
     */
    public void setDeleteParam(HttpServletRequest request) {
        this.delFlag = 1;
        /*this.updateId = UserUtils.getUserId(request);*/
        this.updateTime = new Date();
    }

}
