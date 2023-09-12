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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHardDrive() {
		return hardDrive;
	}

	public void setHardDrive(String hardDrive) {
		this.hardDrive = hardDrive;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getScreenCard() {
		return screenCard;
	}

	public void setScreenCard(String screenCard) {
		this.screenCard = screenCard;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public String getDiskSpace() {
		return diskSpace;
	}

	public void setDiskSpace(String diskSpace) {
		this.diskSpace = diskSpace;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}

	public String getBackCamera() {
		return backCamera;
	}

	public void setBackCamera(String backCamera) {
		this.backCamera = backCamera;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getTimeContinuousUse() {
		return timeContinuousUse;
	}

	public void setTimeContinuousUse(String timeContinuousUse) {
		this.timeContinuousUse = timeContinuousUse;
	}

	public String getConnectWithOS() {
		return connectWithOS;
	}

	public void setConnectWithOS(String connectWithOS) {
		this.connectWithOS = connectWithOS;
	}

	public String getTimeEarPhone() {
		return timeEarPhone;
	}

	public void setTimeEarPhone(String timeEarPhone) {
		this.timeEarPhone = timeEarPhone;
	}

	public String getTimeChargeBox() {
		return timeChargeBox;
	}

	public void setTimeChargeBox(String timeChargeBox) {
		this.timeChargeBox = timeChargeBox;
	}

	public String getChargePort() {
		return chargePort;
	}

	public void setChargePort(String chargePort) {
		this.chargePort = chargePort;
	}

	public String getConnectSupport() {
		return connectSupport;
	}

	public void setConnectSupport(String connectSupport) {
		this.connectSupport = connectSupport;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
	
}
