package com.ct271.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "configure")
public class Configure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "configure_id")
	private Long id;
	private String cpu;
	private String ram;
	private String hardDrive;
	private String screen;
	private String os;
	private String weight;
	private String screenCard;
	private String chip;
	private String diskSpace;
	private String sim;
	private String frontCamera;
	private String backCamera;
	private String pin;
	private String timeContinuousUse;
	private String connectWithOS;
	private String timeEarPhone;
	private String timeChargeBox;
	private String chargePort;
	private String connectSupport;
	@OneToMany(mappedBy = "configure", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Product> products;
	
	public Configure() {
		super();
	}
	
	//Các config dành cho tablet
	public Configure(String ram, String screen, String os, String chip, String diskSpace, String sim,
			String frontCamera, String backCamera, String pin) {
		super();
		this.ram = ram;
		this.screen = screen;
		this.os = os;
		this.chip = chip;
		this.diskSpace = diskSpace;
		this.sim = sim;
		this.frontCamera = frontCamera;
		this.backCamera = backCamera;
		this.pin = pin;
	}
	
	//Các configure cho Smartwatch
	public Configure(String screen, String timeContinuousUse, String connectWithOS) {
		super();
		this.screen = screen;
		this.timeContinuousUse = timeContinuousUse;
		this.connectWithOS = connectWithOS;
	}
	
	//Các configure cho Earphone
	public Configure(String connectWithOS, String timeEarPhone, String timeChargeBox, String chargePort,
			String connectSupport) {
		super();
		this.connectWithOS = connectWithOS;
		this.timeEarPhone = timeEarPhone;
		this.timeChargeBox = timeChargeBox;
		this.chargePort = chargePort;
		this.connectSupport = connectSupport;
	}
	
	//Các configure cho Laptop
	public Configure(String cpu, String ram, String hardDrive, String screen, String os, String weight,
			String screenCard) {
		super();
		this.cpu = cpu;
		this.ram = ram;
		this.hardDrive = hardDrive;
		this.screen = screen;
		this.os = os;
		this.weight = weight;
		this.screenCard = screenCard;
	}


}
