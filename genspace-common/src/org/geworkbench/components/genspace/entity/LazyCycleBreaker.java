package org.geworkbench.components.genspace.entity;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

import com.sun.xml.bind.CycleRecoverable;

public abstract class LazyCycleBreaker implements CycleRecoverable {
	
	public abstract int getId();
	private Object ref;
	@XmlIDREF
	@XmlAttribute
	@Transient
	public Object getRef() {
		return ref;
	}
	public void setRef(Object ref) {
		this.ref = ref;
	}
	
	@XmlID
	@Transient
	public String getIdstr()
	{
		if(getId() > 0)
			return this.getClass().getName()+getId();
		return null;
	}
	public void setIdstr(String s)
	{
		
	}
	public Object onCycleDetected(Context ctx) {
		System.out.println("Cycle detected on obj " + this.getClass().getName() + " (#" + getId() + ")");
		try {
			Object u = this.getClass().newInstance();
			this.getClass().getMethod("setRef", Object.class).invoke(u, this);
	        return u;
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Failing to ");
			return null;
		}
	}
}
