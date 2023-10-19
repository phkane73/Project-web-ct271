package com.ct271.service;

import com.ct271.entity.Configure;
import com.ct271.entity.Product;
import com.ct271.repository.IConfigureRepo;
import com.ct271.repository.IProductRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigureServiceImpl implements IConfigureService {

	private final IConfigureRepo iConfigureRepo;

	private final IProductRepo iProductRepo;

	public ConfigureServiceImpl(IConfigureRepo iConfigureRepo, IProductRepo iProductRepo) {
		this.iConfigureRepo = iConfigureRepo;
		this.iProductRepo = iProductRepo;
	}

	//Hàm thêm configure
	@Override
	public Configure addConfigure(Product product) {
		Configure configure = new Configure();
		if (product.getConfigure().getBackCamera() != null) {
			configure.setBackCamera(product.getConfigure().getBackCamera());
		}
		if (product.getConfigure().getChargePort() != null) {
			configure.setChargePort(product.getConfigure().getChargePort());
		}
		if (product.getConfigure().getChip() != null) {
			configure.setChip(product.getConfigure().getChip());
		}
		if (product.getConfigure().getConnectSupport() != null) {
			configure.setConnectSupport(product.getConfigure().getConnectSupport());
		}
		if (product.getConfigure().getConnectWithOS() != null) {
			configure.setConnectWithOS(product.getConfigure().getConnectWithOS());
		}
		if (product.getConfigure().getCpu() != null) {
			configure.setCpu(product.getConfigure().getCpu());
		}
		if (product.getConfigure().getDiskSpace() != null) {
			configure.setDiskSpace(product.getConfigure().getDiskSpace());
		}
		if (product.getConfigure().getFrontCamera() != null) {
			configure.setFrontCamera(product.getConfigure().getFrontCamera());
		}
		if (product.getConfigure().getHardDrive() != null) {
			configure.setHardDrive(product.getConfigure().getHardDrive());
		}
		if (product.getConfigure().getOs() != null) {
			configure.setOs(product.getConfigure().getOs());
		}
		if (product.getConfigure().getPin() != null) {
			configure.setPin(product.getConfigure().getPin());
		}
		if (product.getConfigure().getRam() != null) {
			configure.setRam(product.getConfigure().getRam());
		}
		if (product.getConfigure().getScreen() != null) {
			configure.setScreen(product.getConfigure().getScreen());
		}
		if (product.getConfigure().getScreenCard() != null) {
			configure.setScreenCard(product.getConfigure().getScreenCard());
		}
		if (product.getConfigure().getSim() != null) {
			configure.setSim(product.getConfigure().getSim());
		}
		if (product.getConfigure().getTimeChargeBox() != null) {
			configure.setTimeChargeBox(product.getConfigure().getTimeChargeBox());
		}
		if (product.getConfigure().getTimeContinuousUse() != null) {
			configure.setTimeContinuousUse(product.getConfigure().getTimeContinuousUse());
		}
		if (product.getConfigure().getTimeEarPhone() != null) {
			configure.setTimeContinuousUse(product.getConfigure().getTimeEarPhone());
		}
		if (product.getConfigure().getWeight() != null) {
			configure.setWeight(product.getConfigure().getWeight());
		}
		if (product.getConfigure().getClockFace() != null) {
			configure.setClockFace(product.getConfigure().getClockFace());
		}
		iConfigureRepo.save(configure);
		return configure;
	}

	//Hàm chỉnh sửa configure
	@Override
	public Configure updateConfigure(Long id, Product product) {
		Optional<Product> product_old = iProductRepo.findById(id);
		Optional<Configure> configure = iConfigureRepo.findById(product_old.get().getConfigure().getId());
		if (product.getConfigure().getBackCamera() != null) {
			configure.get().setBackCamera(product.getConfigure().getBackCamera());
		}
		if (product.getConfigure().getChargePort() != null) {
			configure.get().setChargePort(product.getConfigure().getChargePort());
		}
		if (product.getConfigure().getChip() != null) {
			configure.get().setChip(product.getConfigure().getChip());
		}
		if (product.getConfigure().getConnectSupport() != null) {
			configure.get().setConnectSupport(product.getConfigure().getConnectSupport());
		}
		if (product.getConfigure().getConnectWithOS() != null) {
			configure.get().setConnectWithOS(product.getConfigure().getConnectWithOS());
		}
		if (product.getConfigure().getCpu() != null) {
			configure.get().setCpu(product.getConfigure().getCpu());
		}
		if (product.getConfigure().getDiskSpace() != null) {
			configure.get().setDiskSpace(product.getConfigure().getDiskSpace());
		}
		if (product.getConfigure().getFrontCamera() != null) {
			configure.get().setFrontCamera(product.getConfigure().getFrontCamera());
		}
		if (product.getConfigure().getHardDrive() != null) {
			configure.get().setHardDrive(product.getConfigure().getHardDrive());
		}
		if (product.getConfigure().getOs() != null) {
			configure.get().setOs(product.getConfigure().getOs());
		}
		if (product.getConfigure().getPin() != null) {
			configure.get().setPin(product.getConfigure().getPin());
		}
		if (product.getConfigure().getRam() != null) {
			configure.get().setRam(product.getConfigure().getRam());
		}
		if (product.getConfigure().getScreen() != null) {
			configure.get().setScreen(product.getConfigure().getScreen());
		}
		if (product.getConfigure().getScreenCard() != null) {
			configure.get().setScreenCard(product.getConfigure().getScreenCard());
		}
		if (product.getConfigure().getSim() != null) {
			configure.get().setSim(product.getConfigure().getSim());
		}
		if (product.getConfigure().getTimeChargeBox() != null) {
			configure.get().setTimeChargeBox(product.getConfigure().getTimeChargeBox());
		}
		if (product.getConfigure().getTimeContinuousUse() != null) {
			configure.get().setTimeContinuousUse(product.getConfigure().getTimeContinuousUse());
		}
		if (product.getConfigure().getTimeEarPhone() != null) {
			configure.get().setTimeEarPhone(product.getConfigure().getTimeEarPhone());
		}
		if (product.getConfigure().getWeight() != null) {
			configure.get().setWeight(product.getConfigure().getWeight());
		}
		if (product.getConfigure().getClockFace() != null) {
			configure.get().setClockFace(product.getConfigure().getClockFace());
		}
		iConfigureRepo.save(configure.get());
		return configure.get();
	}
}
