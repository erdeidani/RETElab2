package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

	void setEmergencyBreak(boolean emergency);

	boolean getEmergencyBreak();

	int getPosition();

	void setSensor(TrainSensor s);
}
