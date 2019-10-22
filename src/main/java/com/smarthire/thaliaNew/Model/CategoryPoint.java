package com.smarthire.thaliaNew.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY_POINT")
public class CategoryPoint  implements Serializable {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="pnt")
	private int pnt;
	
	@Column(name="partPointerId")
	private Long partPointerId;
	
	//@ManyToOne
	//@JoinColumn(name="id", referencedColumnName="partPointerId")
	//private PartPointer partPointer;

	
	
	public Long getId() {
		return id;
	}
	
	public CategoryPoint() {
		super();
	}

	public CategoryPoint(String name, int pnt, Long partPointerId) {
		super();
		this.name = name;
		this.pnt = pnt;
		this.partPointerId = partPointerId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPnt() {
		return pnt;
	}

	public void setPnt(int pnt) {
		this.pnt = pnt;
	}

	public Long getPartPointerId() {
		return partPointerId;
	}

	public void setPartPointerId(Long partPointerId) {
		this.partPointerId = partPointerId;
	}

	//public PartPointer getPartPointer() {
	//	return partPointer;
	//}

	//public void setPartPointer(PartPointer partPointer) {
	//	this.partPointer = partPointer;
	//}
	
	

}
