package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;

public class TrainControllerImpl implements TrainController {

	private TrainSensor sensor;
	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private boolean isEmergencyBreak = false;


	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}
		if(isEmergencyBreak)
			speedLimit=0;

		sensor.Tachograph();
		enforceSpeedLimit();
	}

	@Override
	public void setSensor(TrainSensor s){
		this.sensor = s;
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	@Override
	public void setEmergencyBreak(boolean emergency) {
		this.isEmergencyBreak = emergency;
		enforceSpeedLimit();
	}

	@Override
	public boolean getEmergencyBreak(){return isEmergencyBreak; }


	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	@Override
	public int getPosition(){return step;}

}
