package corp.adentis.wc.model.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import corp.adentis.wc.model.enumerations.DoorStates;

@Entity
public class Door implements Comparable<Door>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private DoorStates state;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date date;
	
	
	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Door other = (Door) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (state != other.state)
			return false;
		return true;
	}
	
	public DoorStates getState() {
		return state;
	}

	public void setState(DoorStates state) {
		this.state = state;
	}

	@Override
	public int compareTo(Door o) {
		return Long.compare(this.getId(), o.getId());
	}

	
}
