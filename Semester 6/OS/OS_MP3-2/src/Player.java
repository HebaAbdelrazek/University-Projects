
public class Player extends Thread {
	int id;
	int waitingTime;
	boolean onBoard;
	boolean rideComplete;
	Operator operator;

	public Player(int id, int waitingTime, Operator operator) throws InterruptedException {
		this.id = id;
		this.waitingTime = waitingTime;
		this.operator = operator;
		this.start();
	}

	public synchronized void run() {
		try {
			synchronized (this) {
				this.wait(waitingTime);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		operator.addPlayer(this);

	}

}
