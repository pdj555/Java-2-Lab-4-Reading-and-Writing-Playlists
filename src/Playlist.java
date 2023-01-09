import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Playlist {
	
	private ArrayList<Song> songs;
	
	public Playlist() {
		songs = new ArrayList<Song>();
	}
	
	public Playlist (String filename) throws IOException {
		this();
		addSongs(filename);
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
		if (song == null || index < 0 || index > songs.size()) {
			return false;
		}
		else {
			songs.add(index, song);
		}
		return true;
	}
	
	public int addSongs(Playlist playlist) {
		if (playlist == null || playlist.getNumSongs() == 0) {
			return 0;
		}
		else {
			int count = playlist.getNumSongs();
			
			for (int i = 0; i < count; ++i) {
				this.addSong(playlist.getSong(i));
			}
			
			return count;
		}
	}
	
	public int addSongs(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		int counter = 0;
		
		while (br.readLine() != null) {
			counter++;
		}
		br.close();
		
		BufferedReader br1 = new BufferedReader(new FileReader(filename));
		
		for (int i = 0; i < counter; ++i) {
			Song song = new Song(br1.readLine());
			
			songs.add(song);
		}
		br1.close();
		
		return counter;
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		if (index >= getNumSongs() || index < 0 || songs.get(index) == null) {
			return null;
		}
		else {
			Song a = songs.get(index);
			songs.remove(index);
			return a;
		}
	}
	
	public void saveSongs(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		bw.write(toString());
		bw.close();
	}
	
	public String toString() {
		String output = "";
		
		if (songs != null) {
			for (int i = 0; i < getNumSongs(); ++i) {
				if (i == getNumSongs() - 1) {
					output = output + songs.get(i).toString();
				}
				else {
					output = output + songs.get(i).toString() + System.lineSeparator();
				}
			}
		}
		return output;
	}
	
	public int[] getTotalTime() {
		int[] totalTime = {0, 0, 0};
		
		for (Song song : songs) {
			int[] tempTime = song.getTime();
			
			for (int i = 0; i < tempTime.length; ++i) {
				totalTime[i] += tempTime[i];
			}
		}
		
		if (totalTime[0] > 59) {
			totalTime[1] += (totalTime[0] / 60);
			totalTime[0] = (totalTime[0] % 60);
		}
		
		if (totalTime[1] > 59) {
			totalTime[2] += (totalTime[1] / 60);
			totalTime[1] = (totalTime[1] % 60);
		}
		
		if (totalTime[2] == 0 && totalTime[1] == 0) {
			totalTime = new int[] {totalTime[0]};
		}
		else if (totalTime[2] == 0) {
			totalTime = new int[] {totalTime[0], totalTime[1]};
		}
		return totalTime;
	}
}
