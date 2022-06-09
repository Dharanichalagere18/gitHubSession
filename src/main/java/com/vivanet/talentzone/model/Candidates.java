package com.vivanet.talentzone.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection="candidates")
public class Candidates {
	@Id
	private ObjectId id;
	private String firstName;
	private String lastName;
	private String qualification;
	private String designation;
	private String universityName;
	private int passoutYear;
	private int percentage;
	private int experience;
	private String experienceLevel;
	
	public Candidates() {
		super();
		
	}

	public Candidates(String firstName, String lastName, String qualification, String designation,
			String universityName, int passoutYear, int percentage, int experience, String experienceLevel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.qualification = qualification;
		this.designation = designation;
		this.universityName = universityName;
		this.passoutYear = passoutYear;
		this.percentage = percentage;
		this.experience = experience;
		this.experienceLevel = experienceLevel;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public int getPassoutYear() {
		return passoutYear;
	}

	public void setPassoutYear(int passoutYear) {
		this.passoutYear = passoutYear;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}


}

