import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Operator {
	Wheel wheel;
	static ArrayList<Player> totalPlayers = new ArrayList<Player>();
	int total_players_count;

	public Operator() {

		try {
			FileReader fr = new FileReader("/Users/khaledhammoud/Downloads/input-output-files/input-2.txt");
			BufferedReader br = new BufferedReader(fr);
			int max_wait_time = Integer.parseInt(br.readLine());
			total_players_count = Integer.parseInt(br.readLine());
			String currentLine;
			int thread_id;
			int player_waiting_time;
			Player player;
			this.wheel = new Wheel(max_wait_time);
			this.wheel.start();

			for (int i = 0; i < total_players_count; i++) {
				currentLine = br.readLine();
				StringTokenizer st = new StringTokenizer(currentLine, ",");
				thread_id = Integer.parseInt(st.nextToken());
				player_waiting_time = Integer.parseInt(st.nextToken());
				player = new Player(thread_id, player_waiting_time, this);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void addPlayer(Player player) {
		this.totalPlayers.add(player);
		if (this.totalPlayers.size() == this.total_players_count) {
			try {
				this.run_wheel();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("deprecation")
	public synchronized void run_wheel() throws InterruptedException {
		while (true) {
			while (this.wheel.getState().TIMED_WAITING != null) {

				if ((this.wheel.onBoardCount < this.wheel.capacity) && (this.totalPlayers.size() > 0)) {
					System.out.println("player wakes up: " + this.totalPlayers.get(0).id);

					System.out.println("passing player: " + this.totalPlayers.get(0).id + " to the operator");
					this.wheel.load_players(this.totalPlayers.get(0));
					System.out.println("Player: " + this.totalPlayers.get(0).id + " on board, capacity: "
							+ this.wheel.onBoardCount);
					this.totalPlayers.remove(this.totalPlayers.get(0));

				} else {

					synchronized (this.wheel) {
						this.wheel.notify();
					}
					break;
				}

			}
			if (this.totalPlayers.size() == 0) {
				this.wheel.stop();
				break;
			}

		}

	}

	public static void main(String[] args) {
		Operator operator = new Operator();
	}
}
