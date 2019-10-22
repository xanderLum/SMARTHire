/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smarthire.main.models.SocialMediaData;

/**
 *
 * @author Nathalia
 */

public class PartPointer {
	
    private String type;
    private PhraseData roleA;
    private LinkedList<ClauseData> roleB;
    private String content;
    private String syntacticRole;
    private String semanticRole;
    private String number;
	private HashMap<String, String> categoryPnt;
	
	public PartPointer()
	{
		super();
	}
	
	public PartPointer(String type, PhraseData role, String content, String syntacticRole, String number) {
        this.type = type;
        this.roleA = role;
        this.content = content;
        this.syntacticRole = syntacticRole;
        this.number = number;
    }
	
	public PartPointer(String type, LinkedList<ClauseData> role, String content, String syntacticRole, String number) {
        this.type = type;
        this.roleB = role;
        this.content = content;
        this.syntacticRole = syntacticRole;
        this.number = number;
    }
	
    public PartPointer(String type, PhraseData role, String content, String syntacticRole, String semanticRole, String number) {
        this.type = type;
        this.roleA = role;
        this.content = content;
        this.syntacticRole = syntacticRole;
        this.semanticRole = semanticRole;
        this.number = number;
    }

    public PartPointer(String type, LinkedList<ClauseData> role, String content, String syntacticRole, String semanticRole, String number) {
        this.type = type;
        this.roleB = role;
        this.content = content;
        this.syntacticRole = syntacticRole;
        this.semanticRole = semanticRole;
        this.number = number;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getSyntacticRole() {
        return syntacticRole;
    }

    public void setSyntacticRole(String syntacticRole) {
        this.syntacticRole = syntacticRole;
    }

    public String getSemanticRole() {
        return semanticRole;
    }

    public void setSemanticRole(String semanticRole) {
        this.semanticRole = semanticRole;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
	//public ClauseData getClauseData() {
	//	return clauseData;
	//}

	//public void setClauseData(ClauseData clauseData) {
	//	this.clauseData = clauseData;
	//}

	public HashMap<String, String> getCategoryPnt() {
		return categoryPnt;
	}


	public void setCategoryPnt(HashMap<String, String> categoryPnt) {
		this.categoryPnt = categoryPnt;
	}

	public PhraseData getRoleA() {
		return roleA;
	}

	public void setRoleA(PhraseData roleA) {
		this.roleA = roleA;
	}

	public LinkedList<ClauseData> getRoleB() {
		return roleB;
	}

	public void setRoleB(LinkedList<ClauseData> roleB) {
		this.roleB = roleB;
	}
    
}
