package corp.adentis.wc.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class DoorAverage implements Comparable<DoorAverage> {
	
	/* VARIAVEIS */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date entry_time;
	

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date exit_time;
	

	private long elapsed_time;

	private long average_time;
	
	
	
	/* METODOS */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Date getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public Date getExit_time() {
		return exit_time;
	}

	public void setExit_time(Date exit_time) {
		this.exit_time = exit_time;
	}

	public long getElapsed_time() {
		return elapsed_time;
	}

	public void setElapsed_time(long elapsed_time) {
		this.elapsed_time = elapsed_time;
	}

	public long getAverage_time() {
		return average_time;
	}

	public void setAverage_time(long average_time) {
		this.average_time = average_time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (average_time ^ (average_time >>> 32));
		result = prime * result + (int) (elapsed_time ^ (elapsed_time >>> 32));
		result = prime * result + ((entry_time == null) ? 0 : entry_time.hashCode());
		result = prime * result + ((exit_time == null) ? 0 : exit_time.hashCode());
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
		DoorAverage other = (DoorAverage) obj;
		if (average_time != other.average_time)
			return false;
		if (elapsed_time != other.elapsed_time)
			return false;
		if (entry_time == null) {
			if (other.entry_time != null)
				return false;
		} else if (!entry_time.equals(other.entry_time))
			return false;
		if (exit_time == null) {
			if (other.exit_time != null)
				return false;
		} else if (!exit_time.equals(other.exit_time))
			return false;
		return true;
	}

	@Override
	public int compareTo(DoorAverage o) {
		// TODO Auto-generated method stub
		return Long.compare(this.getId(), o.getId());
	}

	
	
}
