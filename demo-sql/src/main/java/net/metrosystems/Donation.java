package net.metrosystems;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Donation {

	@Id
	private int id;
	private long amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
