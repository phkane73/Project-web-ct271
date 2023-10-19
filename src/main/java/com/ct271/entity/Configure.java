package com.ct271.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

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
	private String clockFace;
	@OneToMany(mappedBy = "configure", cascade = CascadeType.ALL)
	@JsonBackReference
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


	public void setId(Long id) {
		this.id = id;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public void setHardDrive(String hardDrive) {
		this.hardDrive = hardDrive;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setScreenCard(String screenCard) {
		this.screenCard = screenCard;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public void setDiskSpace(String diskSpace) {
		this.diskSpace = diskSpace;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}

	public void setBackCamera(String backCamera) {
		this.backCamera = backCamera;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setTimeContinuousUse(String timeContinuousUse) {
		this.timeContinuousUse = timeContinuousUse;
	}

	public void setConnectWithOS(String connectWithOS) {
		this.connectWithOS = connectWithOS;
	}

	public void setTimeEarPhone(String timeEarPhone) {
		this.timeEarPhone = timeEarPhone;
	}

	public void setTimeChargeBox(String timeChargeBox) {
		this.timeChargeBox = timeChargeBox;
	}

	public void setChargePort(String chargePort) {
		this.chargePort = chargePort;
	}

	public void setConnectSupport(String connectSupport) {
		this.connectSupport = connectSupport;
	}

	public void setClockFace(String clockFace) {
		this.clockFace = clockFace;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Long getId() {
		return this.id;
	}

	public String getCpu() {
		return this.cpu;
	}

	public String getRam() {
		return this.ram;
	}

	public String getHardDrive() {
		return this.hardDrive;
	}

	public String getScreen() {
		return this.screen;
	}

	public String getOs() {
		return this.os;
	}

	public String getWeight() {
		return this.weight;
	}

	public String getScreenCard() {
		return this.screenCard;
	}

	public String getChip() {
		return this.chip;
	}

	public String getDiskSpace() {
		return this.diskSpace;
	}

	public String getSim() {
		return this.sim;
	}

	public String getFrontCamera() {
		return this.frontCamera;
	}

	public String getBackCamera() {
		return this.backCamera;
	}

	public String getPin() {
		return this.pin;
	}

	public String getTimeContinuousUse() {
		return this.timeContinuousUse;
	}

	public String getConnectWithOS() {
		return this.connectWithOS;
	}

	public String getTimeEarPhone() {
		return this.timeEarPhone;
	}

	public String getTimeChargeBox() {
		return this.timeChargeBox;
	}

	public String getChargePort() {
		return this.chargePort;
	}

	public String getConnectSupport() {
		return this.connectSupport;
	}

	public String getClockFace() {
		return this.clockFace;
	}

	public Set<Product> getProducts() {
		return this.products;
	}
}
