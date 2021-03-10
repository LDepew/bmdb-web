package com.bmdb.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Actor {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	
		private int Id;
		private String firstName;
		private String lastName;
		private String gender;
		private String birthDate;
		
		public Actor(int id, String firstName, String lastName, String gender, String birthDate) {
			super();
			Id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.gender = gender;
			this.birthDate = birthDate;
		}

		public Actor() {
			super();
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}

		@Override
		public String toString() {
			return "Actor [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
					+ ", birthDate=" + birthDate + "]";
		}
		
		

}
