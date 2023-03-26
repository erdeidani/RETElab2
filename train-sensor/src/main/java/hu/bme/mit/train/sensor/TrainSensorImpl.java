package hu.bme.mit.train.sensor;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.checkerframework.checker.units.qual.Time;

import java.time.LocalTime;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private Table<LocalTime, Integer, Integer> tachograph;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		controller.setSensor(this);
		this.user = user;
		this.tachograph = HashBasedTable.create();
	}


	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}

	@Override
	public void Tachograph(){
		tachograph.put(LocalTime.now(), controller.getPosition(), controller.getReferenceSpeed());
	}

	@Override
	public Table getTachograph(){return tachograph;}
}
