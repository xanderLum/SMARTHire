package com.smarthire.xander.models;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="documents")
public class Documents implements Serializable {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private Long id;

		@Column(name="username")
		private String username;

		@Column(name="description")
		private String description;

		@Column(name="filename")
		private String filename;

		@Column(name="content")
		@Lob
		private Blob content;
		
		@Column(name="content_type")
		private String contentType;
		
		@Column(name="created")
		private Date created;
		

		
		public Documents() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		
		public Documents(Long id, String username, String description, String filename, Blob content,
				String contentType, Date created) {
			super();
			this.id = id;
			this.username = username;
			this.description = description;
			this.filename = filename;
			this.content = content;
			this.contentType = contentType;
			this.created = created;
		}



		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public Blob getContent() {
			return content;
		}

		public void setContent(Blob content) {
			this.content = content;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		public Date getCreated() {
			return created;
		}

		public void setCreated(Date created) {
			this.created = created;
		}



		@Override
		public String toString() {
			return "Documents [" + (id != null ? "id=" + id + ", " : "")
					+ (username != null ? "username=" + username + ", " : "")
					+ (description != null ? "description=" + description + ", " : "")
					+ (filename != null ? "filename=" + filename + ", " : "")
					+ (content != null ? "content=" + content + ", " : "")
					+ (contentType != null ? "contentType=" + contentType + ", " : "")
					+ (created != null ? "created=" + created : "") + "]";
		}
		
		
	}

