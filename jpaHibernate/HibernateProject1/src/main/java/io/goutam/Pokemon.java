package io.goutam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bytebuddy.implementation.ToStringMethod;

@Entity
@Table(name = "Pokemon_Details")
public class Pokemon {
		@Id
		@Column(name="pokemon_name")
		private String name;
		@Column(nullable=false)  // type field not null
		private String type;
		// If we don't mention the Column annotation then also variable name will be created as Column name
		private int power;
		@Column(name="colour")
		@Enumerated(EnumType.STRING) // without @Enumerated annotation or @Enumerated(EnumType.ORDINAL), the colour field shows Enum's ordinal (index) value 
		private Colour col;  // Colour is a Enum declared with RED,YELLOW,BLUE,CREAM 
		 
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getPower() {
			return power;
		}
		public void setPower(int power) {
			this.power = power;
		}		
		public Colour getCol() {
			return col;
		}
		public void setCol(Colour col) {
			this.col = col;
		}
		@Override
		public String toString() {
			return "Pokemon [Name=" + getName() + ", Type=" + getType() + ", Power=" + getPower()	+ ", Colour=" + getCol() + "]";
		}

	}
