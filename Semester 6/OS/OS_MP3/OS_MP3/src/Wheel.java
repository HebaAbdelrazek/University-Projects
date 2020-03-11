import java.util.ArrayList;

public class Wheel extends Thread {
	int capacity;
	int onBoardCount;
	ArrayList<Player> onBoardPlayers;
	int max_wait_time;

	public Wheel(int max_wait_time) {
		this.capacity = 5;
		this.onBoardCount = 0;
		this.onBoardPlayers = new ArrayList<Player>();
		this.max_wait_time = max_wait_time;

	}

	public synchronized void load_players(Player player) {
		this.onBoardPlayers.add(player);
		player.onBoard = true;
		this.onBoardCount++;

	}

	public synchronized void run() {
		try {
			System.out.println("wheel start sleep");
			try {
				synchronized (this) {
					this.wait(max_wait_time);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("wheel end sleep");

			this.run_ride();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void run_ride() throws InterruptedException {

		for (int i = 0; i < this.onBoardCount; i++) {
			this.onBoardPlayers.get(i).rideComplete = true;

		}

		this.end_ride();
	}

	public synchronized void end_ride() throws InterruptedException {
		System.out.println("Wheel is full, Let's go for a ride");
		System.out.println("Threads in this ride are: ");
		for (int i = 0; i < this.onBoardCount; i++) {
			System.out.print(this.onBoardPlayers.get(i).id + " ");
		}
		System.out.println();

		this.onBoardPlayers.clear();
		this.onBoardCount = 0;

		System.out.println("wheel start sleep");
		try {
			synchronized (this) {
				this.wait(max_wait_time);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("wheel end sleep");

		this.run_ride();

	}

}
